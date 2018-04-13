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

package io.dtlabs.registration.main.service;

import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.thoughtworks.xstream.mapper.Mapper.Null;

import io.dtlabs.registration.MailListConfiguration;
import io.dtlabs.registration.category.vo.Category;
import io.dtlabs.registration.main.dao.RegistrationDao;
import io.dtlabs.registration.main.vo.Notification;
import io.dtlabs.registration.main.vo.Registration;

@Service("registrationService")
public class RegistrationService {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationService.class);

	@Autowired
	private RegistrationDao registrationDao;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private RestTemplate notificationRestTemplate;

	@Autowired
	private MailListConfiguration mailList; 

//	@Value("${mailing.from}")
//	private String from;
//
//	@Value("${mailing.subject}")
//	private String subject;
//
//	@Value("${mailing.text}")
//	private String text;

	@Value("${hipchat-noti.color}")
	private String color;

	@Value("${hipchat-noti.message}")
	private String message;

	@Value("${hipchat-noti.notify}")
	private boolean notify;

	@Value("${hipchat-noti.message_format}")
	private String message_format;

	@Value("${hipchat-noti.url}")
	private String url;

	public List<Registration> getRegistrations() throws ParseException {

		List<Registration> registrationList = registrationDao.selectRegistrationList();
		for (Registration registration : registrationList) {
			String contents = (String) registration.getContents();

			JSONParser parser = new JSONParser();
			Object obj = parser.parse(contents);
			JSONObject jsonObj = (JSONObject) obj;

			registration.setContents(jsonObj);
		}
		return registrationList;
	}

	public Registration getRegistration(int id) throws ParseException {

		Registration registration = registrationDao.selectRegistration(id);
		String contents = (String) registration.getContents();

		JSONParser parser = new JSONParser();
		Object obj = parser.parse(contents);
		JSONObject jsonObj = (JSONObject) obj;

		registration.setContents(jsonObj);

		return registration; 
	}

	@Transactional
	public void createRegistration(Registration registration) {
		registrationDao.insertRegistration(registration);
	}
	// @Transactional
	// public void createRegistration(String email, Registration registration) {
	//
	// registration.setEmail(email);
	// registrationDao.insertRegistration(registration);
	//
	// }

	@HystrixCommand(fallbackMethod = "sendMailFallback")
	public void sendMail() {
//labs-mail-service 는 IaaS로 이전하면서 삭제됨
//		MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
//
//		String[] tos = mailList.getArray();
//		for (String to : tos) {
//			multiValueMap.add("to", to);
//		}
//
//		multiValueMap.add("from", from);
//		multiValueMap.add("subject", subject);
//		multiValueMap.add("text", text);
//
//		String result = restTemplate.postForObject("http://dtlabs-mail-service/v1/mail/send",
//				multiValueMap, String.class);
//		logger.debug("[http://dtlabs-mail-service/v1/mail/send] call result -> " + result);

		Notification notification = new Notification(color, message, notify, message_format);

		notificationRestTemplate.postForObject(url, notification, Null.class);
		logger.debug(url + "call.");
	}

	@SuppressWarnings("unused")
	private void sendMailFallback(Throwable t) {
		logger.error("Failed to send noti.", t);
	}

	// 보안 적용 전까지 임시 주석 처리.
	// @HystrixCommand(fallbackMethod = "sendMailFallback")
	// public void sendMail(String email) {
	//
	// MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
	//
	// String[] tos = mailList.getArray();
	// for (String to : tos) {
	// multiValueMap.add("to", to);
	// }
	//
	// multiValueMap.add("from", from);
	// multiValueMap.add("subject", email + subject);
	// multiValueMap.add("text", text);
	//
	// restTemplate.postForObject("http://dtlabs-mail-service/v1/mail/send", multiValueMap,
	// String.class);
	// }

	// @SuppressWarnings("unused")
	// private void sendMailFallback(String param, Throwable t) {
	// logger.error("Failed to send mail.", t);
	// }

	public Category getCateogryOfRegistration(int id) {
		return registrationDao.selectCategoryOfRegistration(id);
	}

}
