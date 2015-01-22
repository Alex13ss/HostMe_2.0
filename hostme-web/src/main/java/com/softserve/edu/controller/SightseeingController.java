package com.softserve.edu.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.softserve.edu.dto.EventDto;
import com.softserve.edu.dto.SightseeingDto;
import com.softserve.edu.model.Sightseeing;
import com.softserve.edu.service.SightseeingService;

@Controller
public class SightseeingController {

	@Autowired
	private SightseeingService sightseeingService;

	@RequestMapping(value = "/sightseeings", method = RequestMethod.GET)
	public String showSightseeings(Model model) {
		List<SightseeingDto> sightseeings = sightseeingService
				.getAllSightseeings();
		model.addAttribute("sightseeings", sightseeings);
		return "sightseeings";
	}

	@RequestMapping(value = "/all-sightseeings", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<SightseeingDto> getAllSightseeings() {
		List<SightseeingDto> sightseeings = sightseeingService
				.getAllSightseeings();
		return sightseeings;
	}

//	@RequestMapping(value = "/all-sightseeings", method = RequestMethod.GET, produces = "application/json")
//	public @ResponseBody List<SightseeingDto> getFavouriteSightseeings() {
//		List<SightseeingDto> sightseeings = sightseeingService
//				.getAllSightseeings();
//		return sightseeings;
//	}

	@RequestMapping(value = "/sightseeing", method = RequestMethod.GET)
	public String showSightseeing(@RequestParam("id") int id, Model model) {
		Sightseeing sightseeing = sightseeingService.findOne(id);
		model.addAttribute("sightseeing", sightseeing);
		return "sightseeing";
	}

	@RequestMapping(value = "/create-sightseeing", method = RequestMethod.GET)
	public String createSightseeing(Model model) {
		Sightseeing sightseeings = new Sightseeing();
		model.addAttribute("sightseeings", sightseeings);
		return "create-sightseeing";
	}

	@RequestMapping("/sightseeing/delete/{id}")
	public String deleteSightseeing(@PathVariable("id") Integer id) {
		Sightseeing sightseeing = sightseeingService.findOne(id);
		sightseeingService.deleteSightseeing(sightseeing);
		return "redirect:/sightseeings";
	}

	@RequestMapping(value = "/update-sightseeing", method = RequestMethod.GET)
	public String editSightseeing(@RequestParam("id") int id, Model model) {
		model.addAttribute("sightseeing", new Sightseeing());
		return "update-sightseeing";
	}

	@RequestMapping(value = "/sightseeingAdd", method = RequestMethod.POST)
	public String addSightseeing(
			@ModelAttribute("sightseeing") Sightseeing sightseeing,
			BindingResult result) {
		sightseeingService.saveSightseeing(sightseeing);
		return "redirect:/create-sightseeing";
	}

	@RequestMapping("/sightseeingEdit")
	public String changeSightseeing(
			@ModelAttribute("sightseeing") Sightseeing sightseeing,
			BindingResult result) {
		sightseeingService.saveSightseeing(sightseeing);
		return "redirect:/sightseeings";
	}
}
