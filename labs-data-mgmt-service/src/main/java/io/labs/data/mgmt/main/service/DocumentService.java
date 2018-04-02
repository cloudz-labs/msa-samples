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

package io.labs.data.mgmt.main.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.labs.data.mgmt.file.service.IFileService;
import io.labs.data.mgmt.main.dao.DocumentDao;
import io.labs.data.mgmt.main.vo.Attachment;
import io.labs.data.mgmt.main.vo.Document;

@Service("documentService")
public class DocumentService {

	private static Logger logger = LoggerFactory.getLogger(DocumentService.class);

	@Autowired
	private DocumentDao documentDao;

	@Autowired
	private IFileService fileService;

	@Value("${attachment.document.container}")
	private String containerName;

	public List<Document> getDocuments() {
		return documentDao.findAll();
	}

	public List<Document> getDocuments(String category) {
		return documentDao.findByCategories(category);
	}

	public List<Document> searchDocuments(String query) {
		return documentDao.findByTitleContainingOrContentsContainingOrderByRegDateDesc(query,
				query);
	}

	public Document getDocument(String id) {
		return documentDao.findOne(id);
	}

	/**
	 * add document.
	 * @param doc document
	 * @param multipartFiles attachment files
	 * @return document
	 * @throws Exception exception
	 */
	public Document addDocument(Document doc, List<MultipartFile> multipartFiles) throws Exception {
		try {
			int index = 0;
			for (MultipartFile multipart : multipartFiles) {
				String fileName = fileService.upload(containerName, multipart.getOriginalFilename(), multipart.getInputStream());
				logger.debug(fileName + " added!!");
				
				doc.getAttachments().get(index).setPath(fileName);
				index++;
			}

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			doc.setRegDate(format.format(new Date()));

			return documentDao.insert(doc);

		} catch (Exception e) {
			doc.getAttachments().forEach(attachment -> {
				if (attachment.getPath() != null && !attachment.getPath().isEmpty()) {
					fileService.remove(containerName, attachment.getPath());
				}
			});

			throw e;
		}
	}

	/**
	 * modify document.
	 * @param doc document
	 * @param multipartFiles attachment files
	 * @return document
	 * @throws Exception exception
	 */
	public Document modifyDocument(Document doc, List<MultipartFile> multipartFiles)
			throws Exception {
		Document oldDoc = documentDao.findOne(doc.getId());
		logger.debug("modifyDocument() : old document -> " + oldDoc);
		logger.debug("modifyDocument() : new document -> " + doc);

		// deleted file
		for (Attachment oldAttachment : oldDoc.getAttachments()) {
			boolean deleted = true;
			for (Attachment attachment : doc.getAttachments()) {
				if (oldAttachment.getPath().equals(attachment.getPath())) {
					deleted = false;
					break;
				}
			}

			if (deleted) {
				logger.debug(oldAttachment.getPath() + " deleted!!");
				fileService.remove(containerName, oldAttachment.getPath());
			}
		}

		// added file
		int index = 0;
		for (Attachment attachment : doc.getAttachments()) {
			boolean added = true;
			for (Attachment oldAttachment : oldDoc.getAttachments()) {
				if (attachment.getPath().equals(oldAttachment.getPath())) {
					added = false;
					break;
				}
			}
			
			if(added) {
				String fileName = fileService.upload(containerName, multipartFiles.get(index).getOriginalFilename(),
						multipartFiles.get(index).getInputStream());
				logger.debug(fileName + " added!!");

				attachment.setPath(fileName);
				index++;
			}
		}

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		doc.setRegDate(format.format(new Date()));

		return documentDao.save(doc);
	}

	/**
	 * remove document.
	 * @param id document id
	 */
	public void removeDocument(String id) {
		Document doc = documentDao.findOne(id);
		if (doc != null) {
			doc.getAttachments().forEach(attachment -> {
				if (attachment.getPath() != null && !attachment.getPath().isEmpty()) {
					fileService.remove(containerName, attachment.getPath());
					logger.debug(attachment.getPath() + " deleted!!");
				}
			});

			documentDao.delete(id);
		}
	}

	public Object getStoredObject(String id, String path) throws Exception {
		return fileService.getStoredObject(containerName, path);
	}

}
