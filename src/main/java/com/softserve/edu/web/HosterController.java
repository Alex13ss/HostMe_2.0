package com.softserve.edu.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserve.edu.entity.Hosting;
import com.softserve.edu.entity.User;
import com.softserve.edu.service.HostingService;
import com.softserve.edu.service.RegistrationService;
import com.softserve.edu.service.UserService;

@Controller
public class HosterController {
	@Autowired
	private UserService userService;

	@Autowired
	private RegistrationService registrationService;

	@RequestMapping(value = "/hoster", method = RequestMethod.GET)
	public String hosterShow(@RequestParam(value = "hosterId") int id,
			Model model) {
		User user = userService.getUser(id);
		model.addAttribute("user", user);

		return "hoster";
	}
}
