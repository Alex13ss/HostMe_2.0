package com.softserve.edu.service.implementation;

import com.softserve.edu.dao.HostingDao;
import com.softserve.edu.dao.RequestDao;
import com.softserve.edu.dao.UserDao;
import com.softserve.edu.model.Hosting;
import com.softserve.edu.model.Request;
import com.softserve.edu.service.HostingService;
import com.softserve.edu.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class HostingServiceImpl implements HostingService {

	@Autowired
	private HostingDao hostingDao;

	@Autowired
	private UserDao userDao;
	
	@Autowired
        private RequestDao requestDao;
	
	@Autowired
	private ImageService imageService;

	@Override
	@Transactional
	public void addHosting(Hosting hosting) {
		String login = SecurityContextHolder.getContext().getAuthentication()
				.getName();
		hosting.setOwner(userDao.getUserByLogin(login));
		hostingDao.create(hosting);
	}

	@Override
	@Transactional
	public Hosting getHosting(int id) {
		return hostingDao.read(id);
	}
	
	@Override
    @Transactional
	public ArrayList<String> getNonAvailableDates(int hostingId) {
	    List<Request> requests = requestDao.getAllApprovedRequestsByHostingId(hostingId);
	    
	    ArrayList<String> nonAvailableDates = new ArrayList<String>();
	    
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");	        
	    
	    for (Request item : requests) {
	        Date d1 = item.getBeginDate().getTime();
	        Date d2 = item.getEndDate().getTime();
	        
	        Iterator<Date> i = new DateIterator(d1, d2);
                while(i.hasNext())
                {
                    nonAvailableDates.add(dateFormat.format(i.next()));
                }
            }
	    
	    return nonAvailableDates;
	}

	@Override
	@Transactional
	public void updateHosting(Hosting hosting) {
		hostingDao.update(hosting);
	}

	@Override
	@Transactional
	public void deleteHosting(Integer hostingId) {
		Hosting hosting = hostingDao.read(hostingId);
		hostingDao.delete(hosting);
		imageService.deleteImagesForHosting(hosting);
	}

}
