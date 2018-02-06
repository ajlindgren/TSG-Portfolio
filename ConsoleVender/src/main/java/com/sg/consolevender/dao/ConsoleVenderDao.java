/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.consolevender.dao;

import com.sg.consolevender.dto.Product;
import java.util.List;

/**
 *
 * @author Alex
 */
public interface ConsoleVenderDao {
    
    List<Product> getAllProducts() throws ConsoleVenderPersistenceException;
    
    Product getProduct(String productName) throws ConsoleVenderPersistenceException;
    
    Product updateInventory(Product product) throws ConsoleVenderPersistenceException;
    
}
