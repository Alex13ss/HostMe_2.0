package com.softserve.edu.controller.place;

import com.softserve.edu.service.EventService;
import com.softserve.edu.service.SightseeingService;
import com.softserve.edu.service.routes.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlaceController {

    @Autowired
    PlaceService placeService;

    @Autowired
    EventService eventService;

    @Autowired
    SightseeingService sightseeingService;

    @RequestMapping(value = "place")
    public String showPlace(@RequestParam(value = "placeId") int placeId) {
        if (sightseeingService.haveSight(placeId)) {
            return "redirect:sightseeing?id=" + placeId;
        }
        return "redirect:profile";
    }
}
