/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Product;
import java.util.List;

/**
 *
 * @author Alex
 */
public interface WebVenderDao {
    
    List<Product> getAllProducts() throws WebVenderPersistenceException;
    
    Product getProduct(String productName) throws WebVenderPersistenceException;
    
    Product updateInventory(Product product) throws WebVenderPersistenceException;
    
}
