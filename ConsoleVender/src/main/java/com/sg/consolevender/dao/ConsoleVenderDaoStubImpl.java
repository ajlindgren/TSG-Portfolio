/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.consolevender.dao;

import com.sg.consolevender.dto.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alex
 */
public class ConsoleVenderDaoStubImpl implements ConsoleVenderDao {
    
    Product test0;    
    Product test1;
    Product test2;
    
    List<Product> productList = new ArrayList<>();
    
    public ConsoleVenderDaoStubImpl() {
        test0 = new Product("TEST0");
        test0.setProductPrice(BigDecimal.ZERO);
        test0.setInStock(0);
        
        productList.add(test0);
        
        test1 = new Product("TEST1");
        test1.setProductPrice(BigDecimal.ONE);
        test1.setInStock(1);
        
        productList.add(test1);
        
        test2 = new Product("TEST2");
        test2.setProductPrice(BigDecimal.ZERO);
        test2.setInStock(2);
        
        productList.add(test2);
    }

    @Override
    public List<Product> getAllProducts() throws ConsoleVenderPersistenceException {
        return productList;
    }

    @Override
    public Product getProduct(String productName) throws ConsoleVenderPersistenceException {
        
        if (productName.equals(test0.getProductName())) {
            return test0;
        } else if (productName.equals(test1.getProductName())) {
            return test1;
        } else if (productName.equals(test2.getProductName())) {
            return test2;
        } else
            return null;
    }

    @Override
    public Product updateInventory(Product product) throws ConsoleVenderPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
