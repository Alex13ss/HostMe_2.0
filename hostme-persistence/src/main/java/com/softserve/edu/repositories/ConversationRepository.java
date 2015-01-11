package com.softserve.edu.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.softserve.edu.model.Conversation;

public interface ConversationRepository extends PagingAndSortingRepository<Conversation, Long> {
    
    Long countByGroupGroupId(Long id);
    
    Iterable<Conversation> findAllByGroupGroupId(Long id);
    
    Page<Conversation> findAllByGroupGroupId(Pageable pageable, Long id);
    
}
