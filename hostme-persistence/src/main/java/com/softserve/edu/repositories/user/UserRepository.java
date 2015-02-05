package com.softserve.edu.repositories.user;

import java.util.List;
import java.util.Set;

import com.softserve.edu.model.Group;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.softserve.edu.model.Sightseeing;
import com.softserve.edu.model.User;

import org.springframework.data.repository.query.Param;

public interface UserRepository extends PagingAndSortingRepository<User, Integer>{
    public User findByLogin(String login);

    public List<User> findByFirstNameContainingOrLastNameContaining(String first, String lastname);

    public Set<User> findByFavouriveSights(Sightseeing sightseeing);

    public Iterable<User> findAllByInterestingGroups(Group interestingGroups);
    
    public List<User> findByFirstNameContainingOrLastNameContainingOrLoginContainingAllIgnoreCase(String firstName, String lastName, String login);

    @Query("SELECT p FROM User p JOIN FETCH p.places WHERE p.userId = (:id)")
    public User findByUserIdAndFetchPlacesEagerly(@Param("id") int id);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.bookedPlaces WHERE u.userId = (:id)")
    public User findByUserIdAndFetchBookedPlacesEagerly(@Param("id") int id);
}
