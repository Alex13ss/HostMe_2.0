package com.softserve.edu.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.dao.HostingDao;
import com.softserve.edu.dao.UserDao;
import com.softserve.edu.entity.Gender;
import com.softserve.edu.entity.Hosting;
import com.softserve.edu.service.HostingService;

@Service
public class HostingServiceImpl implements HostingService {

	@Autowired
	private HostingDao hostingDao;

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public void addHosting(Hosting hosting) {
		hostingDao.create(hosting);
	}

	@Override
	@Transactional
	public List<Hosting> getUserHostings() {
		return hostingDao.getUserHostings();
	}


    @Override
    @Transactional
    public Hosting getHosting(int id) {
        return hostingDao.read(id);
    }

}
