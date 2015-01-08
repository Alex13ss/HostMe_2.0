package com.softserve.edu.repositories;

import org.springframework.data.repository.CrudRepository;

import com.softserve.edu.model.Conversation;

public interface ConversationRepository extends CrudRepository<Conversation, Long> {
    
    
    
}
