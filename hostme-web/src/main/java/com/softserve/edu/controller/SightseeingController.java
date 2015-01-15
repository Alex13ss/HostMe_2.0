package com.softserve.edu.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.softserve.edu.model.Sightseeing;
import com.softserve.edu.service.SightseeingService;

@Controller
public class SightseeingController {

	@Autowired
	private SightseeingService sightseeingService;

	@RequestMapping(value = "/sightseeings", method = RequestMethod.GET)
	public String showSightseeings(Model model) {
		Set<Sightseeing> sightseeings = sightseeingService.findAll();
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

	@RequestMapping("/sightseeing/delete/{id}")
	public String deleteSightseeing(@PathVariable("id") Integer id) {
		Sightseeing sightseeing = sightseeingService.findOne(id);
		sightseeingService.deleteSightseeing(sightseeing);
		return "redirect:/sightseeings";
	}
	
	@RequestMapping(value = "/update-sightseeing", method = RequestMethod.GET)
	public String editSightseeing(Model model) {
		Sightseeing sightseeings = new Sightseeing();
		model.addAttribute("sightseeings", sightseeings);
		return "create-sightseeing";
	}
	
//	@RequestMapping("/sightseeingEdit/{id}")
//	public String changeSightseeing(@PathVariable("id") Integer id) {
//		Sightseeing sightseeing = sightseeingService.findOne(id);
//		sightseeingService.saveSightseeing(sightseeing);
//		return "redirect:/sightseeings";
//	}

	@RequestMapping(value = "/sightseeingAdd", method = RequestMethod.POST)
	public String addSightseeing(@ModelAttribute("sightseeing") Sightseeing sightseeing,
			BindingResult result) {
		sightseeingService.saveSightseeing(sightseeing);
		return "redirect:/create-sightseeing";
	}
}
