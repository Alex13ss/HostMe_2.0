package com.softserve.edu.service.implementation;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.softserve.edu.dto.GroupDto;
import com.softserve.edu.model.Group;
import com.softserve.edu.model.User;
import com.softserve.edu.repositories.GroupRepository;
import com.softserve.edu.service.GroupService;
import com.softserve.edu.service.ProfileService;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private GroupRepository groupRepository;

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
    public void delete(Group group) {
        groupRepository.delete(group);
    }

    @Override
    public void create(Group group) {
        group.setCreatedAt(new Date());
        group.setCreatorUser(profileService.getCurrentUser());
        groupRepository.save(group);
    }

    @Override
    public void update(Group group) {
        group.setLastEditedAt(new Date());
        group.setLastEditor(profileService.getCurrentUser());
        groupRepository.save(group);
    }

}
