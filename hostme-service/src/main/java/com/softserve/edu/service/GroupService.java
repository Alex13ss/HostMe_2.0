package com.softserve.edu.service;

import java.util.Set;

import com.softserve.edu.model.Group;

public interface GroupService {

    Set<Group> findAll();

    public Group findOne(Long id);

    void delete(Group group);

    void create(Group group);

}
