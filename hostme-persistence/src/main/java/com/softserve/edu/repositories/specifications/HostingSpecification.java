package com.softserve.edu.repositories.specifications;

import com.softserve.edu.model.Hosting;
import com.softserve.edu.model.Hosting_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

public class HostingSpecification {
    
    public static Specification<Hosting> hostingHaveCity(String city) {
        return new Specification<Hosting>() {
            @Override
            public Predicate toPredicate(Root<Hosting> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get(Hosting_.city), city + "%");
            }
        };
    }
    
    public static Specification<Hosting> hostingNumGuest(int guests) {
        return new Specification<Hosting>() {
            @Override
            public Predicate toPredicate(Root<Hosting> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };
    }
    
    public static Specification<Hosting> hostingOption(SingularAttribute<Hosting, Boolean> attribute, boolean allow) {
        return new Specification<Hosting>() {
            @Override
            public Predicate toPredicate(Root<Hosting> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(attribute), allow);
            }
        };
    }
}
