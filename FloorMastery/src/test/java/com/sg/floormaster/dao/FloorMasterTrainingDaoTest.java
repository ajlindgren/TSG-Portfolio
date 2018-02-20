/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.dao;

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
public class FloorMasterTrainingDaoTest {
    
    FloorMasterTrainingDao dao = new FloorMasterTrainingDaoFileImpl();
    
    public FloorMasterTrainingDaoTest() {
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
     * Test of readTrainingConfig method, of class FloorMasterTrainingDao.
     */
    @Test
    public void testReadTrainingConfig() throws Exception {
        boolean test = dao.readTrainingConfig();
    }
    
}
