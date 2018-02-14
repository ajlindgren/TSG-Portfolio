/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.dao;

import com.sg.floormaster.dto.Tax;
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
public class FloorMasterTaxDaoTest {
    
    FloorMasterTaxDao dao = new FloorMasterTaxDaoFileImpl();
    
    public FloorMasterTaxDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {    
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        dao.loadTaxFile();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllTaxes method, of class FloorMasterTaxDao.
     */
    @Test
    public void testGetAllTaxes() throws Exception {
        
        List<Tax> taxList = dao.getAllTaxes();
        assertTrue(taxList.size() > 0);
    }

    /**
     * Test of getTaxByState method, of class FloorMasterTaxDao.
     */
    @Test
    public void testGetTaxByState() throws Exception {
        
        Tax byState = dao.getTaxByState("OH");
        assertTrue(byState.getState().compareToIgnoreCase("OH") == 0);
    }

}
