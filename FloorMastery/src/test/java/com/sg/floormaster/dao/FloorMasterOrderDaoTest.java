/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.dao;

import com.sg.floormaster.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
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
public class FloorMasterOrderDaoTest {
    
    private FloorMasterOrderDao dao = new FloorMasterOrderDaoFileImpl();
    
    public FloorMasterOrderDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() throws Exception {
        
    }

    /**
     * Test of addOrder method, of class FloorMasterOrderDao.
     */
    @Test
    public void testAddOrder() throws Exception {
        Order order = new Order();
        
        dao.loadOrderFile(LocalDate.of(1212, 12, 12));
        
        dao.addOrder(99, order);
        
        assertEquals(dao.getOrder(99), order);
    }

    /**
     * Test of removeOrder method, of class FloorMasterOrderDao.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        Order order = new Order();
        
        dao.loadOrderFile(LocalDate.of(1212, 12, 12));
        
        dao.addOrder(99, order);
        
        int placeholder = dao.getAllOrders().size();
        
        dao.removeOrder(99);
        
        assertTrue(dao.getAllOrders().size() < placeholder);
    }

    /**
     * Test of editOrder method, of class FloorMasterOrderDao.
     */
    @Test
    public void testEditOrder() throws Exception {
        Order order = new Order();
        order.setArea(BigDecimal.ONE);
        
        Order editedOrder = new Order();
        editedOrder.setArea(BigDecimal.ZERO);
        
        dao.loadOrderFile(LocalDate.of(1212, 12, 12));
        
        dao.addOrder(99, order);
        dao.editOrder(99, editedOrder);
        
        assertNotEquals(order, dao.getOrder(99));
    }

    /**
     * Test of getAllOrders method, of class FloorMasterOrderDao.
     */
    @Test
    public void testGetAllOrders() throws Exception {
        dao.loadOrderFile(LocalDate.of(1212, 12, 12));
        
        List<Order> orderList = dao.getAllOrders();
        
        assertTrue(orderList.size() > 0);
    }

    /**
     * Test of getOrder method, of class FloorMasterOrderDao.
     */
    @Test
    public void testGetOrder() throws Exception {
        Order order = new Order();
        
        dao.addOrder(99, order);
        
        assertEquals(dao.getOrder(99), order);
    }

    /**
     * Test of saveOrderFile method, of class FloorMasterOrderDao.
     */
    @Test
    public void testSaveOrderFile() throws Exception {
        dao.loadOrderFile(LocalDate.of(1212, 12, 12));
        for (Order currentOrder : dao.getAllOrders())
            currentOrder.setOrderDate(LocalDate.of(1212, 12, 12));
        
        int placeholder = dao.getAllOrders().size();
        
        Order order = new Order();
        order.setOrderDate(LocalDate.of(1212,12,12));
        order.setOrderNumber(2);
        order.setArea(BigDecimal.ZERO);
        order.setCostMaterialSquareFoot(BigDecimal.ZERO);
        order.setCostLaborSquareFoot(BigDecimal.ZERO);
        order.setTaxRate(BigDecimal.ZERO);
        order.setMaterialCost(BigDecimal.ZERO);
        order.setLaborCost(BigDecimal.ZERO);
        order.setTax(BigDecimal.ZERO);
        order.setTotal(BigDecimal.ZERO);
        
        dao.addOrder(2, order);
        
        dao.saveOrderFile();
        dao.loadOrderFile(LocalDate.of(1212,12,12));
        
        assertTrue(dao.getAllOrders().size() > placeholder);
        
    }

    /**
     * Test of loadOrderFile method, of class FloorMasterOrderDao.
     */
    @Test
    public void testLoadOrderFile() throws Exception {
        dao.loadOrderFile(LocalDate.of(1212, 12, 12));
        
        assertTrue(dao.getAllOrders().size() > 0);
    }
    
}
