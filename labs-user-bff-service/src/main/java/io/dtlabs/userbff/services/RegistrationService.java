package io.dtlabs.userbff.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.dtlabs.userbff.vo.Course;
import io.dtlabs.userbff.vo.Questionnaire;

@Service
public class RegistrationService {
	@Autowired
	private RestTemplate restTemplate;

	@Value("${api.connect.path}")
	private String apiUrl;
	
	public Questionnaire getQuestionnaire(String categoryId) {
		return restTemplate.getForObject(
			apiUrl + "/registration-service/v1/questions/{categoryId}",
			Questionnaire.class,
			categoryId
		);
	}

	public void submitQuestionnaire(Questionnaire questionnaire) {
		restTemplate.postForEntity(
			apiUrl + "/registration-service/v1/registrations",
			questionnaire,
			null);
	}
}
