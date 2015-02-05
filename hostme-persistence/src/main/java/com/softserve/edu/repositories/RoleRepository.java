package com.softserve.edu.repositories;

import org.springframework.data.repository.CrudRepository;
import com.softserve.edu.model.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
};
