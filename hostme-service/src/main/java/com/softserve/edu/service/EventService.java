package com.softserve.edu.service;

import java.util.Date;
import java.util.List;

import com.softserve.edu.model.City;
import com.softserve.edu.model.Event;
import com.softserve.edu.model.PriceCategory;
import com.softserve.edu.model.User;

public interface EventService {

	public Event addEvent(Event event);

	public void updateEvent(Event event);

	public void removeEvent(Integer id);
	
	public List<Event> getAllEvents();

	public Event getEvent(Integer id);

	public Event getEventByStartDate(Date date);

	public List<Event> getEventByCity(City city);

	public List<Event> getEventByOwner(User owner);

	public List<Event> getEventByPriceCategory(PriceCategory priceCategory);

	public List<Event> getEventByWebSite(String website);

}
