/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.WebVenderAuditDaoFileImpl;
import com.sg.vendingmachinespringmvc.dao.WebVenderDaoStubImpl;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import com.sg.vendingmachinespringmvc.dao.WebVenderAuditDao;
import com.sg.vendingmachinespringmvc.dao.WebVenderDao;
import com.sg.vendingmachinespringmvc.model.Change;
import static com.sg.vendingmachinespringmvc.model.Coin.DIME;
import static com.sg.vendingmachinespringmvc.model.Coin.NICKEL;
import static com.sg.vendingmachinespringmvc.model.Coin.QUARTER;
import com.sg.vendingmachinespringmvc.model.Product;

/**
 *
 * @author Alex
 */
public class WebVenderServiceLayerTest {
    
    private WebVenderServiceLayer service;
    
    public WebVenderServiceLayerTest() {
        WebVenderDao dao = new WebVenderDaoStubImpl();
        WebVenderAuditDao auditDao = new WebVenderAuditDaoFileImpl();
        service = new WebVenderServiceLayerImpl(dao);
    }
    
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        List<Product> productList = service.getAllProducts();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllProducts method, of class WebVenderServiceLayer.
     */
    @Test
    public void testGetAllProducts() throws Exception {
        List<Product> fromDao = service.getAllProducts();
        
        assertEquals(3, fromDao.size());
    }

    /**
     * Test of getProduct method, of class WebVenderServiceLayer.
     */
    @Test
    public void testGetProduct() throws Exception {
        Product product = new Product("TEST1");
        product.setPrice(BigDecimal.ONE);
        product.setQuantity(1);
        
        Product fromDao = service.getProduct("TEST1");
        
        assertEquals(product, fromDao);
    }

    /**
     * Test of deposit5 method, of class WebVenderServiceLayer.
     */
    @Test
    public void testDeposit5() {
        Change change = new Change();
        
        change.addToTotal(new BigDecimal("5"));
        
        assertEquals(new BigDecimal("5"), change.getTotal());
    }

    /**
     * Test of deposit1 method, of class WebVenderServiceLayer.
     */
    @Test
    public void testDeposit1() {
        Change change = new Change();
        
        change.addToTotal(new BigDecimal("1"));
        
        assertEquals(new BigDecimal("1"), change.getTotal());
    }

    /**
     * Test of depositQuarter method, of class WebVenderServiceLayer.
     */
    @Test
    public void testDepositQuarter() {
        Change change = new Change();
        
        change.addToTotal(QUARTER.getValue());
        
        assertEquals(new BigDecimal("0.25"), change.getTotal());
    }

    /**
     * Test of depositDime method, of class WebVenderServiceLayer.
     */
    @Test
    public void testDepositDime() {
        Change change = new Change();
        
        change.addToTotal(DIME.getValue());
        
        assertEquals(new BigDecimal("0.10"), change.getTotal());
    }

    /**
     * Test of depositNickel method, of class WebVenderServiceLayer.
     */
    @Test
    public void testDepositNickel() {
        Change change = new Change();
        
        change.addToTotal(NICKEL.getValue());
        
        assertEquals(new BigDecimal("0.05"), change.getTotal());
    }
    
    /**
     * Test of calcChange method, of class WebVenderServiceLayer.
     */
    @Test
    public void testCalcChange() throws Exception {
        Change change = new Change();
        change.setTotal(new BigDecimal("1.15"));
        
        Product product = service.getProduct("TEST0");
        BigDecimal[] test = service.calcChange(product, change);
        assertEquals(new BigDecimal("4"), test[0]);
        assertEquals(BigDecimal.ONE, test[1]);
        assertEquals(BigDecimal.ONE, test[2]);
        assertEquals(new BigDecimal("1.15"), change.getTotal());
    }
    
    /**
     * Test of calcRefund method, of class WebVenderServiceLayer.
     */
    @Test
    public void testCalcRefund() throws Exception {
        Change change = new Change();
        change.setTotal(new BigDecimal("1.15"));
        
        BigDecimal[] test = service.calcRefund(change);
        assertEquals(new BigDecimal("4"), test[0]);
        assertEquals(BigDecimal.ONE, test[1]);
        assertEquals(BigDecimal.ONE, test[2]);
        assertEquals(new BigDecimal("1.15"), change.getTotal());
    }
    
}
