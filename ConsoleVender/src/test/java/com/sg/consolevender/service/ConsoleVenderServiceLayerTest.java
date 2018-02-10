/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.consolevender.service;

import com.sg.consolevender.dao.ConsoleVenderAuditDao;
import com.sg.consolevender.dao.ConsoleVenderAuditDaoFileImpl;
import com.sg.consolevender.dao.ConsoleVenderDao;
import com.sg.consolevender.dao.ConsoleVenderDaoStubImpl;
import com.sg.consolevender.dto.Change;
import static com.sg.consolevender.dto.Coin.DIME;
import static com.sg.consolevender.dto.Coin.NICKEL;
import static com.sg.consolevender.dto.Coin.QUARTER;
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
public class ConsoleVenderServiceLayerTest {
    
    private ConsoleVenderServiceLayer service;
    
    public ConsoleVenderServiceLayerTest() {
        ConsoleVenderDao dao = new ConsoleVenderDaoStubImpl();
        ConsoleVenderAuditDao auditDao = new ConsoleVenderAuditDaoFileImpl();
        service = new ConsoleVenderServiceLayerImpl(dao, auditDao);
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
     * Test of getAllProducts method, of class ConsoleVenderServiceLayer.
     */
    @Test
    public void testGetAllProducts() throws Exception {
        List<Product> fromDao = service.getAllProducts();
        
        assertEquals(2, fromDao.size());
    }

    /**
     * Test of getProduct method, of class ConsoleVenderServiceLayer.
     */
    @Test
    public void testGetProduct() throws Exception {
        Product product = new Product("TEST1");
        product.setProductPrice(BigDecimal.ONE);
        product.setInStock(1);
        
        Product fromDao = service.getProduct("TEST1");
        
        assertEquals(product, fromDao);
    }

    /**
     * Test of deposit5 method, of class ConsoleVenderServiceLayer.
     */
    @Test
    public void testDeposit5() {
        Change change = new Change();
        
        change.addToTotal(new BigDecimal("5"));
        
        assertEquals(new BigDecimal("5"), change.getTotal());
    }

    /**
     * Test of deposit1 method, of class ConsoleVenderServiceLayer.
     */
    @Test
    public void testDeposit1() {
        Change change = new Change();
        
        change.addToTotal(new BigDecimal("1"));
        
        assertEquals(new BigDecimal("1"), change.getTotal());
    }

    /**
     * Test of depositQuarter method, of class ConsoleVenderServiceLayer.
     */
    @Test
    public void testDepositQuarter() {
        Change change = new Change();
        
        change.addToTotal(QUARTER.getValue());
        
        assertEquals(new BigDecimal("0.25"), change.getTotal());
    }

    /**
     * Test of depositDime method, of class ConsoleVenderServiceLayer.
     */
    @Test
    public void testDepositDime() {
        Change change = new Change();
        
        change.addToTotal(DIME.getValue());
        
        assertEquals(new BigDecimal("0.10"), change.getTotal());
    }

    /**
     * Test of depositNickel method, of class ConsoleVenderServiceLayer.
     */
    @Test
    public void testDepositNickel() {
        Change change = new Change();
        
        change.addToTotal(NICKEL.getValue());
        
        assertEquals(new BigDecimal("0.05"), change.getTotal());
    }
    
    /**
     * Test of calcChange method, of class ConsoleVenderServiceLayer.
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
     * Test of calcRefund method, of class ConsoleVenderServiceLayer.
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
