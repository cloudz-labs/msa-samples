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

package io.dtlabs.registration.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.dtlabs.registration.category.service.CategoryService;
import io.dtlabs.registration.category.vo.Category;

@RestController
@RequestMapping("/v1")
public class CategoryRestController {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(path = "/categories", method = RequestMethod.GET, name = "getCategories")
	public List<Category> getCategories() {
		List<Category> categories = categoryService.getCategories();
		return categories;
	}
	
	@RequestMapping(path = "/categories/{id}", method = RequestMethod.GET, name = "getCategory")
	public Category getCategory(@PathVariable(name="id") int id) {
		return categoryService.getCategory(id);
	}
}
