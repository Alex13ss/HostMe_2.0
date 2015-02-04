package com.softserve.edu.service.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.softserve.edu.dto.SearchRequestDto;
import com.softserve.edu.repositories.specifications.PlaceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.dto.EventDto;
import com.softserve.edu.model.City;
import com.softserve.edu.model.Event;
import com.softserve.edu.model.PriceCategory;
import com.softserve.edu.model.User;
import com.softserve.edu.model.routes.Place;
import com.softserve.edu.repositories.CityRepository;
import com.softserve.edu.repositories.CountryRepository;
import com.softserve.edu.repositories.EventRepository;
import com.softserve.edu.repositories.PriceCategoryRepository;
import com.softserve.edu.repositories.routes.PlaceRepository;
import com.softserve.edu.repositories.user.UserRepository;
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
	@Autowired
	PriceCategoryRepository priceCategoryRepository;
	@Autowired
	CityRepository cityRepository;
	@Autowired
	CountryRepository countryRepository;
	@Autowired
	UserRepository userRepository;

	public static Long amountOfOwnerEvents;
	public static Long amountOfAttendeeEvents;

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
	public void removeEvent(Event event) {
		eventRepository.delete(event);
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
	public List<EventDto> getAllEventsPaging(Integer page, Integer size,
			String orderBy, String orderType) {
		List<EventDto> list = new ArrayList<EventDto>();
		PageRequest pageRequsetObj;
		if (orderType.equals("ASC")) {
			pageRequsetObj = new PageRequest(page - 1, size,
					Sort.Direction.ASC, orderBy);
		} else {
			pageRequsetObj = new PageRequest(page - 1, size,
					Sort.Direction.DESC, orderBy);
		}
		for (Event event : eventRepository.findAll(pageRequsetObj)) {
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
	public List<Event> searchEvent(Specifications<Event> specifications) {
		return eventRepository.findAll(specifications);
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
	public List<EventDto> getEventByOwner(Integer page, Integer size,
			String orderBy, String orderType) {
		List<EventDto> list = new ArrayList<EventDto>();
		User owner = profileService.getUserByLogin(SecurityContextHolder
				.getContext().getAuthentication().getName());
		PageRequest pageRequsetObj;
		if (orderType.equals("ASC")) {
			pageRequsetObj = new PageRequest(page - 1, size,
					Sort.Direction.ASC, orderBy);
		} else {
			pageRequsetObj = new PageRequest(page - 1, size,
					Sort.Direction.DESC, orderBy);
		}
		for (Place place : placeRepository.findByOwner(owner, pageRequsetObj)) {
			list.add(new EventDto(eventRepository.findOne(place.getId()), place));
		}
		amountOfOwnerEvents = (long) list.size();
		return list;
	}

	@Override
	@Transactional
	public List<EventDto> getByAttendee(Integer page, Integer size,
			String orderBy, String orderType) {
		List<EventDto> list = new ArrayList<EventDto>();
		User owner = profileService.getUserByLogin(SecurityContextHolder
				.getContext().getAuthentication().getName());
		PageRequest pageRequsetObj;
		if (orderType.equals("ASC")) {
			pageRequsetObj = new PageRequest(page - 1, size,
					Sort.Direction.ASC, orderBy);
		} else {
			pageRequsetObj = new PageRequest(page - 1, size,
					Sort.Direction.DESC, orderBy);
		}
		User attendee = profileService.getUserByLogin(SecurityContextHolder
				.getContext().getAuthentication().getName());

		//Set<Place> attendeeUser = attendee.getAttendee();

		for (Place place : placeRepository.findByAttendee(owner, pageRequsetObj)) {
			list.add(new EventDto(eventRepository.findOne(place.getId()), place));
		}
		amountOfAttendeeEvents = (long) list.size();
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
		event.setDescription(eventDto.getDescription());
		event.setRating(eventDto.getRating());
		return event;
	}

	@Override
	@Transactional
	public void updateEvent(Event event, String city, String priceCategory) {
		Integer pcId = priceCategoryRepository.findByPriceCategory(
				priceCategory).getPriceCategoryId();
		Integer cityId = cityRepository.findByCity(city).getCityId();
		event.setPriceCategory(priceCategoryRepository.findOne(pcId));
		event.setCity(cityRepository.findOne(cityId));
		eventRepository.save(event);
	}
}
