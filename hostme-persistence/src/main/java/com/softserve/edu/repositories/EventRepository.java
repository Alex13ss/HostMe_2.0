package com.softserve.edu.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.softserve.edu.model.Event;
import com.softserve.edu.model.User;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EventRepository extends JpaRepository<Event, Integer>, JpaSpecificationExecutor{
	
	public List<Event> findByStartDate(Date date);

	public List<Event> findByNameContaining(String name);

	public List<Event> findByCity_CityContainingIgnoreCaseAndStartDateBefore(String name, Date startDate);
	
	public List<Event> findByOwner(User owner, Pageable pageable);
}
