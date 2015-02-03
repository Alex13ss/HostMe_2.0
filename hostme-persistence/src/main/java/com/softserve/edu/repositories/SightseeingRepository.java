package com.softserve.edu.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.model.Sightseeing;
import com.softserve.edu.model.User;

public interface SightseeingRepository extends
		JpaRepository<Sightseeing, Integer> {
	public List<Sightseeing> findByNameContaining(String name);

	public Set<Sightseeing> findByLikers(User user);
	
	@Modifying
	@Transactional("transactionManager")
	@Query(value = "delete from Favourite_sightseeing where place_id = ?1", nativeQuery=true)
	public void deleteLikefromSightseeing(Integer id);
	
	@Modifying
	@Transactional("transactionManager")
	@Query(value = "delete from Favourite_sightseeing where place_id = ?1 and user_id = ?2", nativeQuery=true)
	public void unlike(Integer id, Integer likerId);
	
}
