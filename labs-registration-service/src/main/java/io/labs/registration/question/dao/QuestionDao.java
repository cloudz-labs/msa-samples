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

package io.labs.registration.question.dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.labs.registration.question.vo.Question;

@Repository("questionDao")
public class QuestionDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	/**
	 * @param category
	 * @return
	 */
	public Question selectContents(String category) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("category", category);
		return sqlSessionTemplate.selectOne("question.selectQuestion", map);
	}
}
