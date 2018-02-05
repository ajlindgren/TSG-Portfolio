/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.consolevender.dao;

import com.sg.consolevender.dto.Product;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alex
 */
public class ConsoleVenderDaoTest {
    
    private ConsoleVenderDao dao = new ConsoleVenderDaoStubImpl();
    
    public ConsoleVenderDaoTest() {
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
     * Test of getAllProducts method, of class ConsoleVenderDao.
     */
    @Test
    public void testGetAllProducts() throws Exception {
        List<Product> fromDao = dao.getAllProducts();
        
        assertEquals(3, fromDao.size());
    }

    /**
     * Test of getProduct method, of class ConsoleVenderDao.
     */
    @Test
    public void testGetProduct() throws Exception {
        Product product = new Product("TEST1");
        product.setProductPrice(BigDecimal.ONE);
        product.setInStock(1);
        
        Product fromDao = dao.getProduct("TEST1");
        
        assertEquals(product, fromDao);
    }

    /**
     * Test of loadInventory method, of class ConsoleVenderDao.
     */
    @Test
    public void testLoadInventory() throws Exception {
    }

    /**
     * Test of writeInventory method, of class ConsoleVenderDao.
     */
    @Test
    public void testWriteInventory() throws Exception {
    }
    
}
