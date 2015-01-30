package com.softserve.edu.service;

import java.util.Set;

import com.softserve.edu.dto.GroupDto;
import com.softserve.edu.model.Group;
import com.softserve.edu.model.User;

public interface GroupService {

    public Set<GroupDto> findAll();

    public Group findOne(Long id);

    public void delete(Group group);

    public void create(Group group);

    public void update(Group group);

    public Set<GroupDto> getGroupByCreator(User creatorUser);

}
