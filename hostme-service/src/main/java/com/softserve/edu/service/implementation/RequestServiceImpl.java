package com.softserve.edu.service.implementation;

import com.softserve.edu.dao.RequestDao;
import com.softserve.edu.model.Request;
import com.softserve.edu.model.exceptions.RequestAlreadySentException;
import com.softserve.edu.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {
	@Autowired
	private RequestDao requestDao;

	@Override
	@Transactional
	public void createRequest(Request request) {
		requestDao.create(request);
	}

	@Override
	@Transactional
	public void checkRequest(Request request)
			throws RequestAlreadySentException {
		requestDao.checkRequest(request);
	}

	@Override
	@Transactional
	public List<Request> getAll() {
		return requestDao.getAll();
	}

	@Override
	@Transactional
	public List<Request> getMySentRequest(int userId) {
		return requestDao.getMySentRequest(userId);
	}
	@Override
	@Transactional
	public List<Request> getMyReceivedRequest(int userId) {
		return requestDao.getMyReceivedRequest(userId);
	}

	@Override
	@Transactional
	public boolean removeRequest() {
		
		return false;
	}

	@Override
	@Transactional
	public void update(Request request) {
		requestDao.update(request);
	}

	@Override
	@Transactional
	public Request getRequest(int id) {
		return requestDao.read(id);
	}
}
