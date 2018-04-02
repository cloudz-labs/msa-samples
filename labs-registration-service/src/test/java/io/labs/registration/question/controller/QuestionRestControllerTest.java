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

package io.labs.registration.question.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;

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

import io.labs.registration.question.service.QuestionService;
import io.labs.registration.question.vo.Question;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,
		properties = {"spring.cloud.config.enabled=false", "eureka.client.enabled=false"})
@AutoConfigureTestDatabase(connection=EmbeddedDatabaseConnection.H2)
public class QuestionRestControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	private QuestionService qusetionService;

	@Test
	public void getContentsShouldReturnMakeAndModel() throws ParseException {
		Question question = new Question();
		question.setCategory("1");
		given(qusetionService.getContents(anyString())).willReturn(question);

		ResponseEntity<Question> entity =
				restTemplate.getForEntity("/v1/questions/{category}", Question.class, 1);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody().getCategory()).isEqualTo("1");
	}

}
