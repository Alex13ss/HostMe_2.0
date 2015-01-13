package com.softserve.edu.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserve.edu.model.Event;
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
	
	
	private User getCurrentUser() {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		String currentPrincipalName = authentication.getName();

		return profileService.getUserByLogin(currentPrincipalName);
	}

	
	
	@RequestMapping(value = "/events", method = RequestMethod.GET)
	public String getEvents(Model model){
		List<Event> events = eventService.getAllEvents();
		model.addAttribute("events", events);
		return "events";
		
	}
	
	@RequestMapping(value = "/my-events", method = RequestMethod.GET)
	public String getEventsByOwner(Model model){
		
		User user = getCurrentUser();
		
		List<Event> events = eventService.getEventByOwner(user);
		model.addAttribute("my-events", events);
		return "my-events";
		
	}
	
	@RequestMapping(value = "/signed-events", method = RequestMethod.GET)
	public String getEventsByAttendee(Model model){
		
		User user = getCurrentUser();
		
		Set<Event> events = user.getAttendee();
		model.addAttribute("signed-events", events);
		return "signed-events";
		
	}
	
}
