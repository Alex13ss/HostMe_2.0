package com.softserve.edu.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.model.Conversation;

public interface ConversationRepository extends PagingAndSortingRepository<Conversation, Long> {
    
    Long countByGroupId(Long id);
    
    Iterable<Conversation> findAllByGroupId(Long id);
    
    Page<Conversation> findAllByGroupId(Pageable pageable, Long id);
    
    @Modifying
    @Transactional("transactionManager")
    @Query(value = "delete from conversations_moderators where conversation_id = ?1", nativeQuery=true)
    public void deleteModeratorsfromConversation(Long id);
    
}
