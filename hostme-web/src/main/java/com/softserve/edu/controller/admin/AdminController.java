package com.softserve.edu.controller.admin;

import com.softserve.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/usersManager")
    public String userManager(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "usersManager";
    }
}
