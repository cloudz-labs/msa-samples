/*
 * Copyright (c) 2017 SK holdings Co., Ltd
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

package io.dtlabs.data.mgmt.category.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import io.dtlabs.data.mgmt.category.dao.CategoryDao;
import io.dtlabs.data.mgmt.category.vo.Category;

import org.springframework.stereotype.Service;

@Service("categoryService")
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	public List<Category> getCategories() {
		return categoryDao.findAllByOrderByOrder();
	}

	public Category getCategory(String id) {
		return categoryDao.findOne(id);
	}

	public Category addCategory(Category category) {
		return categoryDao.save(category);
	}
}
