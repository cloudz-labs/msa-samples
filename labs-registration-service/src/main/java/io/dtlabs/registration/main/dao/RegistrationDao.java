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

package io.dtlabs.registration.main.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.dtlabs.registration.category.vo.Category;
import io.dtlabs.registration.main.vo.Registration;

@Repository("registrationDao")
public class RegistrationDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public List<Registration> selectRegistrationList() {
		return sqlSessionTemplate.selectList("registration.selectRegistrationList");
	}

	public Registration selectRegistration(int id) {
		return sqlSessionTemplate.selectOne("registration.selectRegistration", id);
	}

	public void insertRegistration(Registration registration) {
		sqlSessionTemplate.insert("registration.insertRegistration", registration);
	}
	
	public Category selectCategoryOfRegistration(int id) {
		return sqlSessionTemplate.selectOne("registration.selectCategoryOfRegistration", id);
	}

}
