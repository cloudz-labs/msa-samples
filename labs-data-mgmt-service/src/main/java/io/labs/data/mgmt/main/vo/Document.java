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

package io.labs.data.mgmt.main.vo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

@org.springframework.data.mongodb.core.mapping.Document(collection = "Document")
public class Document {

	@Id
	private String id;

	private List<String> categories;

	private String title;

	private String contents;

	private List<Link> links;

	private List<Attachment> attachments;

	private String regDate;
	
	public Document() {
		this.categories = new ArrayList<String>();
		this.links = new ArrayList<Link>();
		this.attachments = new ArrayList<Attachment>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	@Override
	public String toString() {
		return "Document [id=" + id + ", categories=" + categories + ", title=" + title
				+ ", contents=" + contents + ", links=" + links + ", attachments=" + attachments
				+ ", regDate=" + regDate + "]";
	}

}
