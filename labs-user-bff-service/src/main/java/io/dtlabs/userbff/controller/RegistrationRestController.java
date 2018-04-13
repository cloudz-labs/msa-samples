package io.dtlabs.userbff.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.dtlabs.userbff.services.CourseService;
import io.dtlabs.userbff.services.RegistrationService;
import io.dtlabs.userbff.vo.Questionnaire;
import io.dtlabs.userbff.vo.RegistrationQuestionnaire;

@RestController
@RequestMapping("/api/v1")
public class RegistrationRestController {
	private static Logger logger = LoggerFactory.getLogger(CourseRestController.class);

		@Autowired
		private CourseService courseService;
		@Autowired
		private RegistrationService registrationService;

		@RequestMapping(path="/registration/{categoryId}", method=RequestMethod.GET)
		public RegistrationQuestionnaire getQuestionnaire(@PathVariable(name="categoryId") String categoryId) {
			RegistrationQuestionnaire result = new RegistrationQuestionnaire();
			result.setCourse(courseService.getCourse(categoryId));
			result.setQuestionnaire(registrationService.getQuestionnaire(categoryId));
			return result;
		}

		@RequestMapping(path="/registration", method=RequestMethod.POST)
		public void submitRegistration(@RequestBody Questionnaire questionnaire) {
			registrationService.submitQuestionnaire(questionnaire);
		}

}