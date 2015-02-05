package com.softserve.edu.repositories.specifications;

import com.softserve.edu.model.Group;
import com.softserve.edu.model.Group_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class GroupSpecification {

    public static Specification<Group> groupHaveName(String name) {
        return new Specification<Group>() {
            @Override
            public Predicate toPredicate(Root<Group> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.like(root.get(Group_.groupName), name + "%");
            }
        };
    }
}
