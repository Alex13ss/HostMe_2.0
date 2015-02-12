package com.softserve.edu.service.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.softserve.edu.dto.GroupDto;
import com.softserve.edu.model.Conversation;
import com.softserve.edu.model.Group;
import com.softserve.edu.model.Status;
import com.softserve.edu.model.User;
import com.softserve.edu.repositories.ConversationRepository;
import com.softserve.edu.repositories.GroupRepository;
import com.softserve.edu.repositories.ImageRepository;
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

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private ImageRepository imageRepository;

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

    @PreAuthorize("#group.creatorUser.login == authentication.name or hasRole('MODERATOR')")
    @Override
    @Transactional
    public void delete(@P("group") Group group) {
        for (Conversation conversation : conversationRepository
                .findAllByGroupId(group.getId())) {
            conversationRepository
                    .deleteModeratorsfromConversation(conversation.getId());
            conversationRepository.delete(conversation);
        }
        groupRepository.removeGroupSubscription(group.getId());
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
        group.setImages(imageRepository.findAllByGroup(group));
        groupRepository.save(group);
    }

    @Override
    @Transactional
    public void saveGroup(Group group) {
        groupRepository.save(group);
    }

    @PreAuthorize("hasRole('USER')")
    @Override
    @Transactional
    public void subscribe(User user, Group group) {
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

    @PreAuthorize("hasRole('USER')")
    @Override
    @Transactional
    public void unsubscribe(Integer userId, Long groupId) {
        groupRepository.unsubscribe(userId, groupId);
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
    @Transactional
    public void updateGroupStatus(String status, Long id) {
        Status newStatus;
        if (status.equals("APPROVED")) {
            newStatus = Status.APPROVED;
        } else if (status.equals("Pending")) {
            newStatus = Status.PENDING;
        } else {
            newStatus = Status.REFUSED;
        }
        Group group = groupRepository.findOne(id);
        group.setStatus(newStatus);
        groupRepository.save(group);
    }

    @Override
    public Integer getCurrentSubscribers(Long groupId) {
        return groupRepository.getGroupSubscribers(groupId);
    }

    @Override
    public List<GroupDto> getGroupsDtoList(List<Group> groups) {
        List<GroupDto> list = new ArrayList<>();
        for (Group group : groups) {
            list.add(new GroupDto(group));
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Group> searchGroup(Specifications<Group> specifications) {
        return groupRepository.findAll(specifications);
    }

    @Override
    public Set<GroupDto> findPendingGroups() {
        Set<GroupDto> list = new HashSet<GroupDto>();
        Status status = Status.PENDING;
        for (Group group : groupRepository.findAllByStatus(status)) {
            list.add(new GroupDto(group));
        }
        return list;
    }

    @Override
    public Set<GroupDto> findApprovedGroups() {
        Set<GroupDto> list = new HashSet<GroupDto>();
        Status status = Status.APPROVED;
        for (Group group : groupRepository.findAllByStatus(status)) {
            list.add(new GroupDto(group));
        }
        return list;
    }

}
