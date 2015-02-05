package com.softserve.edu.service;

import java.util.List;
import com.softserve.edu.dto.PostDto;
import com.softserve.edu.model.Post;

public interface PostService {
    
    List<PostDto> findByConversationId(Long id);
    
    List<PostDto> findByPlaceId(Integer id);
    
    Post findOne(Long id);
    
    Post save(Post post);
    
    void delete(Post post);   
    
    Long findConversationIdByPostId(Long id);    

}
