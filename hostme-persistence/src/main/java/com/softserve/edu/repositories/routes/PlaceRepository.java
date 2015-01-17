package com.softserve.edu.repositories.routes;

import com.softserve.edu.model.User;
import com.softserve.edu.model.routes.Place;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PlaceRepository extends PagingAndSortingRepository<Place, Integer> {
    public List<Place> findByOwnerNot(User owner);
}
