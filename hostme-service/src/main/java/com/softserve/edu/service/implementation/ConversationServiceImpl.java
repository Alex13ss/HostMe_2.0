package com.softserve.edu.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<Conversation> findAll() {
	return (List<Conversation>) conversationRepository.findAll();
    }

    @Override
    @Transactional
    public List<Conversation> findAll(Sort sort) {
	return (List<Conversation>) conversationRepository.findAll(sort);
    }

    @Override
    @Transactional
    public List<Conversation> findAll(Pageable pageable) {
	return conversationRepository.findAll(pageable).getContent();
    }

    @Override
    @Transactional
    public Conversation findOne(Long id) {
	return conversationRepository.findOne(id);
    }

}
