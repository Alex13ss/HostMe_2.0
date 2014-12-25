package com.softserve.edu.controller;

import com.google.gson.Gson;
import com.softserve.edu.entity.Feedback;
import com.softserve.edu.entity.Gender;
import com.softserve.edu.entity.Hosting;
import com.softserve.edu.entity.Request;
import com.softserve.edu.service.HostingService;
import com.softserve.edu.service.ImageService;
import com.softserve.edu.service.ProfileService;
import com.softserve.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HostingController {
	@Autowired
	private HostingService hostingService;
	@Autowired
	private UserService userService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private ProfileService profileService;

	@RequestMapping(value = "/hosting-creation", method = RequestMethod.GET)
	public String hostingCreationShow(Model model) {
		Hosting hosting = new Hosting();
		hosting.setGender(Gender.UNSPECIFIED);
		hosting.setChildren(true);
		hosting.setFamily(true);
		hosting.setSmoking(true);
		hosting.setPets(true);
		model.addAttribute("hosting", hosting);
		return "hosting-creation";
	}

	@RequestMapping(value = "/hosting-creation", method = RequestMethod.POST)
	public String addHosting(@ModelAttribute("hosting") Hosting hosting,
			@RequestParam("file") MultipartFile[] files) {

		hostingService.addHosting(hosting);
		imageService.addImages(files, hosting);

		return "redirect:/profile";
	}

	@RequestMapping(value = "/hosting-editing", method = RequestMethod.GET)
	public String hostingEditingShow(
			@RequestParam(value = "hostingId") Integer hostingId,
			@RequestParam(value = "userId") Integer userId, Model model) {
		Hosting hosting = hostingService.getHosting(hostingId);
		model.addAttribute("hosting", hosting);
		model.addAttribute("userId", userId);
		return "hosting-editing";
	}

	@RequestMapping(value = "/hosting-editing", method = RequestMethod.POST)
	public String editHosting(@ModelAttribute("hosting") Hosting hosting,
			@RequestParam("file") MultipartFile[] files,
			@RequestParam("hostingId") Integer hostingId,
			@RequestParam("userId") Integer userId) {
		hosting.setHostingId(hostingId);
		hosting.setOwner(userService.getUser(userId));
		hostingService.updateHosting(hosting);
		imageService.addImages(files, hosting);
		return "redirect:/profile";
	}

	@RequestMapping(value = "/hosting-delete", method=RequestMethod.GET)
	public String deleteHosting(@RequestParam(value="hostingId") Integer hostingId){
		hostingService.deleteHosting(hostingId);
		return "redirect:/profile";
	}
	
	@RequestMapping(value = "/hosting", method = RequestMethod.GET)
	public String hostelShow(@RequestParam(value = "hostingId") int hostingId,
			Model model) {
		Hosting hosting = hostingService.getHosting(hostingId);
		model.addAttribute("hosting", hosting);
		Request request = new Request();
		model.addAttribute("request", request);
		Feedback feedback = new Feedback();
		model.addAttribute("feedback", feedback);
		// steps to check the hosting availability

		/*
		 * Calendar cal = new GregorianCalendar(); SimpleDateFormat dateFormat =
		 * new SimpleDateFormat("yyyy-M-d");
		 * dateFormat.setTimeZone(cal.getTimeZone());
		 * System.out.println(dateFormat.format(cal.getTime()));
		 * 
		 * String disabledDate = dateFormat.format(cal.getTime());
		 * System.out.println(disabledDate);
		 * 
		 * model.addAttribute("disabledDate", disabledDate.toString());
		 * 
		 * Date today = new Date(); Date dateNew = new Date(today.getTime() + 24
		 * * 60 * 60 * 1000); System.out.println(dateNew);
		 * model.addAttribute("dateNew", dateNew);
		 */

		String nonAvailableDatesJson = new Gson().toJson(hostingService
				.getNonAvailableDates(hostingId));
		model.addAttribute("nonAvailableDatesJson", nonAvailableDatesJson);

		// model.addAttribute("disabledDate",hostingService.getNonAvailableDates(hostingId));
		System.out.println(hostingId);

		return "hosting";
	}

}