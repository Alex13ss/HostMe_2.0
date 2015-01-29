package com.softserve.edu.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.softserve.edu.dto.EventDto;
import com.softserve.edu.model.City;
import com.softserve.edu.model.Event;
import com.softserve.edu.model.PriceCategory;
import com.softserve.edu.model.User;

public interface EventService {

	public boolean haveEvent(int id);

	public void addEvent(Event event);

	public void removeEvent(Event event);

	public List<EventDto> getAllEvents();

	public List<EventDto> getEventsDtoList(List<Event> events);

	public EventDto getEvent(Integer id);
	
	public Event findOne(Integer id);

	public List<EventDto> getEventByStartDate(Date date);

	public List<Event> getEventsLike(String search);

	public List<EventDto> getEventByCity(City city);

	public List<EventDto> getEventByOwner(User owner);

	public List<EventDto> getEventByPriceCategory(PriceCategory priceCategory);

	public List<EventDto> getEventByWebSite(String website);
	
	public void saveEvent(Event event);
	
	public Event convertEventDtoToEvent(EventDto eventDto);
	
	public void updateEvent(Event event, String city, String priceCategory);
	//public Page<EventDto> getAllEvents(Integer pageNumber);
}
