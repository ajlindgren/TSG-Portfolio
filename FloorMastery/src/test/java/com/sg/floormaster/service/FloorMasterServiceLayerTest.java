/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.service;

import com.sg.floormaster.dto.Material;
import com.sg.floormaster.dto.Order;
import com.sg.floormaster.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Alex
 */
public class FloorMasterServiceLayerTest {
    
    private FloorMasterServiceLayer service;
    
    public FloorMasterServiceLayerTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("service", FloorMasterServiceLayer.class);
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
    public void tearDown() {
    }

    
    // ======================= Order Dao Methods =============================
    
    
    /**
     * Test of addOrder method, of class FloorMasterServiceLayer.
     */
    @Test
    public void testAddOrder() throws Exception {
        Order order = new Order();
        order.setOrderNumber("2");
        service.addOrder(order.getOrderNumber(), order);
        
        assertEquals(order, service.getOrder(order.getOrderNumber()));
    }

    /**
     * Test of removeOrder method, of class FloorMasterServiceLayer.
     */
    @Test
    public void testCancelOrder() throws Exception {
        service.cancelOrder("1");
        
        assertEquals("(Cancelled)XXX", service.getOrder("1").getCustomerName());
    }

    /**
     * Test of editOrder method, of class FloorMasterServiceLayer.
     */
    @Test
    public void testEditOrder() throws Exception {
        service.getOrder("1").setArea(BigDecimal.ONE);
        service.editOrder("1", service.getOrder("1"));
        
        assertNotEquals(BigDecimal.ZERO, service.getOrder("1").getArea());
    }

    /**
     * Test of getAllOrders method, of class FloorMasterServiceLayer.
     */
    @Test
    public void testGetAllOrders() throws Exception {
        assertNotEquals(0, service.getAllOrders().size());
    }

    /**
     * Test of getOrder method, of class FloorMasterServiceLayer.
     */
    @Test
    public void testGetOrder() throws Exception {
        Order order = service.getOrder("1");
        assertNotNull(order);
        order = service.getOrder("9999");
        assertNull(order);
    }

    /**
     * Test of saveOrderFile method, of class FloorMasterServiceLayer.
     */
    @Test
    public void testSaveOrderFile() throws Exception {
        //no need to write to file in Stub Implementation
    }

    /**
     * Test of loadOrderFile method, of class FloorMasterServiceLayer.
     */
    @Test
    public void testLoadOrderFile() throws Exception {
        //no need to read from file in Stub Implementation
    }

    
    // ============================= Material Dao Methods =====================
    
    /**
     * Test of getAllMaterials method, of class FloorMasterServiceLayer.
     */
    @Test
    public void testGetAllMaterials() throws Exception {
        assertEquals(1, service.getAllMaterials().size());
    }

    /**
     * Test of getMaterial method, of class FloorMasterServiceLayer.
     */
    @Test
    public void testGetMaterial() throws Exception {
        Material material = service.getMaterial("Wood");
        assertNotNull(material);
        material = service.getMaterial("XXXX");
        assertNull(material);
    }

    /**
     * Test of loadMaterialFile method, of class FloorMasterServiceLayer.
     */
    @Test
    public void testLoadMaterialFile() throws Exception {
        //no need to read from file in Stub Implementation
    }

    
    // ======================== Tax Dao Methods ===============================
    
    
    /**
     * Test of getAllTaxes method, of class FloorMasterServiceLayer.
     */
    @Test
    public void testGetAllTaxes() throws Exception {
        assertEquals(1, service.getAllTaxes().size());
    }

    /**
     * Test of getTaxByState method, of class FloorMasterServiceLayer.
     */
    @Test
    public void testGetTaxByState() throws Exception {
        Tax tax = service.getTaxByState("OH");
        assertNotNull(tax);
        assertEquals(tax.getState(), "OH");
    }

