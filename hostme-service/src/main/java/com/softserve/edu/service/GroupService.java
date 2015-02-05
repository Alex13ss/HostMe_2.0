package com.softserve.edu.service;

import java.util.List;
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

    public void saveGroup(Group group);

    public void saveInterestedUser(User user, Group group);

    public void removeInterestingRelationship(User user, Group group);

    public Set<GroupDto> getGroupsByCreator(User creatorUser);

    public List<GroupDto> getGroupsByInterestedUser(User interestedUser);

    public boolean checkInterestedByGroupAndUser(Group group, User user);

    public boolean checkIsItGroupCreator(Group group, User user);

    public void updateGroupStatus(String status, Long id);

    public List<GroupDto> getGroupsDtoList(List<Group> groups);

}
