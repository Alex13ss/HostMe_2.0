package com.softserve.edu.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import com.softserve.edu.model.Post;

public interface PostRepository extends CrudRepository<Post, Long>{
    
    Post findFirstByConversationIdOrderByIdDesc(Long id);
    
    Iterable<Post> findByConversationId(Long id);
    
    Iterable<Post> findByPlaceId(Integer id);
    
    @Transactional("transactionManager")
    @Query(value = "select conversation_id from posts where id = ?1", nativeQuery=true)
    Long findConversationIdByPostId(Long id);
    
}
