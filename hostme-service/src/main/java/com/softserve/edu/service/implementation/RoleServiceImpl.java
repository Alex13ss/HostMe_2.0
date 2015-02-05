package com.softserve.edu.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.edu.model.Role;
import com.softserve.edu.repositories.RoleRepository;
import com.softserve.edu.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;

	public Role getRole(int id){
		return roleRepository.findOne(id);
	}
}
