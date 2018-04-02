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

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.labs.registration.question.service.QuestionService;
import io.labs.registration.question.vo.Question;

@RestController
@RequestMapping("/v1")
public class QuestionRestController {

	@Autowired
	private QuestionService questionService;

	@RequestMapping(path = "/questions/{category}", method = RequestMethod.GET,
			name = "getContents")
	public Question getContents(@PathVariable("category") String category) throws ParseException {
		return questionService.getContents(category);
	}
}
