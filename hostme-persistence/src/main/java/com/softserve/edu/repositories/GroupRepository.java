package com.softserve.edu.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.softserve.edu.model.Group;
import com.softserve.edu.model.User;

public interface GroupRepository extends
        PagingAndSortingRepository<Group, Long> {

    public Iterable<Group> findAllByCreatorUser(User creatorUser);

    public Iterable<Group> findAllByInterestedUsers(User interestedUsers);

}
