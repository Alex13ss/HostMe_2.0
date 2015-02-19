package com.softserve.edu.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.softserve.edu.model.Sightseeing;
import com.softserve.edu.model.User;

public interface SightseeingRepository extends
		JpaRepository<Sightseeing, Integer>, JpaSpecificationExecutor<Sightseeing> {
	public List<Sightseeing> findByNameContaining(String name);

	public Set<Sightseeing> findByLikers(User user);

	@Modifying
	@Query(value = "delete from Favourite_sightseeing where place_id = ?1", nativeQuery = true)
	public void deleteLikefromSightseeing(Integer id);

	@Modifying
	@Query(value = "delete from Favourite_sightseeing where place_id = ?1 and user_id = ?2", nativeQuery = true)
	public void unlike(Integer id, Integer likerId);

	@Query(value = "select count(*) from Favourite_sightseeing where place_id = ?1", nativeQuery = true)
	public Integer getRating(Integer id);

	public Long countByOwner(User owner);

	public List<Sightseeing> findByOwner(User owner, Pageable peagelbe);

	public List<Sightseeing> findByLikers(User liker, Pageable pageable);
}
