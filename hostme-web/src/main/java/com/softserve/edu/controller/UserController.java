package com.softserve.edu.controller;

import com.softserve.edu.dto.PlaceDto;
import com.softserve.edu.model.routes.Place;
import com.softserve.edu.service.ProfileService;
import com.softserve.edu.service.UserService;
import com.softserve.edu.service.routes.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ProfileService profileService;

    @Autowired
    PlaceService placeService;

    @RequestMapping (value = "/addBookedPlace", method = RequestMethod.POST)
    public @ResponseBody boolean addBookingPlace(@RequestBody String placeId) {
        int bookPlaceId = Integer.valueOf(placeId.replace("=", ""));
        userService.addBookedPlace(bookPlaceId);
        return true;
    }

    @RequestMapping (value = "/getBookedPlaces")
    public @ResponseBody List<PlaceDto> getBookedPlaces() {
        return placeService.placeToPlaceDto(userService.getBookedPlaces());
    }
}
