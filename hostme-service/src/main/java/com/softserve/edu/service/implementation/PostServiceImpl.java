package com.softserve.edu.service.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.softserve.edu.dto.ConversationDto;
import com.softserve.edu.dto.PostDto;
import com.softserve.edu.model.Conversation;
import com.softserve.edu.model.Post;
import com.softserve.edu.repositories.PostRepository;
import com.softserve.edu.service.PostService;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;
    
    @Override
    @Transactional
    public List<PostDto> findByConversationId(Long id) {
	List<PostDto> result = new ArrayList<PostDto>();
	List<Post> posts = (List<Post>) postRepository.findByConversationId(id);
	for (Post post : posts){
	    result.add(new PostDto(post));
	}
	return result;
    }
    
    @Override
    @Transactional
    public Post save(Post post) {
	return postRepository.save(post);
    }

    @Override
    @Transactional
    public void delete(Post post) {
	postRepository.delete(post);
    }

}
