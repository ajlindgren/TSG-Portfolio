/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alex
 */
public class WebVenderDaoStubImpl implements WebVenderDao {
    
    Product test0;    
    Product test1;
    Product test2;
    
    List<Product> productList = new ArrayList<>();
    
    public WebVenderDaoStubImpl() {
        test0 = new Product("TEST0");
        test0.setPrice(BigDecimal.ZERO);
        test0.setQuantity(1);
        
        productList.add(test0);
        
        test1 = new Product("TEST1");
        test1.setPrice(BigDecimal.ONE);
        test1.setQuantity(1);
        
        productList.add(test1);
        
        test2 = new Product("TEST2");
        test2.setPrice(BigDecimal.ZERO);
        test2.setQuantity(2);
        
        productList.add(test2);
    }

    @Override
    public List<Product> getAllProducts() throws WebVenderPersistenceException {
        return productList;
    }

    @Override
    public Product getProduct(String productName) throws WebVenderPersistenceException {
        
        if (productName.equals(test0.getName())) {
            return test0;
        } else if (productName.equals(test1.getName())) {
            return test1;
        } else if (productName.equals(test2.getName())) {
            return test2;
        } else
            return null;
    }

    @Override
    public Product updateInventory(Product product) throws WebVenderPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
