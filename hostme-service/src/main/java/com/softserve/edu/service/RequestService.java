package com.softserve.edu.service;

import java.util.List;

import com.softserve.edu.entity.exceptions.RequestAlreadySentException;
import com.softserve.edu.entity.Request;

public interface RequestService {

	public Request getRequest(int id);
	public boolean removeRequest();

	public void update(Request request);

	public List<Request> getAll();

	public List<Request> getMySentRequest(int userId);
	public List<Request> getMyReceivedRequest(int userId);

	public void createRequest(Request request);

	public void checkRequest(Request request)
			throws RequestAlreadySentException;

}
