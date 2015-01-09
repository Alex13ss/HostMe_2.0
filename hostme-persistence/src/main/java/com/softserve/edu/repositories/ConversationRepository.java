package com.softserve.edu.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.softserve.edu.model.Conversation;

public interface ConversationRepository extends PagingAndSortingRepository<Conversation, Long> {
    
    
    
}
