package com.softserve.edu.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.softserve.edu.dto.EventDto;
import com.softserve.edu.model.City;
import com.softserve.edu.model.Country;
import com.softserve.edu.model.Event;
import com.softserve.edu.model.PriceCategory;
import com.softserve.edu.model.User;
import com.softserve.edu.service.CityService;
import com.softserve.edu.service.CountryService;
import com.softserve.edu.service.EventService;
import com.softserve.edu.service.PriceCategoryService;
import com.softserve.edu.service.ProfileService;

@Controller
public class EventContoller {

	@Autowired
	EventService eventService;
	@Autowired
	ProfileService profileService;
	@Autowired
	private CountryService countryService;
	@Autowired
	private CityService cityService;
	@Autowired
	private PriceCategoryService priceCategoryService;

	@RequestMapping(value = "/events", method = RequestMethod.GET)
	public String showEvents(Model model) {
		List<EventDto> events = eventService.getAllEvents();
		Event event = new Event();
		List<Country> countries = countryService.getAllCountry();
		List<City> cities = cityService.getAllCity();
		List<PriceCategory> priceCategories = priceCategoryService.getAllPriceCategory();
		model.addAttribute("events", events);
		model.addAttribute("event", event);
		model.addAttribute("countries", countries);
		model.addAttribute("cities", cities);
		model.addAttribute("priceCategories", priceCategories);
		return "events";
	}

	@RequestMapping(value = "/events", method = RequestMethod.POST)
	public String addEvent(Model model, @ModelAttribute("event") @Valid Event event, final BindingResult result,
			RedirectAttributes redirectAttributes, HttpSession httpSession) {
		User user = profileService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
		event.setOwner(user);
		eventService.saveEvent(event);
		return "redirect:/events";
	}

	@RequestMapping(value = "/all-events", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<EventDto> getAllEvents() {
		List<EventDto> events = eventService.getAllEvents();
		return events;
	}
	
//	@RequestMapping(value = "/all-events/{pageNumber}", method = RequestMethod.GET, produces = "application/json")
//	public String getAllEvents(@PathVariable Integer pageNumber, Model model) {
//		Page<EventDto> events = eventService.getAllEvents(pageNumber);
//		
//		int current = events.getNumber() + 1;
//	    int begin = Math.max(1, current - 5);
//	    int end = Math.min(begin + 10, events.getTotalPages());
//
//	    model.addAttribute("all_events", events);
//	    model.addAttribute("beginIndex", begin);
//	    model.addAttribute("endIndex", end);
//	    model.addAttribute("currentIndex", current);
//		return all_events;
//	}

	@RequestMapping(value = "/my-events", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<EventDto> getMyEvents() {
		User user = profileService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
		List<EventDto> events = eventService.getEventByOwner(user);
		return events;
	}

	@RequestMapping(value = "/event", method = RequestMethod.GET)
	public String showEvent(@RequestParam("id") Integer id, Model model) {
		EventDto eventDto = eventService.getEvent(id);
		List<Country> countries = countryService.getAllCountry();
		List<City> cities = cityService.getAllCity();
		List<PriceCategory> priceCategories = priceCategoryService.getAllPriceCategory();
		model.addAttribute("event", eventDto);
		model.addAttribute("countries", countries);
		model.addAttribute("cities", cities);
		model.addAttribute("priceCategories", priceCategories);
		return "event";
	}

	@RequestMapping(value = "/event", method = RequestMethod.POST)
	public String editEvent(@ModelAttribute("event") final Event event, RedirectAttributes redirectAttributes) {
		String priceCategory = event.getPriceCategory().getPriceCategory();
		String city = event.getCity().getCity();
		event.setOwner(profileService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName()));
		eventService.updateEvent(event, city, priceCategory);
		redirectAttributes.addAttribute("id", event.getId()).addFlashAttribute("eventEdited", true);
		return "redirect:/event?id={id}";
	}

	@RequestMapping("/event/delete/{id}")
	public String deleteEvent(@PathVariable("id") Integer id) {
		Event event = eventService.findOne(id);
		eventService.removeEvent(event);
		return "redirect:/events";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		String datePattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

}
