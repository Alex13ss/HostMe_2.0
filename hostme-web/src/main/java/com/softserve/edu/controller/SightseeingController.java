package com.softserve.edu.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softserve.edu.model.Sightseeing;
import com.softserve.edu.service.SightseeingService;

@Controller
public class SightseeingController {
	
	@Autowired
	private SightseeingService sightseeingService;

	@RequestMapping(value = "/sightseeings", method = RequestMethod.GET)
	public String hostingCreationShow(Model model) {
		Set<Sightseeing> sightseeings = sightseeingService.findAll();
		System.out.println(sightseeings.size());
		model.addAttribute("sightseeings", sightseeings);
		return "sightseeings";
	}
}