    /**
     * Test of loadTaxFile method, of class FloorMasterServiceLayer.
     */
    @Test
    public void testLoadTaxFile() throws Exception {
        //no need to read from file in Stub Implementation
    }
    
    // ==================== Training Dao Methods ============================
    
    /**
     * Test of readConfigFile method, of class FloorMasterServiceLayer.
     */
    @Test
    public void testReadConfigFile() throws Exception {
        boolean test = service.readTrainingConfig();
    }
    
    // ==================== ServiceLayer Methods ============================
    /**
     * Test of calcOrder method, of class FloorMasterServiceLayer.
     */
    @Test
    public void testCalcOrder() {
        //first order, area = 1.00, cost material = 1.00, cost labor = 1.00, tax = 1%
        Tax tax = new Tax();
        tax.setState("XX");
        tax.setRate(BigDecimal.ONE);
        
        Material material = new Material();
        material.setMaterialType("XXXX");
        material.setCostMaterialSquareFoot(BigDecimal.ONE);
        material.setCostLaborSquareFoot(BigDecimal.ONE);
        
        Order order = new Order();
        order.setCustomerName("AAAA");
        order.setArea(BigDecimal.ONE);
        
        Order calculatedOrder = service.calcOrder(order, tax, material);

        assertEquals(calculatedOrder.getTotal(), new BigDecimal("2.02"));
        
        //second order, area = 100.00, cost material = 4.50, cost labor = 5.75, tax = 9%
        Tax tax2 = new Tax();
        tax2.setState("AA");
        tax2.setRate(new BigDecimal("9.00"));
        
        Material material2 = new Material();
        material2.setMaterialType("AAAA");
        material2.setCostMaterialSquareFoot(new BigDecimal("4.50"));
        material2.setCostLaborSquareFoot(new BigDecimal("5.75"));
        
        Order order2 = new Order();
        order2.setCustomerName("XXXX");
        order2.setArea(new BigDecimal("100.00"));
        
        Order calculatedOrder2 = service.calcOrder(order2, tax2, material2);
        
        assertEquals(calculatedOrder2.getTotal(), new BigDecimal("1117.25"));
        
    }
    
    /**
     * Test of calcOrderNumber method, of class FloorMasterServiceLayer.
     */
    @Test
    public void testCalcOrderNumber() {
        Order order = new Order();
        order.setOrderDate(LocalDate.parse("02152018", DateTimeFormatter.ofPattern("MMddyyyy")));
        
        Order numberedOrder = service.calcOrderNumber(order);
        assertNotNull(numberedOrder);
        assertTrue(numberedOrder.getOrderNumber().length() > numberedOrder.getOrderDate().format(DateTimeFormatter.ofPattern("MMddyyyy")).length());
    }
    
    /**
     * Test of validateTax method, of class FloorMasterServiceLayer.
     */
    @Test
    public void testValidateTax() {
        assertNotNull(service.validateTax("OH"));
        assertNull(service.validateTax("AL"));
        assertNull(service.validateTax("MN"));
    }
    
    /**
     * Test of validateMaterial method, of class FloorMasterServiceLayer.
     */
    @Test
    public void testValidateMaterial() {
        assertNotNull(service.validateMaterial("Wood"));
        assertNull(service.validateMaterial("XXXX"));
        assertNull(service.validateMaterial("Linoleum"));
    }
    
    /**
     * Test of validateArea method, of class FloorMasterServiceLayer.
     */
    @Test
    public void testValidateArea() {
        assertNotNull(service.validateArea("100"));
        assertNull(service.validateArea("9.9.9.9.9"));
        assertNull(service.validateArea(""));
    }
    
    /**
     * Test of validateOrderNumber method, of class FloorMasterServiceLayer.
     */
    @Test
    public void testValidateOrderNumber() {
        assertNotNull(service.validateOrderNumber("1"));
        assertNull(service.validateOrderNumber("0"));
        assertNull(service.validateOrderNumber("111111111"));
    }
}
