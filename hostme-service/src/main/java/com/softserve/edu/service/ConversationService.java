package com.softserve.edu.service;

import java.util.Set;

import com.softserve.edu.model.Conversation;

public interface ConversationService {
    
    Set<Conversation> findAll();
    
}
