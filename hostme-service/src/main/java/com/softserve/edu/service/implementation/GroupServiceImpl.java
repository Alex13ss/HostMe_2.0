package com.softserve.edu.service.implementation;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.model.Group;
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
    public Set<Group> findAll() {
        List<Group> list = (List<Group>) groupRepository.findAll();
        Set<Group> items = new HashSet<Group>(list);
        return items;
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

    // @Override
    // public void create(Group group, String name) {
    // User user = userRepository.findByName(name);
    // group.setUser(user);
    // groupRepository.save(group);
    // }

}
