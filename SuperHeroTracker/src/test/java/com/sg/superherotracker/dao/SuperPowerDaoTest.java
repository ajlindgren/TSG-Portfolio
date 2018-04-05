/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherotracker.dao;

import com.sg.superherotracker.model.Power;
import com.sg.superherotracker.model.Super;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Alex
 */
public class SuperPowerDaoTest {
    
    SuperPowerDao dao;
    
    public SuperPowerDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws SuperHeroTrackerDeleteDependencyException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
        "test-applicationContext.xml");
        
        dao = ctx.getBean("superPowerDao", SuperPowerDao.class);
        
        
        //delete all supers
        List<Super> supers = dao.getAllSupers();
        for (Super currentSuper : supers) {
            dao.deleteSuper(currentSuper.getSuperId());
        }
        //delete all powers
        List<Power> powers = dao.getAllPowers();
        for (Power currentPower : powers) {
            dao.deletePower(currentPower.getPowerId());
        }
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAddGetPower() {
        Power power = new Power();
        power.setDescription("Flight: Diaphanous Wings");
        
        dao.addPower(power);
        
        Power fromDao = dao.getPowerById(power.getPowerId());
        assertEquals(fromDao, power);
    }

    @Test
    public void testGetAllPowers() {
        Power power1 = new Power();
        power1.setDescription("Flight");
        
        Power power2 = new Power();
        power2.setDescription("Teleportation");
        
        Power power3 = new Power();
        power3.setDescription("Super Strength");
        
        dao.addPower(power1);
        dao.addPower(power2);
        dao.addPower(power3);
        
        assertEquals(dao.getAllPowers().size(), 3);
    }

    @Test
    public void testUpdatePower() {
        Power power = new Power();
        power.setDescription("Super Speed");
        
        dao.addPower(power);
        Power fromDao1 = dao.getPowerById(power.getPowerId());
        
        power.setDescription("Super Strength");
        
        dao.updatePower(power);
        Power fromDao2 = dao.getPowerById(power.getPowerId());
        
        assertNotEquals(fromDao1, fromDao2);
    }

    @Test
    public void testDeletePower() throws SuperHeroTrackerDeleteDependencyException {
        Power power = new Power();
        power.setDescription("Flight: Diaphanous Wings");
    
        dao.addPower(power);
        
        Power fromDao = dao.getPowerById(power.getPowerId());
        assertEquals(fromDao, power);
        dao.deletePower(power.getPowerId());
        assertNull(dao.getPowerById(power.getPowerId()));
    }

    @Test
    public void testAddGetSuper() {
        Power power = new Power();
        power.setDescription("Lazer Eyes");
        
        dao.addPower(power);
        
        Super xSuper = new Super();
        xSuper.setName("Cyclops");
        xSuper.setDescription("Leader, Blaster");
        xSuper.setPower(power);
        
        dao.addSuper(xSuper);
        
        Super fromDao = dao.getSuperById(xSuper.getSuperId());
        
        assertEquals(fromDao, xSuper);
    }

    @Test
    public void testGetAllSupers() {
        Power power1 = new Power();
        power1.setDescription("Flight: Diaphanous Wings");
        Power power2 = new Power();
        power2.setDescription("Teleportation");
        Power power3 = new Power();
        power3.setDescription("Super Strength");
        
        dao.addPower(power1);
        dao.addPower(power2);
        dao.addPower(power3);
        
        Super super1 = new Super();
        super1.setName("Moth Man");
        super1.setDescription("Wings like Moth");
        super1.setPower(power1);
        Super super2 = new Super();
        super2.setName("Nightcrawler");
        super2.setDescription("Space Panther");
        super2.setPower(power2);
        Super super3 = new Super();
        super3.setName("Super Man");
        super3.setDescription("God");
        super3.setPower(power3);
        
        dao.addSuper(super1);
        dao.addSuper(super2);
        dao.addSuper(super3);
        
        assertEquals(dao.getAllSupers().size(), 3);
    }

    @Test
    public void testUpdateSuper() {
        Power power = new Power();
        power.setDescription("Super Speed");
        
        dao.addPower(power);
        
        Super xSuper = new Super();
        xSuper.setName("The Flash");
        xSuper.setDescription("Savant, Speeder");
        xSuper.setPower(power);
        
        dao.addSuper(xSuper);
        
        Super fromDao1 = dao.getSuperById(xSuper.getSuperId());
        
        xSuper.setName("Super Man");
        
        dao.updateSuper(xSuper);
        
        Super fromDao2 = dao.getSuperById(xSuper.getSuperId());
        
        assertNotEquals(fromDao1, fromDao2);
    }

    @Test
    public void testDeleteSuper() {
        Power power = new Power();
        power.setDescription("Super Speed");
        
        dao.addPower(power);
        
        Super xSuper = new Super();
        xSuper.setName("The Flash");
        xSuper.setDescription("Savant, Speeder");
        xSuper.setPower(power);
        
        dao.addSuper(xSuper);
        
        Super fromDao = dao.getSuperById(xSuper.getSuperId());
        
        assertEquals(fromDao, xSuper);
        dao.deleteSuper(xSuper.getSuperId());
        assertNull(dao.getSuperById(xSuper.getSuperId()));
    }
    
}
