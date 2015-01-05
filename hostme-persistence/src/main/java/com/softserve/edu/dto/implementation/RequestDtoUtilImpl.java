package com.softserve.edu.dto.implementation;

import com.softserve.edu.dto.RequestDtoUtil;
import com.softserve.edu.model.Hosting;
import com.softserve.edu.model.Request;
import com.softserve.edu.model.Status;
import com.softserve.edu.model.exceptions.RequestCannotSendException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

//import com.softserve.edu.service.ProfileService;

@Service
public class RequestDtoUtilImpl implements RequestDtoUtil {

//	@Autowired
//	private ProfileService profileService;

	@Override
	public Request formRequestModel(long endDate, long beginDate,
			Status status, int hostingId, String login, String notes) throws RequestCannotSendException {
		Request request = new Request();
		Calendar begin= Calendar.getInstance();
		Calendar end= Calendar.getInstance();
		begin.setTimeInMillis(beginDate);
		request.setBeginDate(begin);
		end.setTimeInMillis(endDate);
		request.setEndDate(end);
//		User user = profileService.getUserByLogin(login);
//		request.setAuthor(user);
		Hosting hosting = new Hosting();
		hosting.setHostingId(hostingId);
		request.setHosting(hosting);
		request.setStatus(status);
		return request;
	}



}
