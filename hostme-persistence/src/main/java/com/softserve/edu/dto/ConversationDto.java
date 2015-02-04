package com.softserve.edu.dto;

import java.text.SimpleDateFormat;

import org.springframework.security.core.context.SecurityContextHolder;

import com.softserve.edu.model.Conversation;
import com.softserve.edu.model.Post;

public class ConversationDto {

    private Conversation conversation;

    private Post lastPost;
    
    private Boolean isEditable;

    public ConversationDto() {

    }

    public ConversationDto(Conversation conversation, Post lastPost) {
	this.conversation = conversation;
	this.lastPost = lastPost;
	if (conversation.getOwner().getLogin().equals(
		SecurityContextHolder.getContext().getAuthentication().getName()))
	{
	    this.isEditable = true;
	}
    }

    public Conversation getConversation() {
	return conversation;
    }

    public void setConversation(Conversation conversation) {
	this.conversation = conversation;
    }

    public Post getLastPost() {
	return lastPost;
    }

    public void setLastPost(Post lastPost) {
	this.lastPost = lastPost;
    }

    public Boolean getIsEditable() {
        return isEditable;
    }

    public void setIsEditable(Boolean isEditable) {
        this.isEditable = isEditable;
    }

    public String getLastPostDate() {
	if (lastPost != null && lastPost.getPostedAt() != null) {
	    return new SimpleDateFormat("yyyy-MM-dd").format(lastPost
		    .getPostedAt().getTime());
	}
	return null;
    }

    public String getLastPostTime() {
	if (lastPost != null && lastPost.getPostedAt() != null) {
	    return new SimpleDateFormat("HH:mm:ss").format(lastPost
		    .getPostedAt().getTime());
	}
	return null;
    }

}
