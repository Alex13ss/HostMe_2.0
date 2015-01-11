package com.softserve.edu.service;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.softserve.edu.model.Group;

@Service
@Transactional
public interface GroupService {
        
        Set<Group> findAll();

}
