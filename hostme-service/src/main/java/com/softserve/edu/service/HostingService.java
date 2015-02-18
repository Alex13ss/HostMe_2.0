package com.softserve.edu.service;

import com.softserve.edu.dto.HostingDto;
import com.softserve.edu.model.Hosting;
import org.springframework.data.jpa.domain.Specifications;

import java.util.ArrayList;
import java.util.List;

public interface HostingService {

	public void addHosting(Hosting hosting);

	public Hosting getHosting(int id);
	
	public ArrayList<String> getNonAvailableDates(int hostingId);

	public void updateHosting(Hosting hosting);

	public void deleteHosting(Integer hostingId);

	public List<HostingDto> getHostingDtoList(List<Hosting> hostings);

	public List<Hosting> getHostingLike(String request);

    public List<Hosting> searchHosting(Specifications<Hosting> specifications);
}
