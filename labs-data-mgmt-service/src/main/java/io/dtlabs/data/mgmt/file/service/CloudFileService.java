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

package io.dtlabs.data.mgmt.file.service;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.javaswift.joss.model.Account;
import org.javaswift.joss.model.Container;
import org.javaswift.joss.model.StoredObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service("fileService")
@Profile({"dev", "stg", "prod"})
public class CloudFileService implements IFileService {

	private static Logger logger = LoggerFactory.getLogger(CloudFileService.class);

	@Autowired
	private Account account;

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
		if (account == null) {
			throw new Exception("No Acount");
		}

		Container container = account.getContainer(containerName);
		if (!container.exists()) {
			container.create();
			container.makePublic();
		}

		String newFileName = null;
		StoredObject object = container.getObject(fileName);
		
		if (object.exists()) {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			int pos = fileName.lastIndexOf(".");
			if (pos != -1) {
				newFileName = fileName.substring(0, pos -1) + "-" + format.format(new Date()) + fileName.substring(pos);
			} else {
				newFileName = fileName + "-" + format.format(new Date());
			}

			object = container.getObject(newFileName);
		} else {
			newFileName = fileName;
		}

		object.uploadObject(inputStream);

		logger.debug("uploaded file : " + object.getPublicURL());
		
		return newFileName;
	}

	/**
	 * get stored object.
	 * @param containerName container name
	 * @param fileName saved file name
	 * @return stored object
	 * @throws Exception exception
	 */
	@Override
	public StoredObject getStoredObject(String containerName, String fileName) throws Exception {
		Container container = account.getContainer(containerName);
		StoredObject object = container.getObject(fileName);
		
		if (!object.exists()) {
			throw new Exception("File not found");
		}
		
		return object;
	}

	/**
	 * remove file.
	 * @param containerName container name
	 * @param fileName deleted file name
	 */
	@Override
	public void remove(String containerName, String fileName) {
		Container container = account.getContainer(containerName);
		StoredObject object = container.getObject(fileName);
		if (object.exists()) {
			object.delete();
		}
	}

}
