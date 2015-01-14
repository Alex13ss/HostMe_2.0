package com.softserve.edu.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softserve.edu.model.Event;
import com.softserve.edu.model.Request;
import com.softserve.edu.model.User;
import com.softserve.edu.service.CityService;
import com.softserve.edu.service.CountryService;
import com.softserve.edu.service.EventService;
import com.softserve.edu.service.ProfileService;

@Controller
public class EventContoller {
	
	@Autowired
	EventService eventService;
	
	@Autowired
	ProfileService profileService;

		
	@RequestMapping(value = "/events", method = RequestMethod.GET)
	public @ResponseBody List<Event> getAllEvents(){
		
		List<Event> events = eventService.getAllEvents();
		return events;
		
	}
	
	
}
