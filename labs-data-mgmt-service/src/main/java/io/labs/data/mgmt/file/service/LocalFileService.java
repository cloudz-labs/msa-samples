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

package io.labs.data.mgmt.file.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

@Service("fileService")
@Profile({"default", "test", "k8s"})
public class LocalFileService implements IFileService {
	
	private static Logger logger = LoggerFactory.getLogger(LocalFileService.class);

	@Value("${file.save.dir}")
	private String fileSaveDir;

	/**
	 * upload file.
	 * @param containerName container name
	 * @param fileName saved file name
	 * @param inputStream file contents
	 * @return String new file name
	 * @throws Exception exception
	 */
	@Override
	public String upload(String containerName, String fileName, InputStream inputStream)
			throws Exception {
		FileOutputStream fos = null;
		
		try {
			File dir = new File(fileSaveDir, containerName);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			
			String newFileName = null;
			File file = new File(dir, fileName);
			
			if (file.exists()) {
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
				int pos = fileName.lastIndexOf(".");
				if (pos != -1) {
					newFileName = fileName.substring(0, pos -1) + "-" + format.format(new Date()) + fileName.substring(pos);
				} else {
					newFileName = fileName + "-" + format.format(new Date());
				}
			} else {
				newFileName = fileName;
			}
			
			fos = new FileOutputStream(new File(dir, newFileName));
			FileCopyUtils.copy(inputStream, fos);
			
			logger.debug("uploaded file : " + newFileName);
			return newFileName;
		
		} catch (Exception e) {
			throw e;
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}
	
	/**
	 * get stored object.
	 * @param containerName container name
	 * @param fileName saved file name
	 * @return stored object
	 * @throws Exception exception
	 */
	@Override
	public Object getStoredObject(String containerName, String fileName) throws Exception {
		File dir = new File(fileSaveDir, containerName);
		File file = new File(dir, fileName);
		
		if (!file.exists()) {
			throw new Exception("File not found");
		}
		
		return file;
	}

	/**
	 * remove file.
	 * @param containerName container name
	 * @param fileName deleted file name
	 */
	@Override
	public void remove(String containerName, String fileName) {
		File dir = new File(fileSaveDir, containerName);
		File file = new File(dir, fileName);
		file.deleteOnExit();
	}

}
