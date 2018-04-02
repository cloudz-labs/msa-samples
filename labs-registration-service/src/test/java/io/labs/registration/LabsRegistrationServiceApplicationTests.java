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

package io.labs.registration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import io.labs.registration.category.controller.CategoryRestController;
import io.labs.registration.main.controller.RegistrationRestController;
import io.labs.registration.question.controller.QuestionRestController;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,
properties = {"spring.cloud.config.enabled=false", "eureka.client.enabled=false"})
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class LabsRegistrationServiceApplicationTests {
	
	@Autowired
	private RegistrationRestController registrationRestController;
	
	@Autowired
	private CategoryRestController categoryRestController;
	
	@Autowired
	private QuestionRestController questionRestController;

	@Test
	public void contextLoads() throws Exception {
		assertThat(registrationRestController).isNotNull();
		assertThat(categoryRestController).isNotNull();
		assertThat(questionRestController).isNotNull();
	}

}
