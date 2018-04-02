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

package io.labs.data.mgmt.main.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.labs.data.mgmt.main.vo.Document;

public interface DocumentDao extends MongoRepository<Document, String> {

	public List<Document> findByCategories(String category);

	public List<Document> findByTitleContainingOrContentsContainingOrderByRegDateDesc(String title,
			String contents);

}
