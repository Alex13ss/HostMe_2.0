package com.softserve.edu.service;

import com.softserve.edu.model.User;

public interface RegistrationSendMail {

    public void sendWelcomeMail(User user);
    
    public void sendNewPassMail(User user, String newpass);
}
