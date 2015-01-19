package com.softserve.edu.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.softserve.edu.model.Event;

public interface EventRepository extends CrudRepository<Event, Integer>  {
	
	public List<Event> findByStartDate(Date date);
	
	
}
