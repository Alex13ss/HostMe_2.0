package com.softserve.edu.repositories.specifications;

import com.softserve.edu.model.routes.Place;
import com.softserve.edu.model.routes.Place_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PlaceSpecification {

    public static Specification<Place> placeHasName(String name) {
        return new Specification<Place>() {
            @Override
            public Predicate toPredicate(Root<Place> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                return cb.like(root.get(Place_.name), name + "%");
            }
        };
    }
}
