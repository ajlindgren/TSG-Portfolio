/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.dao;

import com.sg.floormaster.dto.Material;
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
public class FloorMasterMaterialDaoTest {
    
    FloorMasterMaterialDao dao = new FloorMasterMaterialDaoFileImpl();
    
    public FloorMasterMaterialDaoTest() {
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
     * Test of loadMaterialFile method, of class FloorMasterMaterialDao.
     */
    @Test
    public void testLoadMaterialFile() throws Exception {
        dao.loadMaterialFile();
        List<Material> materialList = dao.getAllMaterials();
        assertTrue(materialList.size() > 0);
    }
    
    /**
     * Test of getAllMaterials method, of class FloorMasterMaterialDao.
     */
    @Test
    public void testGetAllMaterials() throws Exception {
        dao.loadMaterialFile();
        List<Material> materialList = dao.getAllMaterials();
        assertTrue(materialList.size() > 0);
    }

    /**
     * Test of getMaterial method, of class FloorMasterMaterialDao.
     */
    @Test
    public void testGetMaterial() throws Exception {
        dao.loadMaterialFile();
        Material material = dao.getMaterial("Carpet");
        assertNotNull(material);
    }

    
}
