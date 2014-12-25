package com.softserve.edu.dao;

import java.util.List;

import com.softserve.edu.entity.Request;
import com.softserve.edu.entity.exceptions.RequestAlreadySentException;

public interface RequestDao extends GenericDao<Request, Integer> {
	public boolean checkRequest(Request request) throws RequestAlreadySentException;
	
	public List<Request> getAllApprovedRequestsByHostingId(int hostingId);
	public List<Request> getMySentRequest(int userId);
	public List<Request> getMyReceivedRequest(int userId);
}
