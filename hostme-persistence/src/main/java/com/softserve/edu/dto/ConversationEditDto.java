package com.softserve.edu.dto;

import java.util.List;

public class ConversationEditDto {
    
    private String title;
    
    private List<ModeratorDto> moderators;
    
    public ConversationEditDto() {
	
    }

    public ConversationEditDto(String title, List<ModeratorDto> moderators) {
	super();
	this.title = title;
	this.moderators = moderators;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ModeratorDto> getModerators() {
        return moderators;
    }

    public void setModerators(List<ModeratorDto> moderators) {
        this.moderators = moderators;
    }

}
