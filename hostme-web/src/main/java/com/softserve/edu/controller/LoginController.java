package com.softserve.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//import com.softserve.edu.repositories.ConversationRepository;

@Controller
public class LoginController {
//    @Autowired
//    private ConversationRepository repository;
	@RequestMapping("/login")
	public String login() {
//	    System.out.println(repository.count());
		return "login";
	}
}