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

package io.dtlabs.registration.main.controller;

import java.util.List;

import javax.validation.Valid;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.dtlabs.registration.category.vo.Category;
import io.dtlabs.registration.main.service.RegistrationService;
import io.dtlabs.registration.main.vo.Registration;

@RestController
@RequestMapping("/v1")
public class RegistrationRestController {

	@Autowired
	private RegistrationService registrationService;
	
	@RequestMapping(path = "/registrations", method = RequestMethod.GET, name = "getRegistrations")
	public List<Registration> getRegistrations() throws ParseException {
		return registrationService.getRegistrations();
	}

	@RequestMapping(path = "/registrations/{id}", method = RequestMethod.GET,
			name = "getRegistration")
	public Registration getRegistration(@PathVariable("id") int id) throws ParseException {
		return registrationService.getRegistration(id);
	}

	@RequestMapping(path = "/registrations", method = RequestMethod.POST,
			name = "createRegistration")
	public void createRegistration(@Valid @RequestBody Registration registration) {
		registrationService.createRegistration(registration);
		registrationService.sendMail(); 
		return;
	}
	
	// 보안 적용 전까지 임시 주석.
	// @RequestMapping(path = "/registrations/{email:.+}", method = RequestMethod.POST,
	// name = "createRegistration")
	// public void createRegistration(@PathVariable("email") String email,
	// @RequestBody Registration registration) {
	// registrationService.createRegistration(email, registration);
	// registrationService.sendMail(email);
	// return;
	// }
	
	@RequestMapping(path = "/registrations/{id}/category", method = RequestMethod.GET,
			name = "getCategoryOfRegistration")
	public Category getCategoryOfRegistration(@PathVariable("id") int id) throws ParseException {
		return registrationService.getCateogryOfRegistration(id);
	}

}
