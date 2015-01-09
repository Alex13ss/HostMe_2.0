package com.softserve.edu.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.softserve.edu.model.Conversation;

public interface ConversationService {

    List<Conversation> findAll();
    
    List<Conversation> findAll(Sort sort);

    List<Conversation> findAll(Pageable pageable);
    
    Conversation findOne(Long id);
}
