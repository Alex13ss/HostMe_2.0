package com.softserve.edu.repositories.specifications;

import com.softserve.edu.model.City;
import com.softserve.edu.model.Event;
import com.softserve.edu.model.Event_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

public class EventSpecification {

    public static Specification<Event> eventHaveCity(City city) {
        return new Specification<Event>() {
            @Override
            public Predicate toPredicate(Root<Event> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.equal(root.get(Event_.city), city);
            }
        };
    }

    public static Specification<Event> eventFromDate(Date date) {
        return new Specification<Event>() {
            @Override
            public Predicate toPredicate(Root<Event> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.greaterThanOrEqualTo(root.get(Event_.startDate), date);
            }
        };
    }

    public static Specification<Event> eventToDate(Date date) {
        return new Specification<Event>() {
            @Override
            public Predicate toPredicate(Root<Event> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.lessThanOrEqualTo(root.get(Event_.endDate), date);
            }
        };
    }
}
