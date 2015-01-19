package com.softserve.edu.service;

import java.util.Date;
import java.util.List;

import com.softserve.edu.dto.EventDto;
import com.softserve.edu.model.City;
import com.softserve.edu.model.Event;
import com.softserve.edu.model.PriceCategory;
import com.softserve.edu.model.User;
import com.softserve.edu.model.routes.Place;

public interface EventService {

//	public void addEvent(Event event, Place place);
//	
//	public void saveEvent(Event event);
//
//	public void updateEvent(Event event);
//
//	public void removeEvent(Integer id);
//	
	public List<EventDto> getAllEvents();
//
//	public EventDto getEvent(Integer id);
//
//	public EventDto getEventByStartDate(Date date);
//
//	public List<EventDto> getEventByCity(City city);
//
//	public List<EventDto> getEventByOwner(User owner);
//
//	public List<EventDto> getEventByPriceCategory(PriceCategory priceCategory);
//
//	public List<EventDto> getEventByWebSite(String website);

}
