package com.softserve.edu.service.implementation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.softserve.edu.model.Group;
import com.softserve.edu.repositories.GroupRepository;
import com.softserve.edu.service.GroupService;

@Component
public class GroupServiceImpl implements GroupService {
    
    @Autowired
    private GroupRepository groupRepository;

    @Override
    @Transactional
    public Set<Group> findAll() {
        List<Group> list = (List<Group>) groupRepository.findAll();
        Set<Group> items = new HashSet<Group>(list);  
        return items;
    }

}
