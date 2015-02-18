package com.softserve.edu.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.dto.EventDto;
import com.softserve.edu.model.Event;
import com.softserve.edu.model.Status;
import com.softserve.edu.model.User;
import com.softserve.edu.model.routes.Place;
import com.softserve.edu.repositories.CityRepository;
import com.softserve.edu.repositories.CountryRepository;
import com.softserve.edu.repositories.EventRepository;
import com.softserve.edu.repositories.ImageRepository;
import com.softserve.edu.repositories.PriceCategoryRepository;
import com.softserve.edu.repositories.SystemPropertiesRepository;
import com.softserve.edu.repositories.routes.PlaceRepository;
import com.softserve.edu.repositories.user.UserRepository;
import com.softserve.edu.service.EventService;
import com.softserve.edu.service.ProfileService;
import com.softserve.edu.service.SystemPropertiesService;

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
	@Autowired
	ImageRepository imageRepository;
	@Autowired
	SystemPropertiesService systemPropertiesService;
	@Autowired
	SystemPropertiesRepository systemPropertiesRepository;

	public final Integer PROPERTY_ID = 1;
	public static Long amountOfOwnerEvents;
	public static Long amountOfAttendeeEvents;

	public boolean haveEvent(int id) {
		return eventRepository.exists(id);
	}

	@Override
	@Transactional
	public void removeEvent(Event event) {
		eventRepository.deleteEventFromUserPlace(event.getId());
		eventRepository.delete(event);
	}

	public List<EventDto> getEventsDtoList(List<Event> events) {
		List<EventDto> result = new ArrayList<>();
		String propertiesImageUrl = systemPropertiesService.getImageUrl() + "/";
		for (Event event : events) {
			result.add(new EventDto(event, propertiesImageUrl));
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
		return new EventDto(event, place);
	}

	@Override
	@Transactional
	public Event findOne(Integer id) {
		return eventRepository.findOne(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> searchEvent(Specifications<Event> specifications) {
		return eventRepository.findAll(specifications);
	}

	@Override
	@Transactional
	public List<EventDto> getEventByOwner(Integer page, Integer size,
			String orderBy, String orderType) {
		List<EventDto> list = new ArrayList<EventDto>();
		User owner = profileService.getUserByLogin(SecurityContextHolder
				.getContext().getAuthentication().getName());
		for (Event event : eventRepository.findByOwner(owner,
				getPageRequest(page, size, orderBy, orderType))) {
			list.add(new EventDto(event, placeRepository.findOne(event.getId())));
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

		for (Place place : placeRepository.findByAttendee(owner,
				getPageRequest(page, size, orderBy, orderType))) {
			list.add(new EventDto(eventRepository.findOne(place.getId()), place));
		}
		amountOfAttendeeEvents = (long) list.size();
		return list;
	}

	public PageRequest getPageRequest(Integer page, Integer size,
			String orderBy, String orderType) {
		if (orderType.equals("ASC")) {
			return new PageRequest(page - 1, size, Sort.Direction.ASC, orderBy);
		} else {
			return new PageRequest(page - 1, size, Sort.Direction.DESC, orderBy);
		}
	}

	@Override
	@Transactional
	public void addEvent(Event event, String priceCategory, String city) {
		event.setStatus(Status.PENDING);
		Integer pcId = priceCategoryRepository.findByPriceCategory(
				priceCategory).getPriceCategoryId();
		Integer cityId = cityRepository.findByCity(city).getCityId();
		event.setPriceCategory(priceCategoryRepository.findOne(pcId));
		event.setCity(cityRepository.findOne(cityId));
		eventRepository.save(event);
	}

	@Override
	@Transactional
	public void saveEvent(Event event) {
		eventRepository.save(event);
	}

	@Override
	@Transactional
	public void updateEvent(Event event, String city, String priceCategory) {
		Integer pcId = priceCategoryRepository.findByPriceCategory(
				priceCategory).getPriceCategoryId();
		Integer cityId = cityRepository.findByCity(city).getCityId();
		event.setPriceCategory(priceCategoryRepository.findOne(pcId));
		event.setCity(cityRepository.findOne(cityId));
		event.setImage(imageRepository.findAllByPlace(event));
		Event newEvent = eventRepository.findOne(event.getId());
		event.setStatus(newEvent.getStatus());
		event.setRating(newEvent.getRating());
		eventRepository.save(event);
	}

	@Override
	@Transactional
	public Long getPageCount(Long size, String sender) {
		Long amount;
		Long dataBaseSize;
		if (sender.equals("all-events")) {
			dataBaseSize = eventRepository.count();
		} else if (sender.equals("my-events")) {
			dataBaseSize = EventServiceImpl.amountOfOwnerEvents + 1;
		} else {
			dataBaseSize = EventServiceImpl.amountOfAttendeeEvents + 1;
		}
		amount = dataBaseSize / size;
		if (dataBaseSize % size != 0) {
			amount++;
		}
		return amount;
	}

	@Override
	public boolean checkEventOwner(EventDto event, User user) {
		Event checkedEvent = eventRepository.findOne(event.getId());
		User owner = checkedEvent.getOwner();
		return owner.equals(user);
	}

	@Override
	@Transactional
	public void addAttendee(User user, Integer id) {
		Event event = eventRepository.findOne(id);
		Set<User> attendeeSet = event.getAttendee();
		attendeeSet.add(user);
		event.setAttendee(attendeeSet);
		eventRepository.save(event);
		Set<Place> placeSet = user.getAttendee();
		placeSet.add(event);
		user.setAttendee(placeSet);
		userRepository.save(user);
	}

	@Override
	@Transactional
	public void leaveEvent(User user, Integer id) {
		eventRepository.deleteAttendeeFromEvent(user.getUserId(), id);
	}

	public boolean checkEventSubscribed(EventDto event, User user) {
		Event foundedEvent = eventRepository.findOne(event.getId());
		Set<User> attendeeUsers = foundedEvent.getAttendee();
		return attendeeUsers.contains(user);
	}
}
