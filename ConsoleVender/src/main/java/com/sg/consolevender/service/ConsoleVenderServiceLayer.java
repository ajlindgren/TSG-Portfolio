/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.consolevender.service;

import com.sg.consolevender.dao.ConsoleVenderPersistenceException;
import com.sg.consolevender.dto.Change;
import com.sg.consolevender.dto.Product;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Alex
 */
public interface ConsoleVenderServiceLayer {
    
    List<Product> getAllProducts() throws ConsoleVenderPersistenceException;
    
    Product getProduct(String productName) throws ConsoleVenderPersistenceException, ConsoleVenderZeroInventoryException;

    Change deposit5(Change change);

    Change deposit1(Change change);

    Change depositQuarter(Change change);

    Change depositDime(Change change);

    Change depositNickel(Change change);
    
    BigDecimal[] calcChange(Product product, Change change) throws ConsoleVenderInsufficientFundsException;
    
    BigDecimal[] calcRefund(Change change);

    void updateStock(Product product) throws ConsoleVenderPersistenceException;
    
}
