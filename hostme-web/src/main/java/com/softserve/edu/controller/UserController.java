package com.softserve.edu.controller;

import com.softserve.edu.service.ProfileService;
import com.softserve.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ProfileService profileService;

    @RequestMapping (value = "/addBookedPlace", method = RequestMethod.POST)
    public @ResponseBody boolean addBookingPlace(@RequestBody String placeId) {
        int bookPlace = Integer.valueOf(placeId.replace("=", ""));
        userService.addBookedPlace(bookPlace);
        return true;
    }
}
