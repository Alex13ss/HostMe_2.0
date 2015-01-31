package com.softserve.edu.repositories.user;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.softserve.edu.model.Group;
import com.softserve.edu.model.Sightseeing;
import com.softserve.edu.model.User;

public interface UserRepository extends
        PagingAndSortingRepository<User, Integer> {

    public User findByLogin(String login);

    public List<User> findByFirstNameContainingOrLastNameContaining(
            String first, String lastname);

    public Set<User> findByFavouriveSights(Sightseeing sightseeing);

    public Iterable<User> findAllByInterestingGroups(Group interestingGroups);

}
