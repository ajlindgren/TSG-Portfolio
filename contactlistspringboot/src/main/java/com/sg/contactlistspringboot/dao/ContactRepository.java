/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.contactlistspringboot.dao;

import com.sg.contactlistspringboot.model.Contact;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Alex
 */
public interface ContactRepository extends JpaRepository<Contact, Long>{
    List<Contact> findByLastName(String lastname);
}
