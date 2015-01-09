package com.softserve.edu.controller;


import javax.validation.Valid;

import com.softserve.edu.model.User;
import com.softserve.edu.model.UserState;
import com.softserve.edu.service.RegistrationService;
import com.softserve.edu.service.implementation.RegistrationSendMailImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private RegistrationSendMailImpl registrationSendMail;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationShow(Model model) {
        return "redirect:/index";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String addContact(@Valid @ModelAttribute("user") User user,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "index";
        }
        String birthday = (String) bindingResult.getFieldValue("birthday");
        String gender = (String) bindingResult.getFieldValue("gender");
        UserState userState = UserState.INACTIVE;
        registrationService.register(user, birthday, gender, userState);
        Thread mailSenderThread = new Thread(new Runnable() {
            public void run() {
                registrationSendMail.sendWelcomeMail(user);
            }
        });
        mailSenderThread.start();
        return "redirect:/login?registration=true";
    }

    @RequestMapping(value = "registration-confirm", method = RequestMethod.GET)
    public String confirmRegistration(@RequestParam(value = "id", required = true) Integer userId) {
        registrationService.activateAccount(userId);
        return "redirect:/login?success=true";
    }
}
