package com.softserve.edu.service;

import java.util.ArrayList;

import com.softserve.edu.entity.Hosting;

public interface HostingService {

	public void addHosting(Hosting hosting);

	public Hosting getHosting(int id);
	
	public ArrayList<String> getNonAvailableDates(int hostingId);

	public void updateHosting(Hosting hosting);

	public void deleteHosting(Integer hostingId);

}
