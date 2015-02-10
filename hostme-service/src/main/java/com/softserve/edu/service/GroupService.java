package com.softserve.edu.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.domain.Specifications;

import com.softserve.edu.dto.GroupDto;
import com.softserve.edu.model.Group;
import com.softserve.edu.model.User;

public interface GroupService {

    Set<GroupDto> findAll();

    Group findOne(Long id);

    void delete(Group group);

    void create(Group group);

    void update(Group group);

    void saveGroup(Group group);

    void subscribe(User user, Group group);

    void unsubscribe(Integer userId, Long groupId);

    Set<GroupDto> getGroupsByCreator(User creatorUser);

    List<GroupDto> getGroupsByInterestedUser(User interestedUser);

    boolean checkInterestedByGroupAndUser(Group group, User user);

    boolean checkIsItGroupCreator(Group group, User user);

    void updateGroupStatus(String status, Long id);

    List<GroupDto> getGroupsDtoList(List<Group> groups);

    List<Group> searchGroup(Specifications<Group> specifications);

    Integer getCurrentSubscribers(Long groupId);

    Set<GroupDto> findPendingGroups();

    Set<GroupDto> findApprovedGroups();

}
