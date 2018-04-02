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

package io.labs.data.mgmt.preview.service;

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
import io.labs.data.mgmt.main.vo.Attachment;
import io.labs.data.mgmt.preview.dao.PreviewDao;
import io.labs.data.mgmt.preview.vo.Preview;

@Service("previewService")
public class PreviewService {
	
	private static final Logger logger = LoggerFactory.getLogger(PreviewService.class);
	
	@Autowired
	private PreviewDao previewDao;
	
	@Autowired
	private IFileService fileService;

	@Value("${attachment.preview.container}")
	private String containerName;
	
	public List<Preview> getPreviews() {
		return previewDao.findAll();
	}
	
	public List<Preview> getPreviews(String category) {
		return previewDao.findByCategories(category);
	}
	
	public Preview getPreview(String id) {
		return previewDao.findOne(id);
	}
	
	public Preview addPreview(Preview preview, List<MultipartFile> multipartFiles) throws Exception {
		try {
			int index = 0;
			for (MultipartFile multipart : multipartFiles) {
				String fileName = fileService.upload(containerName, multipart.getOriginalFilename(), multipart.getInputStream());
				logger.debug(fileName + " added!!");
				
				preview.getAttachments().get(index).setPath(fileName);
				index++;
			}

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			preview.setRegDate(format.format(new Date()));

			return previewDao.insert(preview);

		} catch (Exception e) {
			preview.getAttachments().forEach(attachment -> {
				if (attachment.getPath() != null && !attachment.getPath().isEmpty()) {
					fileService.remove(containerName, attachment.getPath());
				}
			});

			throw e;
		}
	}

	public Preview modifyPreview(Preview preview, List<MultipartFile> multipartFiles)
			throws Exception {
		Preview oldPreview = previewDao.findOne(preview.getId());
		logger.debug("modifyPreview() : old preview -> " + oldPreview);
		logger.debug("modifyPreview() : new preview -> " + preview);

		// deleted file
		for (Attachment oldAttachment : oldPreview.getAttachments()) {
			boolean deleted = true;
			for (Attachment attachment : preview.getAttachments()) {
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
		for (Attachment attachment : preview.getAttachments()) {
			boolean added = true;
			for (Attachment oldAttachment : oldPreview.getAttachments()) {
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
		preview.setRegDate(format.format(new Date()));

		return previewDao.save(preview);
	}

	public void removeDocument(String id) {
		Preview doc = previewDao.findOne(id);
		if (doc != null) {
			doc.getAttachments().forEach(attachment -> {
				if (attachment.getPath() != null && !attachment.getPath().isEmpty()) {
					fileService.remove(containerName, attachment.getPath());
					logger.debug(attachment.getPath() + " deleted!!");
				}
			});

			previewDao.delete(id);
		}
	}

	public Object getStoredObject(String id, String path) throws Exception {
		return fileService.getStoredObject(containerName, path);
	}

}
