package com.softserve.edu.controller.admin;

import java.util.List;

import com.softserve.edu.model.User;
import com.softserve.edu.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/usersManager")
    public String userManager(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "usersManager";
    }
    
	@RequestMapping(value = "/all-users", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<User> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return users;
	}
}
