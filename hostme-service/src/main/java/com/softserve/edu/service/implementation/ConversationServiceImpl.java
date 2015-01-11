package com.softserve.edu.service.implementation;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.softserve.edu.dto.ConversationDto;
import com.softserve.edu.model.Conversation;
import com.softserve.edu.model.Post;
import com.softserve.edu.repositories.ConversationRepository;
import com.softserve.edu.repositories.PostRepository;
import com.softserve.edu.service.ConversationService;

@Service
public class ConversationServiceImpl implements ConversationService{
    
    @Autowired
    private ConversationRepository conversationRepository;
    
    @Autowired
    private PostRepository postRepository;

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

    @Override
    @Transactional
    public List<ConversationDto> findLatestConversationsDtoByGroupId(Long id) {
	//Додати пошук по ід групи
	List<ConversationDto> result = new ArrayList<ConversationDto>();
	Pageable pageable = new PageRequest(0, 5, Direction.DESC, "id");
	List<Conversation> conversations = conversationRepository.findAll(pageable).getContent();
	for (Conversation conv : conversations){
	    Post lastPost = postRepository.findFirstByConversationIdOrderByIdDesc(conv.getId());
	    result.add(new ConversationDto(conv,lastPost));
	}
	return result;
    }

    @Override
    public Long countByGroupId(Long id) {
	return conversationRepository.countByGroupGroupId(id);
    }
    
}
