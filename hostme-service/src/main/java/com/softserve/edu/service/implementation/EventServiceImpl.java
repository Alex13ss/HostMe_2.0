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

	public List<EventDto> getEventsDtoList(List<Event> events) {
		List<EventDto> result = new ArrayList<>();
		for (Event event : events) {
			result.add(new EventDto(event));
		}
		return result;
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
	public Event findOne(Integer id) {
		Event event = eventRepository.findOne(id);
		return event;
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

	public List<Event> getEventsLike(String searchingName) {
		return eventRepository.findByNameContaining(searchingName);
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

	@Override
	@Transactional
	public Event convertEventDtoToEvent(EventDto eventDto) {
		Event event = eventRepository.findOne(eventDto.getId());
		event.setAddress(eventDto.getAddress());
		event.setCity(eventDto.getCity());
		event.setName(eventDto.getName());
		event.setStartDate(eventDto.getStartDate());
		event.setEndDate(eventDto.getEndDate());
		event.setComment(eventDto.getComment());
		event.setWebsite(eventDto.getWebsite());
		event.setPriceCategory(eventDto.getPriceCategory());
		event.setOwner(eventDto.getOwner());
		event.setImage(eventDto.getImage());
		event.setAttendee(eventDto.getAttendee());
		return event;
	}

	@Override
	@Transactional
	public void updateEvent(Event event) {

		Event transEvent = eventRepository.findOne(event.getId());
		Place transPlace = placeRepository.findOne(event.getId());

		if (event.getName() != null) {
			transPlace.setName(event.getName());
		} else {
			transPlace.setName(transPlace.getName());
		}
		if (event.getDescription() != null) {
			transPlace.setDescription(event.getDescription());
		} else {
			transPlace.setDescription(transPlace.getDescription());
		}
		if (event.getComment() != null) {
			transPlace.setComment(event.getComment());
		} else {
			transPlace.setComment(transPlace.getComment());
		}
		if (event.getWebsite() != null) {
			transPlace.setWebsite(event.getWebsite());
		} else {
			transPlace.setWebsite(transPlace.getWebsite());
		}
		if (event.getStatus() != null) {
			transPlace.setStatus(event.getStatus());
		} else {
			transPlace.setStatus(transPlace.getStatus());
		}
		if (event.getPriceCategory() != null) {
			transPlace.setPriceCategory(event.getPriceCategory());
		} else {
			transPlace.setPriceCategory(transPlace.getPriceCategory());
		}
		if (event.getCity() != null) {
			transPlace.setCity(event.getCity());
		} else {
			transPlace.setCity(transPlace.getCity());
		}
		if (event.getAddress() != null) {
			transPlace.setAddress(event.getAddress());
		} else {
			transPlace.setAddress(transPlace.getAddress());
		}
		if (event.getOwner() != null) {
			transPlace.setOwner(event.getOwner());
		} else {
			transPlace.setOwner(transPlace.getOwner());
		}

		if (event.getRating() != null) {
			transPlace.setRating(event.getRating());
		} else {
			transPlace.setRating(transPlace.getRating());
		}
		if (event.getAttendee() != null) {
			transPlace.setAttendee(event.getAttendee());
		} else {
			transPlace.setAttendee(transPlace.getAttendee());
		}
		if (event.getRoutes() != null) {
			transPlace.setRoutes(event.getRoutes());
		} else {
			transPlace.setRoutes(transPlace.getRoutes());
		}
		if (event.getImage() != null) {
			transPlace.setImage(event.getImage());
		} else {
			transPlace.setImage(transPlace.getImage());
		}
		if (event.getStartDate() != null) {
			transEvent.setStartDate(event.getStartDate());
		} else {
			transEvent.setStartDate(transEvent.getStartDate());
		}
		if (event.getEndDate() != null) {
			transEvent.setEndDate(event.getEndDate());
		} else {
			transEvent.setEndDate(transEvent.getEndDate());
		}

		eventRepository.save(transEvent);
		placeRepository.save(transPlace);

	}
}
