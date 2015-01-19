package com.softserve.edu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softserve.edu.dto.EventDto;
import com.softserve.edu.model.Event;
import com.softserve.edu.service.EventService;
import com.softserve.edu.service.ProfileService;

@Controller
public class EventContoller {

	@Autowired
	EventService eventService;
	@Autowired
	ProfileService profileService;

	@RequestMapping(value = "/events", method = RequestMethod.GET)
	public String showEvents(Model model) {
		List<EventDto> events = eventService.getAllEvents();
		model.addAttribute("events", events);
		return "events";
	}
	@RequestMapping(value = "/all-events", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<EventDto> getAllEvents() {
		List<EventDto> events = eventService.getAllEvents();
		return events;
	}
	@RequestMapping(value = "/event-creation", method = RequestMethod.GET)
	public String addEvent(Model model) {
		Event event = new Event();
		model.addAttribute("event", event);
		return "event-creation";
	}
	/*@RequestMapping(value = "/eventAdd", method = RequestMethod.POST)
	public String addContact(@ModelAttribute("event") Event event,
			BindingResult result) {
		eventService.saveEvent(event);
		return "redirect:/events";
	}*/

}
