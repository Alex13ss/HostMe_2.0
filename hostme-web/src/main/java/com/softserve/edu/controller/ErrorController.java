package com.softserve.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorController {
	@RequestMapping("error")
	public String errorPageRedirect(){
		return "error";
		
	}
	@RequestMapping(value="error-search",method=RequestMethod.GET)
	public String errorSearch(@ModelAttribute("search")String search,Model model){
		StringBuilder redirectBuilder = new StringBuilder();
		redirectBuilder.append("redirect:/").append(search);
		model.asMap().clear();
		return redirectBuilder.toString();
	}

}
