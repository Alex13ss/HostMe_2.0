package com.softserve.edu.controller;

import com.softserve.edu.dto.RequestDtoUtil;
import com.softserve.edu.entity.Hosting;
import com.softserve.edu.entity.Request;
import com.softserve.edu.entity.User;
import com.softserve.edu.entity.exceptions.RequestAlreadySentException;
import com.softserve.edu.service.HostingService;
import com.softserve.edu.service.ProfileService;
import com.softserve.edu.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestRequestController {
	@Autowired
	private RequestService requestService;
	@Autowired
	private RequestDtoUtil requestDtoUtil;
	@Autowired
	private ProfileService profileService;
	@Autowired
	private HostingService hostingService;

	@RequestMapping(value = "/response", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Boolean processUserRequest(@RequestBody Request request) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		String login = authentication.getName();
		try {
			User user=profileService.getUserByLogin(login);
			Hosting hosting=hostingService.getHosting(request.getHosting().getHostingId());
			request.setHosting(hosting);
			request.setAuthor(user);
			request.setReceiver(hosting.getOwner());
			requestService.checkRequest(request);
			requestService.createRequest(request);
			Thread.sleep(1000);

		} catch (RequestAlreadySentException e) {
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return true;

	}

}
