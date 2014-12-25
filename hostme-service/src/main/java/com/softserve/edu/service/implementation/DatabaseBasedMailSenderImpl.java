package com.softserve.edu.service.implementation;

import com.softserve.edu.service.SystemPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class DatabaseBasedMailSenderImpl extends JavaMailSenderImpl{

    @Autowired
    private SystemPropertiesService systemProperties;

    @Override
    public String getPassword() {
        return systemProperties.getMailPass();
    }

    @Override
    public String getUsername() {
        return systemProperties.getMailUsername();
    }

}
