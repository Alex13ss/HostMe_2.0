package com.softserve.edu.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specifications;

import com.softserve.edu.dto.EventDto;
import com.softserve.edu.model.Event;
import com.softserve.edu.model.User;


public interface EventService {

	public boolean haveEvent(int id);

	public void removeEvent(Event event);

	public List<EventDto> getAllEvents();

	public List<EventDto> getAllEventsPaging(Integer page, Integer size, String orderBy, String orderType);

	public List<EventDto> getEventsDtoList(List<Event> events);

	public EventDto getEvent(Integer id);

	public Event findOne(Integer id);

	public List<Event> searchEvent(Specifications<Event> specifications);

	public List<EventDto> getEventByOwner(Integer page, Integer size, String orderBy, String orderType);
	
	public List<EventDto> getByAttendee(Integer page, Integer size, String orderBy, String orderType);

	public void saveEvent(Event event);
	
	void addEvent(Event event, String priceCategory, String city);

	public void updateEvent(Event event, String city, String priceCategory);
	
	public boolean checkEventOwner(EventDto event, User user);
	
	public boolean checkEventSubscribed(EventDto event, User user);
	
	public void addAttendee(User user, Integer id);
	
	public Long getPageCount(Long size, String sender);
	
	public void leaveEvent(User user, Integer id);
}

