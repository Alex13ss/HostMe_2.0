package com.softserve.edu.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserve.edu.model.Conversation;
import com.softserve.edu.model.Gender;
import com.softserve.edu.model.Hosting;
import com.softserve.edu.model.Sightseeing;
import com.softserve.edu.service.SightseeingService;

@Controller
public class SightseeingController {

	@Autowired
	private SightseeingService sightseeingService;

	@RequestMapping(value = "/sightseeings", method = RequestMethod.GET)
	public String showSightseeings(Model model) {
		Set<Sightseeing> sightseeings = sightseeingService.findAll();
		System.out.println(sightseeings.size());
		model.addAttribute("sightseeings", sightseeings);
		return "sightseeings";
	}

	@RequestMapping(value = "/create-sightseeing", method = RequestMethod.GET)
	public String addSightseeing(Model model) {
		Sightseeing sightseeings = new Sightseeing();

		model.addAttribute("sightseeings", sightseeings);
		return "create-sightseeing";
	}

	@RequestMapping(value = "/sightseeing", method = RequestMethod.GET)
	public String showConversation(@RequestParam("id") int id, Model model) {
		Sightseeing sightseeing = sightseeingService.findOne(id);
		model.addAttribute("sightseeing", sightseeing);
		return "sightseeing";
	}
}
