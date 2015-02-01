package com.softserve.edu.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.softserve.edu.model.Event;

public interface EventRepository extends JpaRepository<Event, Integer>  {
	
	public List<Event> findByStartDate(Date date);

	public List<Event> findByNameContaining(String name);
	
	public Integer countById(Integer id);
}
