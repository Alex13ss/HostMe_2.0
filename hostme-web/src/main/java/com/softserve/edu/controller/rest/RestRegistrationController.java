package com.softserve.edu.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softserve.edu.entity.User;
import com.softserve.edu.service.RegistrationService;

@RestController
public class RestRegistrationController {

	@Autowired
	private RegistrationService registrationService;

	@RequestMapping("/check-login")
	public Boolean checkLoginIdentity(
			@RequestParam(value = "login") String login) {
		boolean result = false;
		User loginUser = registrationService.getUserByLogin(login);
		if (loginUser != null) {
			result = true;
		}
		System.out.println("entered");

		return result;
	}

	@RequestMapping("/check-email")
	public Boolean checkEmailIdentity(
			@RequestParam(value = "email") String email) {
		boolean result = false;
		User emailUser = registrationService.getUserByEmail(email);
		if (emailUser != null) {
			result = true;

		}
		System.out.println("entered");
		return result;

	}

	@RequestMapping("/test")
	public Boolean checkEmailLoginIdentity(
			@RequestParam(value = "email") String email,
			@RequestParam(value = "login") String login) {
		boolean result = false;
		User emailUser = registrationService.getUserByEmail(email);
		User loginUser = registrationService.getUserByLogin(login);
		if (emailUser != null && loginUser != null) {
			result = true;

		}
		System.out.println("entered");
		return result;

	}

}
