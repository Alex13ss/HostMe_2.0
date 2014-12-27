package com.softserve.edu.dao;

import com.softserve.edu.model.Contact;

import java.util.List;

public interface ContactDAO {
    
    public void addContact(Contact contact);

    public List<Contact> listContact();

    public void removeContact(Integer id);
    
}
