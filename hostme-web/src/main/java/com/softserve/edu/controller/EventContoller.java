package com.softserve.edu.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserve.edu.model.Event;
import com.softserve.edu.service.CityService;
import com.softserve.edu.service.CountryService;
import com.softserve.edu.service.EventService;

@Controller
public class EventContoller {
	
	@Autowired
	EventService eventService;
	
	
	
	@RequestMapping(value = "/events", method = RequestMethod.GET)
	public String getEvents(Model model){
		List<Event> events = eventService.getAllEvents();
		model.addAttribute("events", events);
		return "events";
		
	}
}
