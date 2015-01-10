package com.softserve.edu.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.softserve.edu.model.City;
import com.softserve.edu.model.Event;
import com.softserve.edu.model.PriceCategory;
import com.softserve.edu.model.User;

public interface EventRepository extends CrudRepository<Event, Integer>  {
	

	public List<Event> findByStartDate(Date date);
	
	public List<Event> findByCity(City city);
	
	public List<Event> findByOwner(User owner);
	
	public List<Event> findByPriceCategory(PriceCategory priceCategory);
	
	public List<Event> findByWebsite(String website);
}
