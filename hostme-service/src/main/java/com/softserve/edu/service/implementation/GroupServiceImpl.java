package com.softserve.edu.service.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import com.softserve.edu.repositories.user.UserRepository;
import com.softserve.edu.service.GroupService;
import com.softserve.edu.service.ProfileService;
import com.softserve.edu.service.SystemPropertiesService;

/**
 * @author Oleksandr Bandurka
 */
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private SystemPropertiesService systemPropertiesService;

    public String getPropImgUrl() {
        return systemPropertiesService.getImageUrl() + "/";
    }

    @Override
    @Transactional
    public List<GroupDto> findAll(Integer page, Integer size, String orderBy,
            String orderType) {
        List<GroupDto> list = new ArrayList<GroupDto>();
        for (Group group : groupRepository.findAll(getPageRequsetObj(page,
                size, orderBy, orderType))) {
            list.add(new GroupDto(group, getPropImgUrl()));
        }
        return list;
    }

    @Override
    public List<GroupDto> findPendingGroups(Integer page, Integer size,
            String orderBy, String orderType) {
        List<GroupDto> list = new ArrayList<GroupDto>();
        Status status = Status.PENDING;
        for (Group group : groupRepository.findAllByStatus(status,
                getPageRequsetObj(page, size, orderBy, orderType))) {
            list.add(new GroupDto(group, getPropImgUrl()));
        }
        return list;
    }

    @Override
    public List<GroupDto> findApprovedGroups(Integer page, Integer size,
            String orderBy, String orderType) {
        List<GroupDto> list = new ArrayList<GroupDto>();
        Status status = Status.APPROVED;
        for (Group group : groupRepository.findAllByStatus(status,
                getPageRequsetObj(page, size, orderBy, orderType))) {
            list.add(new GroupDto(group, getPropImgUrl()));
        }
        return list;
    }

    @Override
    @Transactional
    public List<GroupDto> getGroupsByCreator(User creatorUser, Integer page,
            Integer size, String orderBy, String orderType) {
        List<GroupDto> list = new ArrayList<GroupDto>();
        for (Group group : groupRepository.findAllByCreatorUser(creatorUser,
                getPageRequsetObj(page, size, orderBy, orderType))) {
            list.add(new GroupDto(group, getPropImgUrl()));
        }
        return list;
    }

    @Override
    @Transactional
    public List<GroupDto> getGroupsByInterestedUser(User interestedUser,
            Integer page, Integer size, String orderBy, String orderType) {
        List<GroupDto> list = new ArrayList<GroupDto>();
        for (Group group : groupRepository.findAllByInterestedUsers(
                interestedUser,
                getPageRequsetObj(page, size, orderBy, orderType))) {
            list.add(new GroupDto(group, getPropImgUrl()));
        }
        return list;
    }

    private PageRequest getPageRequsetObj(Integer page, Integer size,
            String orderBy, String orderType) {
        PageRequest pageRequsetObj;
        if ("ASC".equals(orderType)) {
            pageRequsetObj = new PageRequest(page - 1, size,
                    Sort.Direction.ASC, orderBy);
        } else {
            pageRequsetObj = new PageRequest(page - 1, size,
                    Sort.Direction.DESC, orderBy);
        }
        return pageRequsetObj;
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
        if ("APPROVED".equals(status)) {
            newStatus = Status.APPROVED;
        } else if ("Pending".equals(status)) {
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
            list.add(new GroupDto(group, getPropImgUrl()));
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Group> searchGroup(Specifications<Group> specifications) {
        return groupRepository.findAll(specifications);
    }

    @Override
    public Long getGroupsPaging(Long size, String sender, User currentUser) {
        Long amount;
        Long dataBaseSize;
        switch (sender) {
        case "all-groups":
            dataBaseSize = groupRepository.count();
            break;
        case "pending-groups":
            dataBaseSize = (long) groupRepository.findAllByStatus(
                    Status.PENDING).size();
            break;
        case "approved-groups":
            dataBaseSize = (long) groupRepository.findAllByStatus(
                    Status.APPROVED).size();
            break;
        case "interesting-groups":
            dataBaseSize = (long) groupRepository.findAllByInterestedUsers(
                    currentUser).size();
            break;
        default:
            dataBaseSize = groupRepository.countByCreatorUser(currentUser);
        }
        amount = dataBaseSize / size;
        if (dataBaseSize % size > 0) {
            amount++;
        }
        return amount;
    }

}
