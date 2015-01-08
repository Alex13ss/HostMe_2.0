package com.softserve.edu.service.implementation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softserve.edu.model.Conversation;
import com.softserve.edu.repositories.ConversationRepository;
import com.softserve.edu.service.ConversationService;

@Service
public class ConversationServiceImpl implements ConversationService{
    
    @Autowired
    private ConversationRepository conversationRepository;

    @Override
    @Transactional
    public Set<Conversation> findAll() {
	List<Conversation> list = (List<Conversation>) conversationRepository.findAll();
	Set<Conversation> items = new HashSet<Conversation>(list);  
	return items;
    }
    
    
    
    

}
