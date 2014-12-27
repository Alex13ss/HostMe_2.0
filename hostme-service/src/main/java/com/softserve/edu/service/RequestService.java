package com.softserve.edu.service;

import com.softserve.edu.model.Request;
import com.softserve.edu.model.exceptions.RequestAlreadySentException;

import java.util.List;

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
