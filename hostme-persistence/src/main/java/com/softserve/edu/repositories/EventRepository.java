package com.softserve.edu.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.softserve.edu.model.Event;
import com.softserve.edu.model.User;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

public interface EventRepository extends JpaRepository<Event, Integer>, JpaSpecificationExecutor{
	
	public List<Event> findByStartDate(Date date);

	public List<Event> findByNameContaining(String name);

	public List<Event> findByCity_CityContainingIgnoreCaseAndStartDateBefore(String name, Date startDate);
	
	public List<Event> findByOwner(User owner, Pageable pageable);
	
	@Modifying
	@Transactional("transactionManager")
	@Query(value = "delete from user_place where place_id = ?1", nativeQuery=true)
	public void deleteEventFromUserPlace(Integer placeId);
	
	@Modifying
	@Transactional("transactionManager")
	@Query(value = "delete from user_place where user_id = ?1 and place_id = ?2 ", nativeQuery=true)
	public void deleteAttendeeFromEvent(Integer userId, Integer placeId);
}

