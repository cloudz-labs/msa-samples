package io.dtlabs.userbff.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import io.dtlabs.userbff.vo.Course;
import io.dtlabs.userbff.services.CourseService;

@RestController
@RequestMapping("/api/v1")
public class CourseRestController {
	private static Logger logger = LoggerFactory.getLogger(CourseRestController.class);

	@Autowired
	private CourseService courseService;

	@RequestMapping(path="/courses", method=RequestMethod.GET)
	public List<Course> getCourses() {
		List<Course> courses = courseService.getCourseList();
		logger.info("Courses : " + courses.toString());
		return courses;
	}

	@RequestMapping(path="/courses/{id}", method=RequestMethod.GET)
	public Course getCourse(@PathVariable(name="id") String id) {
		Course course = courseService.getCourse(id);
		logger.info("Course : " + course.toString());
		return course;
	}
	
	@RequestMapping(path="/asyncCourses", method=RequestMethod.GET, name="getAsyncCourse")
	public DeferredResult<ResponseEntity<?>> getAsyncCourse() {
		DeferredResult<ResponseEntity<?>> deferred = new DeferredResult<>();
		ListenableFuture<ResponseEntity<Course[]>> coursesFuture = courseService.asyncGetCourseList();
		coursesFuture.addCallback(
				new ListenableFutureCallback<ResponseEntity<Course[]>>() {
					@Override
					public void onSuccess(ResponseEntity<Course[]> courses) {
//						ResponseEntity<ResponseEntity<Course[]>> responseEntity = 
//							new ResponseEntity<>(courses, HttpStatus.OK);
//						deferred.setResult(responseEntity);
						deferred.setResult(courses);
					}
					@Override
					public void onFailure(Throwable ex) {
						logger.error("Failed to fetch result from remote service", ex);
						ResponseEntity<Void> responseEntity = 
						    new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
						deferred.setResult(responseEntity);
					}
				}
				);
		return deferred;
	}
}