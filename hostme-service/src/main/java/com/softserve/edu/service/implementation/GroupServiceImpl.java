package com.softserve.edu.service.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.dto.GroupDto;
import com.softserve.edu.model.Group;
import com.softserve.edu.model.Notification;
import com.softserve.edu.model.Status;
import com.softserve.edu.model.User;
import com.softserve.edu.repositories.GroupRepository;
import com.softserve.edu.repositories.NotificationRepository;
import com.softserve.edu.repositories.user.UserRepository;
import com.softserve.edu.service.GroupService;
import com.softserve.edu.service.ProfileService;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    @Transactional
    public Set<GroupDto> findAll() {
        Set<GroupDto> list = new HashSet<GroupDto>();
        for (Group group : groupRepository.findAll()) {
            list.add(new GroupDto(group));
        }
        return list;
    }

    @Override
    @Transactional
    public Set<GroupDto> getGroupsByCreator(User creatorUser) {
        Set<GroupDto> list = new HashSet<GroupDto>();
        for (Group group : groupRepository.findAllByCreatorUser(creatorUser)) {
            list.add(new GroupDto(group));
        }
        return list;
    }

    @Override
    @Transactional
    public Group findOne(Long id) {
        return groupRepository.findOne(id);
    }

    @Override
    @Transactional
    public void delete(Group group) {
        groupRepository.delete(group);
    }

    @Override
    @Transactional
    public void create(Group group) {
        group.setCreatedAt(new Date());
        group.setStatus(Status.PENDING);
        group.setCreatorUser(profileService.getCurrentUser());
        groupRepository.save(group);
    }

    @Override
    @Transactional
    public void update(Group group) {
        group.setLastEditedAt(new Date());
        group.setLastEditor(profileService.getCurrentUser());
        groupRepository.save(group);
    }

    @Override
    @Transactional
    public void saveInterestedUser(User user, Group group) {
        List<User> interestedUsers = (List<User>) userRepository
                .findAllByInterestingGroups(group);
        interestedUsers.add(user);
        List<Group> interestingGroups = (List<Group>) groupRepository
                .findAllByInterestedUsers(user);
        group.setInterestedUsers(interestedUsers);
        interestingGroups.add(group);
        user.setInterestingGroups(interestingGroups);
        groupRepository.save(group);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void removeInterestingRelationship(User user, Group group) {
        List<User> interestedUsers = (List<User>) userRepository
                .findAllByInterestingGroups(group);
        interestedUsers.remove(user);
        List<Group> interestingGroups = (List<Group>) groupRepository
                .findAllByInterestedUsers(user);
        group.setInterestedUsers(interestedUsers);
        interestingGroups.remove(group);
        user.setInterestingGroups(interestingGroups);
        groupRepository.save(group);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public List<GroupDto> getGroupsByInterestedUser(User interestedUser) {
        List<GroupDto> list = new ArrayList<GroupDto>();
        for (Group group : groupRepository
                .findAllByInterestedUsers(interestedUser)) {
            list.add(new GroupDto(group));
        }
        return list;
    }

    @Override
    @Transactional
    public boolean checkInterestedByGroupAndUser(Group group, User user) {
        Group groupFound = groupRepository.findOne(group.getId());
        List<User> interestedUsers = groupFound.getInterestedUsers();
        if (interestedUsers.contains(user)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkIsItGroupCreator(Group group, User user) {
        Group groupFound = groupRepository.findOne(group.getId());
        User creatorUser = groupFound.getCreatorUser();
        if (creatorUser.equals(user)) {
            return true;
        }
        return false;
    }

    @Override
    public List<GroupDto> getGroupsWithNotifications(
            List<Notification> notifications) {
        List<GroupDto> list = new ArrayList<GroupDto>();
//        for (Group group : groupRepository.findGroupByNotifications(notifications)) {
//            list.add(new Group(group));
//        }
        return list;
    }

}
