package com.softserve.edu.repositories.user;

import com.softserve.edu.model.User;
import com.softserve.edu.model.routes.Place;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User, Integer>{
    public User findByLogin(String login);

    public List<User> findByFirstNameContainingOrLastNameContaining(String first, String lastname);
}
