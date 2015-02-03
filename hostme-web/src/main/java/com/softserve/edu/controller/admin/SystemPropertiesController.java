package com.softserve.edu.controller.admin;

import java.util.List;

import com.softserve.edu.model.Event;
import com.softserve.edu.model.SystemProperties;
import com.softserve.edu.model.User;
import com.softserve.edu.service.SystemPropertiesService;
import com.softserve.edu.service.UserService;
import com.softserve.edu.service.implementation.SystemPropertiesServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SystemPropertiesController {

	@Autowired
	SystemPropertiesService systemPropertiesService;

	@RequestMapping(value = "/systemProperties", method = RequestMethod.GET)
    public String systemProperties(Model model) {
		model.addAttribute("systemproperties", systemPropertiesService.getSystemProperties() );
        return "systemProperties";
    }
	
	@RequestMapping(value = "/all-systemProperties", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody SystemProperties getSystemProperties() {
		return systemPropertiesService.getSystemProperties();
	}
	
	@RequestMapping(value = "/systemProperties", method = RequestMethod.POST)
	public String editSystemProperty(@ModelAttribute("systemproperties") final SystemProperties systemproperties) {
		systemPropertiesService.updateSystemProperties(systemproperties);
		return "systemProperties";
	}
	
	
}
