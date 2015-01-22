package com.softserve.edu.repositories.user;

import com.softserve.edu.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Integer>{
    public User findByLogin(String login);
}
