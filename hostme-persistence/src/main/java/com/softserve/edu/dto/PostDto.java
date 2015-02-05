package com.softserve.edu.dto;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import com.softserve.edu.model.Post;

public class PostDto {
    
    private Long id;
    
    private String content;
    
    private Calendar postedAt;
    
    private String imageUrl;
    
    private String authorId;
    
    private String author;
    
    private boolean isRemovable;
    
    public PostDto() {
	
    }

    public PostDto(Post post, String imageUrl, boolean isRemovable) {
	this.id = post.getId();
	this.content = post.getContent();
	this.postedAt = post.getPostedAt();
	this.authorId = post.getAuthor().getUserId().toString();
	this.author = post.getAuthor().getFirstName() + " " + post.getAuthor().getLastName();
	this.imageUrl = imageUrl;
	this.isRemovable = isRemovable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Calendar getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Calendar postedAt) {
        this.postedAt = postedAt;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isRemovable() {
        return isRemovable;
    }

    public void setEditable(boolean isRemovable) {
        this.isRemovable = isRemovable;
    }
    
    public String getPostDate() {
	if (postedAt != null) {
	    return new SimpleDateFormat("yyyy-MM-dd").format(postedAt.getTime());
	}
	return null;
    }

    public String getPostTime() {
	if (postedAt != null) {
	    return new SimpleDateFormat("HH:mm:ss").format(postedAt.getTime());
	}
	return null;
    }

}
