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

package io.labs.data.mgmt.category.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.labs.data.mgmt.category.service.CategoryService;
import io.labs.data.mgmt.category.vo.Category;
import io.labs.data.mgmt.main.service.DocumentService;
import io.labs.data.mgmt.main.vo.Document;

@RestController
@RequestMapping("/v1")
public class CategoryRestController {

	private static Logger logger = LoggerFactory.getLogger(CategoryRestController.class);

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private DocumentService documentService;

	@RequestMapping(path = "/categories", method = RequestMethod.GET, name = "getCategories")
	public List<Category> getCategories() {
		List<Category> categories = categoryService.getCategories();
		return categories;
	}

	@RequestMapping(path = "/categories", method = RequestMethod.POST, name = "addCategory")
	public void addCategory(@RequestBody Category category) {
		categoryService.addCategory(category);
	}

	@RequestMapping(path = "/categories/{id}", method = RequestMethod.GET, name = "getCategory")
	public Category getCategory(@PathVariable(name = "id") String id) {
		Category category = categoryService.getCategory(id);
		return category;
	}

	@RequestMapping(path = "/categories/{id}/docs", method = RequestMethod.GET,
			name = "getDocuments")
	public List<Document> getDocuments(@PathVariable(name = "id") String id) {
		List<Document> documents = documentService.getDocuments(id);
		return documents;
	}

	@RequestMapping(path = "/categories/docs", method = RequestMethod.GET,
			name = "getCategoryDocuments")
	public List<Category> getCategoryDocuments() {
		List<Category> categories = categoryService.getCategories();
		categories.forEach(category -> {
			category.setDocuments(documentService.getDocuments(category.getId()));
			if (category.getDocuments() == null) {
				category.setDocuments(new ArrayList<Document>());
			}
		});
		return categories;
	}

}
