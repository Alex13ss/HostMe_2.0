package com.softserve.edu.controller.admin;

import java.util.List;

import com.softserve.edu.model.SystemProperties;
import com.softserve.edu.service.SystemPropertiesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class SystemPropertiesController {

	@Autowired
	SystemPropertiesService systemPropertiesService;

	@RequestMapping(value = "/systemProperties", method = RequestMethod.GET)
    public String systemProperties(Model model) {
		model.addAttribute("systemproperties", new SystemProperties() );
        return "systemProperties";
    }
	
	@RequestMapping(value = "/all-systemProperties", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<SystemProperties> getSystemProperties() {
		return systemPropertiesService.getAllSystemProperties();
	}
	
	@RequestMapping(value = "/systemproperty", method = RequestMethod.GET)
	public String showSystemProperty(@RequestParam("id") int id, Model model) {
		SystemProperties systemproperty = systemPropertiesService.getSystemProperty(id);
		model.addAttribute("systemproperty", systemproperty);
		return "systemproperty";
	}	
	@RequestMapping(value = "/systemproperty", method = RequestMethod.POST)
	public String editSystemProperty(@ModelAttribute("systemproperty") final SystemProperties systemproperty,RedirectAttributes redirectAttributes) {
		systemPropertiesService.saveSystemProperties(systemproperty);
		redirectAttributes.addAttribute("id", systemproperty.getPropertyId()).addFlashAttribute(
				"systemPropertyEdited", true);
		return "redirect:/systemproperty?id={id}";
	}
	
	@RequestMapping(value = "/systemProperties", method = RequestMethod.POST)
	public String addSystemProperties(Model model, @ModelAttribute("systemproperties") SystemProperties systemProperties) {
		systemPropertiesService.saveSystemProperties(systemProperties);
		return "redirect:/systemProperties";
	}

	
	
}
