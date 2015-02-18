package com.softserve.edu.service.implementation;

import com.softserve.edu.dao.HostingDao;
import com.softserve.edu.dao.RequestDao;
import com.softserve.edu.dao.UserDao;
import com.softserve.edu.dto.HostingDto;
import com.softserve.edu.model.Hosting;
import com.softserve.edu.model.Request;
import com.softserve.edu.model.User;
import com.softserve.edu.repositories.hosting.HostingRepository;
import com.softserve.edu.service.HostingService;
import com.softserve.edu.service.ImageService;
import com.softserve.edu.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
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
	private HostingRepository hostingRepository;

	@Autowired
	private ProfileService profileService;
	
	@Autowired
        private RequestDao requestDao;
	
	@Autowired
	private ImageService imageService;

	@Override
	@Transactional
	public void addHosting(Hosting hosting) {
		User user = profileService.getCurrentUser();
		hosting.setOwner(user);
		hostingDao.create(hosting);
	}

	@Override
	@Transactional
	public Hosting getHosting(int id) {
		return hostingDao.read(id);
	}

	public List<Hosting> getHostingLike(String request) {
		return hostingRepository.findByCityLike(request);
	}

	public List<HostingDto> getHostingDtoList(List<Hosting> hostings) {
		List<HostingDto> result = new ArrayList<>();
		for (Hosting hosting : hostings) {
			result.add(new HostingDto(hosting));
		}
		return result;
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

    @Override
    public List<Hosting> searchHosting(Specifications<Hosting> specifications) {
        return hostingRepository.findAll(specifications);
    }
}
