package io.labs.userbff.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import io.labs.userbff.vo.Course;

@Service("courseService")
public class CourseService {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private AsyncRestTemplate asyncRestTemplate;

	@Value("${api.connect.path}")
	private String apiUrl;

	public List<Course> getCourseList() {
		return Arrays.asList(
			restTemplate.getForObject(
					apiUrl + "/registration-service/v1/categories",
					Course[].class)
		);
	}

	public Course getCourse(String categoryId) {
		return restTemplate.getForObject(
			apiUrl + "/registration-service/v1/categories/{categoryId}", Course.class, categoryId
		);
	}

	public ListenableFuture<ResponseEntity<Course[]>> asyncGetCourseList() {
		return asyncRestTemplate.getForEntity(
			apiUrl + "/registration-service/v1/categories",
			Course[].class);
	}
}