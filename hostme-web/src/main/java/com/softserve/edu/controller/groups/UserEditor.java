package com.softserve.edu.controller.groups;

import java.beans.PropertyEditorSupport;
import com.softserve.edu.model.User;
import com.softserve.edu.service.UserService;

public class UserEditor extends PropertyEditorSupport {

    private final UserService userService;

    public UserEditor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        User user = userService.getUser(Integer.parseInt(text));
        super.setValue(user);
    }

}
