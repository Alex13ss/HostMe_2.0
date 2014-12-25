package com.softserve.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	/*
	 * Just now we are using default spring-security bean for login-processing,
	 * so this controller has no logic.
	 */
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
}