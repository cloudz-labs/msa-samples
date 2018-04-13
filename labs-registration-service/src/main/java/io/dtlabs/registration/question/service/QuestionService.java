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

package io.dtlabs.registration.question.service;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.dtlabs.registration.question.dao.QuestionDao;
import io.dtlabs.registration.question.vo.Question;

@Service("questionService")
public class QuestionService {

	@Autowired
	private QuestionDao questionDao;

	public Question getContents(String category) throws ParseException {

		Question question = questionDao.selectContents(category);
		String contents = (String) question.getContents();

		JSONParser parser = new JSONParser();
		Object obj = parser.parse(contents);
		JSONObject jsonObj = (JSONObject) obj;

		question.setContents(jsonObj);

		return question;
	}

}
