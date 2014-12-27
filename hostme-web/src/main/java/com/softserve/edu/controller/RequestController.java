package com.softserve.edu.controller;

import com.softserve.edu.model.Hosting;
import com.softserve.edu.model.Request;
import com.softserve.edu.model.Status;
import com.softserve.edu.model.User;
import com.softserve.edu.service.HostingService;
import com.softserve.edu.service.ProfileService;
import com.softserve.edu.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@Controller
public class RequestController {
	@Autowired
	private RequestService requestService;
	@Autowired
	private ProfileService profileService;
	@Autowired
	private HostingService hostingService;

	@RequestMapping(value = "/request", method = RequestMethod.GET)
	public String showRequest() {
		return "request-history";
	}

	@RequestMapping(value = "/dismiss-request", method = RequestMethod.GET)
	public @ResponseBody boolean removeRequest() {

		return false;

	}

	@RequestMapping(value = "/request-update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Request updateRequestStatus(
			@RequestBody Request request) {
		requestService.update(request);
		return request;

	}

	@RequestMapping(value = "/request-sent-history", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Request> dataTableSent() {
		User user = profileService.getUserByLogin(SecurityContextHolder
				.getContext().getAuthentication().getName());
		List<Request> requests = requestService.getMySentRequest(user
				.getUserId());
		return requests;
	}

	@RequestMapping(value = "/request-obtain-history", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Request> dataTableObtain() {
		User user = profileService.getUserByLogin(SecurityContextHolder
				.getContext().getAuthentication().getName());
		List<Request> requests = requestService.getMyReceivedRequest(user
				.getUserId());
		return requests;
	}

	@RequestMapping(value = "/request", method = RequestMethod.POST)
	public String sendRequest(@ModelAttribute("request") Request request,
			BindingResult bindingResult, Model model) {

		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTimeInMillis(Long.parseLong((String) bindingResult
				.getFieldValue("beginDate")));
		request.setBeginDate(calendar1);
		calendar2.setTimeInMillis(Long.parseLong((String) bindingResult
				.getFieldValue("endDate")));
		request.setEndDate(calendar2);
		request.setStatus(Status.PENDING);
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		String currentPrincipalName = authentication.getName();
		User user = profileService.getUserByLogin(currentPrincipalName);
		request.setAuthor(user);
		int id = Integer.parseInt((String) bindingResult
				.getFieldValue("hosting"));
		Hosting hosting = hostingService.getHosting(id);
		request.setHosting(hosting);
		request.setReceiver(hosting.getOwner());
		requestService.createRequest(request);

		boolean requestSent = true;
		model.addAttribute("requestSent", requestSent);
		StringBuilder returnString = new StringBuilder(
				"redirect:/hosting?hostingId=");
		return returnString.append(hosting.getHostingId()).append("&userId=")
				.append(user.getUserId()).toString();

	}
}
