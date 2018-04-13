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

package io.dtlabs.data.mgmt.preview.controller;

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

import io.dtlabs.data.mgmt.preview.service.PreviewService;
import io.dtlabs.data.mgmt.preview.vo.Preview;

@RestController
@RequestMapping("/v1")
public class PreviewRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(PreviewRestController.class);

	@Autowired
	private PreviewService previewService; 
	
	@RequestMapping(path = "/docs/preview", method = RequestMethod.GET, name = "getPreviews")
	public List<Preview> getPreviews(@RequestParam(name = "category", required = false) String category) {
		List<Preview> previews = null;

		if (category == null || category.isEmpty()) {
			previews = previewService.getPreviews();
		} else {
			previews = previewService.getPreviews(category);
		}

		return previews;
	}
	
	@RequestMapping(path = "/docs/{id}/preview", method = RequestMethod.GET, name = "getPreview")
	public Preview getPreview(@PathVariable(name = "id") String id) {
		Preview preview = previewService.getPreview(id);
		return preview;
	}
	
	@RequestMapping(path = "/docs/preview", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, name = "addPreview")
	public void addPreview(@ModelAttribute Preview preview, @RequestParam("files") List<MultipartFile> multipartFiles) throws Exception {
		previewService.addPreview(preview, multipartFiles);
	}

	@RequestMapping(path = "/docs/{id}/preview", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, name = "modifyPreview")
	public void modifyPreview(@PathVariable(name = "id") String id, @ModelAttribute Preview preview, @RequestParam("files") List<MultipartFile> multipartFiles) throws Exception {
		logger.debug("preview [id: " + id + "] modified");

		if (preview.getId() == null || preview.getId().isEmpty()) {
			preview.setId(id);
		}

		previewService.modifyPreview(preview, multipartFiles);
	}

	@RequestMapping(path = "/docs/{id}/preview", method = RequestMethod.DELETE, name = "removePreview")
	public void removePreview(@PathVariable(name = "id") String id) {
		logger.debug("preview [id: " + id + "] removed");
		previewService.removeDocument(id);
	}

	@RequestMapping(path = "/docs/{id}/preview/attachments/{path:.+}", method = RequestMethod.GET, name = "download")
	public void download(HttpServletResponse response, @PathVariable(name = "id") String id, @PathVariable(name = "path") String path) throws Exception {
		Object object = previewService.getStoredObject(id, path);
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
