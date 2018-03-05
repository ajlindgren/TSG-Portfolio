/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.WebVenderPersistenceException;
import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Product;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Alex
 */
public interface WebVenderServiceLayer {
    
    List<Product> getAllProducts() throws WebVenderPersistenceException;
    
    Product getProduct(String productName) throws WebVenderPersistenceException, WebVenderZeroInventoryException;
    
    Change deposit(Change change, BigDecimal amount);

    Change deposit5(Change change);

    Change deposit1(Change change);

    Change depositQuarter(Change change);

    Change depositDime(Change change);

    Change depositNickel(Change change);
    
    BigDecimal[] calcChange(Product product, Change change) throws WebVenderInsufficientFundsException;
    
    BigDecimal[] calcRefund(Change change);

    void updateStock(Product product) throws WebVenderPersistenceException;
    
}
