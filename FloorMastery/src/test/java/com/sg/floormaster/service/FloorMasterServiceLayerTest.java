/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.service;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
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

    /**
     * Test of addOrder method, of class FloorMasterServiceLayer.
     */
    @Test
    public void testAddOrder() throws Exception {
        
    }

    /**
     * Test of removeOrder method, of class FloorMasterServiceLayer.
     */
    @Test
    public void testRemoveOrder() throws Exception {
    }

    /**
     * Test of editOrder method, of class FloorMasterServiceLayer.
     */
    @Test
    public void testEditOrder() throws Exception {
    }

    /**
     * Test of getAllOrders method, of class FloorMasterServiceLayer.
     */
    @Test
    public void testGetAllOrders() throws Exception {
    }

    /**
     * Test of getOrder method, of class FloorMasterServiceLayer.
     */
    @Test
    public void testGetOrder() throws Exception {
    }

    /**
     * Test of saveOrderFile method, of class FloorMasterServiceLayer.
     */
    @Test
    public void testSaveOrderFile() throws Exception {
    }

    /**
     * Test of loadOrderFile method, of class FloorMasterServiceLayer.
     */
    @Test
    public void testLoadOrderFile() throws Exception {
    }

    /**
     * Test of getAllMaterials method, of class FloorMasterServiceLayer.
     */
    @Test
    public void testGetAllMaterials() throws Exception {
    }

    /**
     * Test of getMaterial method, of class FloorMasterServiceLayer.
     */
    @Test
    public void testGetMaterial() throws Exception {
    }

    /**
     * Test of loadMaterialFile method, of class FloorMasterServiceLayer.
     */
    @Test
    public void testLoadMaterialFile() throws Exception {
    }

    /**
     * Test of getAllTaxes method, of class FloorMasterServiceLayer.
     */
    @Test
    public void testGetAllTaxes() throws Exception {
    }

    /**
     * Test of getTaxByState method, of class FloorMasterServiceLayer.
     */
    @Test
    public void testGetTaxByState() throws Exception {
    }

    /**
     * Test of loadTaxFile method, of class FloorMasterServiceLayer.
     */
    @Test
    public void testLoadTaxFile() throws Exception {
    }
    
}
