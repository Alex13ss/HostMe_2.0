package com.softserve.edu.repositories.specifications;

import com.softserve.edu.model.City;
import com.softserve.edu.model.Sightseeing;
import com.softserve.edu.model.SightseeingType;
import com.softserve.edu.model.Sightseeing_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SightseeingSpecification {

    public static Specification<Sightseeing> sightHaveCity(City city) {
        return new Specification<Sightseeing>() {
            @Override
            public Predicate toPredicate(Root<Sightseeing> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.equal(root.get(Sightseeing_.city), city);
            }
        };
    }

    public static Specification<Sightseeing> sightHaveType(SightseeingType st) {
        return new Specification<Sightseeing>() {
            @Override
            public Predicate toPredicate(Root<Sightseeing> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.equal(root.get(Sightseeing_.sightseeingType), st);
            }
        };
    }
}
