package com.softserve.edu.dao.impl;

import com.softserve.edu.dao.ContactDAO;
import com.softserve.edu.model.Contact;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ContactDAOImpl implements ContactDAO {
    
    @PersistenceContext()
    private EntityManager entityManager;

    public void addContact(Contact contact) {
	entityManager.persist(contact);
        //sessionFactory.getCurrentSession().save(contact);
    }

    @SuppressWarnings("unchecked")
    public List<Contact> listContact() {
	Session session = (Session) entityManager.getDelegate();
        return session.createQuery("from Contact").list();
    }

    public void removeContact(Integer id) {
	Session session = (Session) entityManager.getDelegate();
        Contact contact = (Contact) session.load(
                Contact.class, id);
        if (null != contact) {
            session.delete(contact);
        }
    }

}
