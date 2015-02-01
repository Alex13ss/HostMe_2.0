package com.softserve.edu.dto;

import java.util.List;

public class ConversationCreateDto {
    
    private String groupId;
    
    private String title;
    
    private String message;
    
    List<String> moderatorLogins;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getModeratorLogins() {
        return moderatorLogins;
    }

    public void setModeratorLogins(List<String> moderatorLogins) {
        this.moderatorLogins = moderatorLogins;
    }
    
}
