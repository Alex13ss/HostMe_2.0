package com.softserve.edu.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softserve.edu.model.Event;
import com.softserve.edu.model.Group;
import com.softserve.edu.model.Image;
import com.softserve.edu.model.Sightseeing;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    Set<Image> findAllByPlace(Sightseeing sightseeing);

    Set<Image> findAllByPlace(Event event);

    Set<Image> findAllByGroup(Group group);

}
