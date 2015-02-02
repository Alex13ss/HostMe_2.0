package com.softserve.edu.dto;

import java.text.SimpleDateFormat;

import com.softserve.edu.model.Conversation;
import com.softserve.edu.model.Post;

public class ConversationDto {

    private Conversation conversation;

    private Post lastPost;
    
    //soon will be used
    //private Boolean isEditable;

    public ConversationDto() {

    }

    public ConversationDto(Conversation conversation, Post lastPost) {
	this.conversation = conversation;
	this.lastPost = lastPost;
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
