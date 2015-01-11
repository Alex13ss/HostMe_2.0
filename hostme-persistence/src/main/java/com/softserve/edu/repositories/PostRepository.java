package com.softserve.edu.repositories;

import org.springframework.data.repository.CrudRepository;

import com.softserve.edu.model.Post;

public interface PostRepository extends CrudRepository<Post, Long>{
    
    Post findFirstByConversationIdOrderByIdDesc(Long id);
    //Post findFirstByOrderByLastname();
    
}
