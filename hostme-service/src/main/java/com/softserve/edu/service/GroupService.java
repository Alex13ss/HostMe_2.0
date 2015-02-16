package com.softserve.edu.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specifications;

import com.softserve.edu.dto.GroupDto;
import com.softserve.edu.model.Group;
import com.softserve.edu.model.User;

/**
 * @author Oleksandr Bandurka
 */
public interface GroupService {

    List<GroupDto> findAll(Integer page, Integer size, String orderBy,
            String orderType);

    List<GroupDto> findPendingGroups(Integer page, Integer size,
            String orderBy, String orderType);

    List<GroupDto> findApprovedGroups(Integer page, Integer size,
            String orderBy, String orderType);

    List<GroupDto> getGroupsByCreator(User creatorUser, Integer page,
            Integer size, String orderBy, String orderType);

    List<GroupDto> getGroupsByInterestedUser(User interestedUser, Integer page,
            Integer size, String orderBy, String orderType);

    void create(Group group);       /*-C-*/

    Group findOne(Long id);         /*-R-*/

    void update(Group group);       /*-U-*/

    void delete(Group group);       /*-D-*/

    void saveGroup(Group group);

    void subscribe(User user, Group group);

    void unsubscribe(Integer userId, Long groupId);

    boolean checkInterestedByGroupAndUser(Group group, User user);

    boolean checkIsItGroupCreator(Group group, User user);

    void updateGroupStatus(String status, Long id);

    List<GroupDto> getGroupsDtoList(List<Group> groups);

    List<Group> searchGroup(Specifications<Group> specifications);

    Integer getCurrentSubscribers(Long groupId);

    Long getGroupsPaging(Long size, String sender, User currentUser);

}
