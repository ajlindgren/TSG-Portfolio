/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.contactlistspringboot.dao;

import com.sg.contactlistspringboot.model.Contact;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author ward
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactListDaoTest {

    @Autowired
    private ContactRepository dao;
    //private ContactListDao dao;

    public ContactListDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        dao.deleteAll();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addGetDeleteContact() {
        // Create new contact
        Contact nc = new Contact();
        nc.setFirstName("John");
        nc.setLastName("Doe");
        nc.setCompany("Oracle");
        nc.setEmail("john@doe.com");
        nc.setPhone("1234445678");
        dao.save(nc);
        Contact fromDb = dao.findById(nc.getContactId()).orElse(null);
        assertEquals(fromDb, nc);
        dao.deleteById(nc.getContactId());
        assertNull(dao.findById(nc.getContactId()).orElse(null));
    }

    @Test
    public void addUpdateContact() {
        // Create new contact
        Contact nc = new Contact();
        nc.setFirstName("Jimmy");
        nc.setLastName("Smith");
        nc.setCompany("Sun");
        nc.setEmail("jimmy@smith.com");
        nc.setPhone("1112223333");
        dao.save(nc);
        nc.setPhone("9999999999");
        dao.save(nc);
        Contact fromDb = dao.findById(nc.getContactId()).orElse(null);
        assertEquals(fromDb, nc);
    }

    @Test
    public void getAllContacts() {
        // Create new contact
        Contact nc = new Contact();
        nc.setFirstName("Jimmy");
        nc.setLastName("Smith");
        nc.setCompany("Sun");
        nc.setEmail("jimmy@smith.com");
        nc.setPhone("1112223333");
        dao.save(nc);
        // Create new contact
        Contact nc2 = new Contact();
        nc2.setFirstName("John");
        nc2.setLastName("Jones");
        nc2.setCompany("Apple");
        nc2.setEmail("john@jones.com");
        nc2.setPhone("5556667777");
        dao.save(nc2);
        List<Contact> cList = dao.findAll();
        assertEquals(cList.size(), 2);
    }

    @Test
    public void searchContacts() {
        // Create new contact
        Contact nc = new Contact();
        nc.setFirstName("Jimmy");
        nc.setLastName("Smith");
        nc.setCompany("Sun");
        nc.setEmail("jimmy@smith.com");
        nc.setPhone("1112223333");
        nc = dao.save(nc);
        // Create new contact
        Contact nc2 = new Contact();
        nc2.setFirstName("John");
        nc2.setLastName("Jones");
        nc2.setCompany("Apple");
        nc2.setEmail("john@jones.com");
        nc2.setPhone("5556667777");
        nc2 = dao.save(nc2);
        // Create new contact - same last name as first 
        // contact but different company
        Contact nc3 = new Contact();
        nc3.setFirstName("Steve");
        nc3.setLastName("Smith");
        nc3.setCompany("Microsoft");
        nc3.setEmail("steve@msft.com");
        nc3.setPhone("5552221234");
        nc3 = dao.save(nc3);
        // Create search criteria
        Contact contact = new Contact();
        contact.setLastName("Jones");
        List<Contact> cList = dao.findAll(Example.of(contact, ExampleMatcher.matching().withIgnoreNullValues().withIgnorePaths("contactId")));
        assertEquals(1, cList.size());
        assertEquals(nc2, cList.get(0));

        // New search criteria - look for Smith
        contact.setLastName("Smith");
        cList = dao.findAll(Example.of(contact, ExampleMatcher.matching().withIgnoreNullValues().withIgnorePaths("contactId")));
        assertEquals(2, cList.size());

        // Add company to search criteria
        contact.setCompany("Sun");
        cList = dao.findAll(Example.of(contact, ExampleMatcher.matching().withIgnoreNullValues().withIgnorePaths("contactId")));
        assertEquals(1, cList.size());
        assertEquals(nc, cList.get(0));

        // Change company to Microsoft, should get back nc3
        contact.setCompany("Microsoft");
        cList = dao.findAll(Example.of(contact, ExampleMatcher.matching().withIgnoreNullValues().withIgnorePaths("contactId")));
        assertEquals(1, cList.size());
        assertEquals(nc3, cList.get(0));
        // Change company to Apple, should get back nothing
        contact.setCompany("Apple");
        cList = dao.findAll(Example.of(contact, ExampleMatcher.matching().withIgnoreNullValues().withIgnorePaths("contactId")));
        assertEquals(0, cList.size());
    }
}