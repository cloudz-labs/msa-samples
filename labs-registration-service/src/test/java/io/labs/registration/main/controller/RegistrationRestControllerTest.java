/*
 * Copyright (c) 2016 SK holdings Co., Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package io.labs.registration.main.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import io.labs.registration.main.service.RegistrationService;
import io.labs.registration.main.vo.Registration;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,
		properties = {"spring.cloud.config.enabled=false", "eureka.client.enabled=false"})
@AutoConfigureTestDatabase(connection=EmbeddedDatabaseConnection.H2)
@SuppressWarnings("rawtypes")
public class RegistrationRestControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	private RegistrationService registrationService;

	@Test
	public void getRegistrationsShouldReturnMakeAndModel() throws ParseException {
		final List<Registration> registrations = new ArrayList<Registration>();
		Registration registration = new Registration();
		registration.setId(1);
		registration.setEmail("labs@sk.com");
		registrations.add(registration);
		given(registrationService.getRegistrations()).willReturn(registrations);

		ResponseEntity<List> entity = restTemplate.getForEntity("/v1/registrations", List.class, (Object) null);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody().size()).isNotZero();
	}

	@Test
	public void getRegistrationShouldReturnMakeAndModel() throws ParseException {
		Registration registration = new Registration();
		registration.setId(1);
		registration.setEmail("labs@sk.com");
		given(registrationService.getRegistration(anyInt())).willReturn(registration);

		ResponseEntity<Registration> entity =
				restTemplate.getForEntity("/v1/registrations/{id}", Registration.class, 1);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody().getId()).isEqualTo(1);
	}

	// @Test
	// public void createRegistrationShouldReturnMakeAndModel() throws ParseException {
	// Registration registration = new Registration();
	// registration.setEmail("labs@sk.com");
	// registration.setContents("");
	// ResponseEntity entity = restTemplate.postForEntity("/v1/registrations/{email}",
	// registration, null, "labs@sk.com");
	// assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	// }

}
