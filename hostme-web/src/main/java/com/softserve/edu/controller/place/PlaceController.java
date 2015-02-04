package com.softserve.edu.controller.place;

import com.softserve.edu.dto.PlaceDto;
import com.softserve.edu.dto.RoutePagingDto;
import com.softserve.edu.service.EventService;
import com.softserve.edu.service.SightseeingService;
import com.softserve.edu.service.routes.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@EnableSpringDataWebSupport
public class PlaceController {

    @Autowired
    PlaceService placeService;

    @Autowired
    EventService eventService;

    @Autowired
    SightseeingService sightseeingService;

    @RequestMapping(value = "/getPopularPlaces", method = RequestMethod.POST)
    public @ResponseBody
    List<PlaceDto> getPopularPlaces(@RequestBody RoutePagingDto routeRequest, Pageable pageable) {
        return placeService.placeToPlaceDto(placeService.getAllPlaces(pageable));
    }

    @RequestMapping(value = "place")
    public String showPlace(@RequestParam(value = "placeId") int placeId) {
        if (sightseeingService.haveSight(placeId)) {
            return "redirect:sightseeing?id=" + placeId;
        } else if (eventService.haveEvent(placeId)) {
            return "redirect:event?id=" + placeId;
        }
        return "redirect:profile";
    }
}
