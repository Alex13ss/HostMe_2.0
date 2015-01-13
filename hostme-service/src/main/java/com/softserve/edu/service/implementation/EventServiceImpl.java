package com.softserve.edu.service.implementation;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.model.City;
import com.softserve.edu.model.Event;
import com.softserve.edu.model.PriceCategory;
import com.softserve.edu.model.User;
import com.softserve.edu.repositories.EventRepository;
import com.softserve.edu.service.EventService;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	EventRepository eventRepository;

	@Override
	@Transactional
	public Event addEvent(Event event) {

		return eventRepository.save(event);
	}

	@Override
	@Transactional
	public void updateEvent(Event event) {
		
		Event update = eventRepository.findOne(event.getId());
		
		if(update != null) {
			update.setStartDate(event.getStartDate());
			update.setOwner(event.getOwner());
			update.setCity(event.getCity());
		}

	}

	@Override
	@Transactional
	public void removeEvent(Integer id) {

		eventRepository.delete(id);
	}

	@Override
	@Transactional
	public List<Event> getAllEvents() {
		List<Event> list = (List<Event>) eventRepository.findAll();
		return list;
	}

	@Override
	@Transactional
	public Event getEvent(Integer id) {
		return eventRepository.findOne(id);
	}

	@Override
	@Transactional
	public Event getEventByStartDate(Date date) {
		return (Event) eventRepository.findByStartDate(date);
	}

	@Override
	@Transactional
	public List<Event> getEventByCity(City city) {
		return eventRepository.findByCity(city);
	}

	@Override
	@Transactional
	public List<Event> getEventByOwner(User owner) {
		return eventRepository.findByOwner(owner);
	}

	@Override
	@Transactional
	public List<Event> getEventByPriceCategory(PriceCategory priceCategory) {
		return eventRepository.findByPriceCategory(priceCategory);
	}

	@Override
	@Transactional
	public List<Event> getEventByWebSite(String website) {
		return eventRepository.findByWebsite(website);
	}

}
