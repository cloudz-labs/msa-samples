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

package io.dtlabs.data.mgmt.main.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.javaswift.joss.model.StoredObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.dtlabs.data.mgmt.main.service.DocumentService;
import io.dtlabs.data.mgmt.main.vo.Document;

@RestController
@RequestMapping("/v1")
public class DocumentRestController {

	private static Logger logger = LoggerFactory.getLogger(DocumentRestController.class);

	@Autowired
	private DocumentService documentService;

	/**
	 * get document list.
	 * @param category category id
	 * @return document list
	 */ 
	@RequestMapping(path = "/docs", method = RequestMethod.GET, name = "getDocuments")
	public List<Document> getDocuments(
			@RequestParam(name = "category", required = false) String category) {
		List<Document> documents = null; 

		if (category == null || category.isEmpty()) {
			documents = documentService.getDocuments();
		} else {
			documents = documentService.getDocuments(category);
		}

		return documents;
	}

	/**
	 * add document.
	 * @param doc document
	 * @param multipartFiles attachment files
	 * @throws Exception exception
	 */
	@RequestMapping(path = "/docs", method = RequestMethod.POST,
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE, name = "addDocument")
	public void addDocument(@ModelAttribute Document doc,
			@RequestParam("files") List<MultipartFile> multipartFiles) throws Exception {
		documentService.addDocument(doc, multipartFiles);
	}

	/**
	 * search document.
	 * @param query query string
	 * @return document list
	 */
	@RequestMapping(path = "/docs/search", method = RequestMethod.GET, name = "searchDocuments")
	public List<Document> searchDocuments(
			@RequestParam(name = "q", required = false) String query) {

		List<Document> documents = null;

		if (query == null || query.isEmpty()) {
			documents = documentService.getDocuments();
		} else {
			documents = documentService.searchDocuments(query);
		}

		return documents;
	}

	/**
	 * get document.
	 * @param id document id
	 * @return document
	 */
	@RequestMapping(path = "/docs/{id}", method = RequestMethod.GET, name = "getDocument")
	public Document getDocument(@PathVariable(name = "id") String id) {
		Document doc = documentService.getDocument(id);
		return doc;
	}

	/**
	 * modify document.
	 * @param id document id
	 * @param doc document
	 * @param multipartFiles attachment files
	 * @throws Exception exception
	 */
	@RequestMapping(path = "/docs/{id}", method = RequestMethod.POST,
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE, name = "modifyDocument")
	public void modifyDocument(@PathVariable(name = "id") String id, @ModelAttribute Document doc,
			@RequestParam("files") List<MultipartFile> multipartFiles) throws Exception {
		logger.debug("document [id: " + id + "] modified");

		if (doc.getId() == null || doc.getId().isEmpty()) {
			doc.setId(id);
		}

		documentService.modifyDocument(doc, multipartFiles);
	}

	/**
	 * remove document.
	 * @param id document id
	 */
	@RequestMapping(path = "/docs/{id}", method = RequestMethod.DELETE, name = "removeDocument")
	public void removeDocument(@PathVariable(name = "id") String id) {
		logger.debug("document [id: " + id + "] removed");
		documentService.removeDocument(id);
	}

	/**
	 * download attachment.
	 * @param response HttpServletResponse
	 * @param id document id
	 * @param path attachment file name
	 * @throws Exception exception
	 */
	@RequestMapping(path = "/docs/{id}/attachments/{path:.+}", method = RequestMethod.GET,
			name = "download")
	public void download(HttpServletResponse response, @PathVariable(name = "id") String id,
			@PathVariable(name = "path") String path) throws Exception {
		
		Object object = documentService.getStoredObject(id, path);
		int len = 0;
		InputStream is = null;
		if (object instanceof StoredObject) {
			len = (int) ((StoredObject) object).getContentLength();
			is = ((StoredObject) object).downloadObjectAsInputStream();
		} else if (object instanceof File) {
			FileInputStream fis = new FileInputStream((File) object);
			len = fis.available();
			is = fis;
		}

		response.setContentType("application/octet-stream");
		response.setContentLength(len);
		response.setHeader("Content-Disposition",
				"attachment; fileName=\"" + URLEncoder.encode(path, "UTF-8") + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");

		FileCopyUtils.copy(is, response.getOutputStream());
	}

}
