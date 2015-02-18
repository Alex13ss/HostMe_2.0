package com.softserve.edu.repositories.routes;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.softserve.edu.model.City;
import com.softserve.edu.model.PriceCategory;
import com.softserve.edu.model.User;
import com.softserve.edu.model.routes.Place;
import org.springframework.data.repository.query.Param;

public interface PlaceRepository extends PagingAndSortingRepository<Place, Integer>, JpaSpecificationExecutor {

	@Query("SELECT p FROM Place p JOIN FETCH p.routes WHERE p.city = (:city)")
	public List<Place> findByCityAndFetchRoutesEagerly(@Param("city")City city);

	public List<Place> findByOwnerNot(User owner);

	public List<Place> findByAttendee(User user, Pageable pageable);

	public List<Place> findByLikers(User liker, Pageable pageable);
	
	public List<Place> findByBookedBy(User user);
}
