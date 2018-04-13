package io.dtlabs.userbff.controller;

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.dtlabs.userbff.services.LibraryService;
import io.dtlabs.userbff.vo.LibraryCategory;

@RestController
@RequestMapping("/api/v1/library")
public class LibraryRestController {
	private static Logger logger = LoggerFactory.getLogger(CourseRestController.class);

	@Autowired
	private LibraryService libraryService;

	@RequestMapping(path="/docs", method=RequestMethod.GET)
	public List<LibraryCategory> getDocs() {
		List<LibraryCategory> list = libraryService.getDocs();
		logger.info("getDocs - categories length " + list.size());
		return list;
	}

	@RequestMapping(path="/attachment/{id}/{path:.+}", method=RequestMethod.GET, name="download")
	public void download(HttpServletResponse response, @PathVariable(name="id") String id,
			@PathVariable(name="path") String path) throws Exception {
		
		logger.info("download start for " + id + ", " + path);
		
		InputStream fis = libraryService.getDownloadStream(id, path);
		
		logger.info("download " + id + ", " + path);
		logger.info("download len : " + fis.available());

		response.setContentType("application/octet-stream");
		response.setContentLength(fis.available());
		response.setHeader("Content-Disposition",
				"attachment; fileName=" + URLEncoder.encode(path, "UTF-8"));
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		FileCopyUtils.copy( fis, response.getOutputStream() );
	}
}
