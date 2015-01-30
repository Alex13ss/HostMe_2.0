package com.softserve.edu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softserve.edu.model.Group;
import com.softserve.edu.model.User;

public interface GroupRepository extends JpaRepository<Group, Long> {
    
    Iterable<Group> findAllByCreatorUser(User creatorUser);

}
