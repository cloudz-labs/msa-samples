package io.dtlabs.userbff.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import io.dtlabs.userbff.vo.LibraryCategory;

@Service("libraryService")
public class LibraryService {
	@Autowired
	private RestTemplate restTemplate;

	@Value("${api.connect.path}")
	private String apiUrl;

	public List<LibraryCategory> getDocs() {
		return Arrays.asList(
			restTemplate.getForObject(
				apiUrl + "/data-mgmt-service/v1/categories/docs",
				LibraryCategory[].class
			)
		);
	}

	public InputStream getDownloadStream(String id, String path) {
		Map<String, String> uriParams = new HashMap<String, String>();
		uriParams.put("id", id);
		uriParams.put("path", path);

		URI targetUrl = UriComponentsBuilder.fromUriString(apiUrl)
			.path("/data-mgmt-service/v1/docs/{id}/attachments/{path}")
			.buildAndExpand(uriParams)
			.encode()
			.toUri();
		InputStream is = null;
		ResponseEntity<Resource> responseEntity = restTemplate.exchange(targetUrl, HttpMethod.GET, null, Resource.class);
		try {
			is = responseEntity.getBody().getInputStream();
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		return is;
	}
}