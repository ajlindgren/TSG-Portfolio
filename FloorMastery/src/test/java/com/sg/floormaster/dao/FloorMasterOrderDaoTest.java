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
import java.util.Map;
import java.util.stream.Collectors;
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
        
        dao.addOrder("99", order);
        
        assertEquals(dao.getOrder("99"), order);
        
        dao.clearMemory();
    }

    /**
     * Test of removeOrder method, of class FloorMasterOrderDao.
     */
    @Test
    public void testCancelOrder() throws Exception {
        dao.loadOrderFile(LocalDate.of(1212, 12, 12));
        
        Order order = new Order();
        dao.addOrder("2", order);
        dao.cancelOrder("2", order);
        
        assertEquals(dao.getOrder("1").getCustomerName(), "XXX");
        
        dao.clearMemory();
    }

    /**
     * Test of editOrder method, of class FloorMasterOrderDao.
     */
    @Test
    public void testEditOrder() {
        Order order = new Order();
        order.setOrderDate(LocalDate.of(1212,12,12));
        order.setOrderNumber("1");
        order.setCustomerName("XXX");
        order.setArea(new BigDecimal("100.00"));
        order.setProductType("Wood");
        order.setCostMaterialSquareFoot(new BigDecimal("5"));
        order.setCostLaborSquareFoot(new BigDecimal("5"));
        order.setState("OH");
        order.setTaxRate(new BigDecimal("5"));
        order.setMaterialCost(new BigDecimal("5"));
        order.setLaborCost(new BigDecimal("5"));
        order.setTax(new BigDecimal("5"));
        order.setTotal(new BigDecimal("5"));
        dao.addOrder("1", order);
        
        Order editedOrder = order;
        editedOrder.setArea(BigDecimal.ZERO);
        
        dao.editOrder("1", editedOrder);
        
        assertEquals(dao.getOrder("1").getArea(), BigDecimal.ZERO);
        
        dao.clearMemory();
    }

    /**
     * Test of getAllOrders method, of class FloorMasterOrderDao.
     */
    @Test
    public void testGetAllOrders() throws Exception {
        dao.loadOrderFile(LocalDate.of(1212, 12, 12));
        
        List<Order> orderList = dao.getAllOrders();
        
        assertTrue(orderList.size() == 1);
        
        dao.clearMemory();
    }

    /**
     * Test of getOrder method, of class FloorMasterOrderDao.
     */
    @Test
    public void testGetOrder() throws Exception {
        Order order = new Order();
        order.setOrderDate(LocalDate.of(1212,12,12));
        order.setOrderNumber("1");
        order.setCustomerName("XXX");
        order.setArea(new BigDecimal("100.00"));
        order.setProductType("Wood");
        order.setCostMaterialSquareFoot(new BigDecimal("5"));
        order.setCostLaborSquareFoot(new BigDecimal("5"));
        order.setState("OH");
        order.setTaxRate(new BigDecimal("5"));
        order.setMaterialCost(new BigDecimal("5"));
        order.setLaborCost(new BigDecimal("5"));
        order.setTax(new BigDecimal("5"));
        order.setTotal(new BigDecimal("5"));
        dao.addOrder("1", order);
        
        dao.addOrder("99", order);
        
        assertEquals(dao.getOrder("99"), order);
    }

    /**
     * Test of saveOrderFile method, of class FloorMasterOrderDao.
     */
    @Test
    public void testSaveOrderFile() throws Exception {
        
        dao.loadOrderFile(LocalDate.of(1212, 12, 12));
        dao.clearMemory();
        
        Order order = new Order();
        order.setOrderDate(LocalDate.of(1212,12,12));
        order.setOrderNumber("1");
        order.setCustomerName("XXX");
        order.setArea(new BigDecimal("100.00"));
        order.setProductType("Wood");
        order.setCostMaterialSquareFoot(new BigDecimal("5"));
        order.setCostLaborSquareFoot(new BigDecimal("5"));
        order.setState("OH");
        order.setTaxRate(new BigDecimal("5"));
        order.setMaterialCost(new BigDecimal("5"));
        order.setLaborCost(new BigDecimal("5"));
        order.setTax(new BigDecimal("5"));
        order.setTotal(new BigDecimal("5"));
        dao.addOrder("1", order);
        
        dao.saveOrderFile();
        
        int placeholder = dao.getAllOrders().size();
        
        Order order2 = new Order();
        order2.setOrderDate(LocalDate.of(1212,12,12));
        order2.setOrderNumber("2");
        order2.setCustomerName("YYY");
        order2.setArea(new BigDecimal("100.00"));
        order2.setProductType("Wood");
        order2.setCostMaterialSquareFoot(new BigDecimal("5"));
        order2.setCostLaborSquareFoot(new BigDecimal("5"));
        order2.setState("OH");
        order2.setTaxRate(new BigDecimal("5"));
        order2.setMaterialCost(new BigDecimal("5"));
        order2.setLaborCost(new BigDecimal("5"));
        order2.setTax(new BigDecimal("5"));
        order2.setTotal(new BigDecimal("5"));
        dao.addOrder("2", order2);
        
        dao.saveOrderFile();
        dao.clearMemory();
        dao.loadOrderFile(LocalDate.of(1212,12,12));
        
        assertTrue(dao.getAllOrders().size() > placeholder);
        
        dao.clearMemory();
        
        dao.addOrder("1", order);
        
        dao.saveOrderFile();
        
        assertTrue(dao.getAllOrders().size() == 1);
        
    }

    /**
     * Test of loadOrderFile method, of class FloorMasterOrderDao.
     */
    @Test
    public void testLoadOrderFile() throws FloorMasterPersistenceException {
        dao.loadOrderFile(LocalDate.of(1212, 12, 12));
        
        assertTrue(dao.getAllOrders().size() == 1);
    }
    
    /**
     * Test of loadAllOrderFiles method, of class FloorMasterOrderDao.
     */
    @Test
    public void testLoadAllOrderFiles() throws FloorMasterPersistenceException {
        dao.loadAllOrderFiles();
        List<LocalDate> dateList = dao.getAllOrders().stream()
                                .map(Order::getOrderDate)
                                .collect(Collectors.toList());
        assertTrue(dateList.size() > 1);
        dao.clearMemory();
    }
    
}
