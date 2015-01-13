package com.softserve.edu.controller;

import com.softserve.edu.model.User;
import com.softserve.edu.service.ImageService;
import com.softserve.edu.service.ProfileService;
import com.softserve.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProfileController {

	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ImageService imageService;

	public User getCurrentUser() {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		String currentPrincipalName = authentication.getName();

		return profileService.getUserByLogin(currentPrincipalName);
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profileShow(Model model) {

		User user = getCurrentUser();

		model.addAttribute("age", profileService.calcAge(user));
		model.addAttribute("birth", profileService.receiveBirthday(user.getBirthday()));
		model.addAttribute("user", user);
		return "profile";
	}

}
