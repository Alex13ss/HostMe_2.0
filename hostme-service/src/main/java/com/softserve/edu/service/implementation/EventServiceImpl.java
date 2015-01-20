package com.softserve.edu.service.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.softserve.edu.dto.EventDto;
import com.softserve.edu.model.City;
import com.softserve.edu.model.Event;
import com.softserve.edu.model.PriceCategory;
import com.softserve.edu.model.User;
import com.softserve.edu.model.routes.Place;
import com.softserve.edu.repositories.EventRepository;
import com.softserve.edu.repositories.routes.PlaceRepository;
import com.softserve.edu.service.EventService;
import com.softserve.edu.service.ProfileService;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	EventRepository eventRepository;
	@Autowired
	PlaceRepository placeRepository;
	@Autowired
	ProfileService profileService;

	public boolean haveEvent(int id) {
		return eventRepository.exists(id);
	}

	@Override
	@Transactional
	public void addEvent(Event event) {
		event.setOwner(profileService.getCurrentUser());
		eventRepository.save(event);
	}

	@Override
	@Transactional
	public void removeEvent(Integer id) {
		placeRepository.delete(id);
		eventRepository.delete(id);
	}

	@Override
	@Transactional
	public List<EventDto> getAllEvents() {
		List<EventDto> list = new ArrayList<EventDto>();
		for (Event event : eventRepository.findAll()) {
			list.add(new EventDto(event, placeRepository.findOne(event.getId())));
		}
		return list;
	}

	@Override
	@Transactional
	public EventDto getEvent(Integer id) {
		Place place = placeRepository.findOne(id);
		Event event = eventRepository.findOne(id);
		EventDto eventDto = new EventDto(event, place);
		return eventDto;
	}

	@Override
	@Transactional
	public List<EventDto> getEventByStartDate(Date date) {
		List<EventDto> list = new ArrayList<EventDto>();
		for (Event event : eventRepository.findByStartDate(date)) {
			list.add(new EventDto(event, placeRepository.findOne(event.getId())));
		}
		return list;
	}

	@Override
	@Transactional
	public List<EventDto> getEventByCity(City city) {
		List<EventDto> list = new ArrayList<EventDto>();
		for (Place place : placeRepository.findByCity(city)) {
			list.add(new EventDto(eventRepository.findOne(place.getId()), place));
		}
		return list;
	}

	@Override
	@Transactional
	public List<EventDto> getEventByOwner(User owner) {
		List<EventDto> list = new ArrayList<EventDto>();
		for (Place place : placeRepository.findByOwner(owner)) {
			list.add(new EventDto(eventRepository.findOne(place.getId()), place));
		}
		return list;
	}

	@Override
	@Transactional
	public List<EventDto> getEventByPriceCategory(PriceCategory priceCategory) {
		List<EventDto> list = new ArrayList<EventDto>();
		for (Place place : placeRepository.findByPriceCategory(priceCategory)) {
			list.add(new EventDto(eventRepository.findOne(place.getId()), place));
		}
		return list;
	}

	@Override
	@Transactional
	public List<EventDto> getEventByWebSite(String website) {
		List<EventDto> list = new ArrayList<EventDto>();
		for (Place place : placeRepository.findByWebsite(website)) {
			list.add(new EventDto(eventRepository.findOne(place.getId()), place));
		}
		return list;
	}

	@Override
	@Transactional
	public void saveEvent(Event event) {
		eventRepository.save(event);
	}

}
