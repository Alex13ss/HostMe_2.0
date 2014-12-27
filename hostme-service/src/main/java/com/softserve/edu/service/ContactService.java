package com.softserve.edu.service;

import com.softserve.edu.model.Contact;

import java.util.List;

public interface ContactService {
    
    public void addContact(Contact contact);

    public List<Contact> listContact();

    public void removeContact(Integer id);
    
}
