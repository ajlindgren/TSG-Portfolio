/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import com.sg.vendingmachinespringmvc.model.Product;

/**
 *
 * @author Alex
 */
public class WebVenderDaoTest {
    
    private WebVenderDao dao = new WebVenderDaoFileImpl();
    
    public WebVenderDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        List<Product> productList = dao.getAllProducts();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllProducts method, of class WebVenderDao.
     */
    //@Test
    public void testGetAllProducts() throws Exception {
        List<Product> fromDao = dao.getAllProducts();
        
        assertEquals(5, fromDao.size());
    }

    /**
     * Test of getProduct method, of class WebVenderDao.
     */
    //@Test
    public void testGetProduct() throws Exception {
        Product product = new Product("TEST0");
        product.setPrice(BigDecimal.ZERO);
        product.setQuantity(0);
        
        Product fromDao = dao.getProduct("TEST0");
        
        assertEquals(product, fromDao);
    }
    
    //@Test
    public void testUpdateInventory() throws Exception {
        Product product = new Product("TEST1");
        product.setPrice(BigDecimal.ONE);
        product.setQuantity(0);
        
        assertEquals(dao.updateInventory(product), product);
    }

}
