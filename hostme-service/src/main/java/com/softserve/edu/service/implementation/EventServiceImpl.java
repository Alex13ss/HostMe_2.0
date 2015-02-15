package com.softserve.edu.service.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.softserve.edu.repositories.*;

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
import com.softserve.edu.model.Status;
import com.softserve.edu.model.User;
import com.softserve.edu.model.routes.Place;
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
	private SystemPropertiesRepository systemPropertiesRepository;

	public final Integer PROPERTY_ID = 1;
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
		for (Event event : eventRepository.findByOwner(owner, pageRequsetObj)) {
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
		PageRequest pageRequsetObj;
		if (orderType.equals("ASC")) {
			pageRequsetObj = new PageRequest(page - 1, size,
					Sort.Direction.ASC, orderBy);
		} else {
			pageRequsetObj = new PageRequest(page - 1, size,
					Sort.Direction.DESC, orderBy);
		}

		for (Place place : placeRepository
				.findByAttendee(owner, pageRequsetObj)) {
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
	public void updateEventStatus(String status, Integer id) {
		Status newStatus;
		if (status.equals("APPROVED")) {
			newStatus = Status.APPROVED;
		} else if (status.equals("Pending")) {
			newStatus = Status.PENDING;
		} else {
			newStatus = Status.REFUSED;
		}
		Event event = eventRepository.findOne(id);
		event.setStatus(newStatus);
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
		if (sender.equals("all-events")) {
			Long dataBaseSize = eventRepository.count();
			if (dataBaseSize % size == 0) {
				amount = dataBaseSize / size;
			} else {
				amount = dataBaseSize / size + 1;
			}
		} else if (sender.equals("my-events")) {
			Long dataOwnreSize = EventServiceImpl.amountOfOwnerEvents + 1;
			if (dataOwnreSize % size == 0) {
				amount = dataOwnreSize / size;
			} else {
				amount = dataOwnreSize / size + 1;
			}
		} else {
			Long dataAttendeeSize = EventServiceImpl.amountOfAttendeeEvents + 1;
			if (dataAttendeeSize % size == 0) {
				amount = dataAttendeeSize / size;
			} else {
				amount = dataAttendeeSize / size + 1;
			}
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
	public void leaveEvent(User user, Integer id){
		eventRepository.deleteAttendeeFromEvent(user.getUserId(), id);
	}

	public boolean checkEventSubscribed(EventDto event, User user){
		Event foundedEvent = eventRepository.findOne(event.getId());
		Set<User> attendeeUsers = foundedEvent.getAttendee();
		return attendeeUsers.contains(user);
	}
}
