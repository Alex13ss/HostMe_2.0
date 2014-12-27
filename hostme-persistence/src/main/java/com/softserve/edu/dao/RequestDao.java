package com.softserve.edu.dao;

import com.softserve.edu.model.Request;
import com.softserve.edu.model.exceptions.RequestAlreadySentException;

import java.util.List;

public interface RequestDao extends GenericDao<Request, Integer> {
	public boolean checkRequest(Request request) throws RequestAlreadySentException;
	
	public List<Request> getAllApprovedRequestsByHostingId(int hostingId);
	public List<Request> getMySentRequest(int userId);
	public List<Request> getMyReceivedRequest(int userId);
}
