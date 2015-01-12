package com.softserve.edu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softserve.edu.model.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {

}
