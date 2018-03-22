/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherotracker.dao;

import com.sg.superherotracker.model.Headquarters;
import com.sg.superherotracker.model.Organization;
import com.sg.superherotracker.model.Power;
import com.sg.superherotracker.model.Super;
import java.util.ArrayList;
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
public class OrganizationHqDaoTest {
    
    OrganizationHqDao dao;
    SuperPowerDao spDao;
    
    public OrganizationHqDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext (
        "test-applicationContext.xml");
        
        dao = ctx.getBean("organizationHqDao", OrganizationHqDao.class);
        spDao = ctx.getBean("superPowerDao", SuperPowerDao.class);
        
        
        //delete all supers
        List<Super> supers = spDao.getAllSupers();
        for (Super currentSuper : supers) {
            spDao.deleteSuper(currentSuper.getSuperId());
        }
        //delete all powers
        List<Power> powers = spDao.getAllPowers();
        for (Power currentPower : powers) {
            spDao.deletePower(currentPower.getPowerId());
        }
        //delete all organizations
        List<Organization> orgs = dao.getAllOrganizations();
        for (Organization currentOrg : orgs) {
            dao.deleteOrganization(currentOrg.getOrganizationId());
        }
        //delete all headquarters
        List<Headquarters> hqs = dao.getAllHeadquarters();
        for (Headquarters currentHq : hqs) {
            dao.deleteHeadquarters(currentHq.getHeadquartersId());
        }
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAddGetHeadquarters() {
        Headquarters hq = new Headquarters();
        hq.setAddress("123 Super Street");
        hq.setPlanet("Earth");
        
        dao.addHeadquarters(hq);
        
        Headquarters fromDao = dao.getHeadquartersById(hq.getHeadquartersId());
        assertEquals(fromDao, hq);
    }

    @Test
    public void testGetAllHeadquarters() {
        Headquarters hq1 = new Headquarters();
        hq1.setAddress("123 Super Street");
        hq1.setPlanet("Earth");
        Headquarters hq2 = new Headquarters();
        hq2.setAddress("124 Super Ave");
        hq2.setPlanet("Mars");
        
        dao.addHeadquarters(hq1);
        dao.addHeadquarters(hq2);
        
        assertEquals(dao.getAllHeadquarters().size(), 2);
    }

    @Test
    public void testUpdateHeadquarters() {
        Headquarters hq = new Headquarters();
        hq.setAddress("123 Super Street");
        hq.setPlanet("Earth");
        
        dao.addHeadquarters(hq);
        Headquarters fromDao1 = dao.getHeadquartersById(hq.getHeadquartersId());
        
        hq.setPlanet("Mars");
        
        dao.updateHeadquarters(hq);
        Headquarters fromDao2 = dao.getHeadquartersById(hq.getHeadquartersId());
        
        assertNotEquals(fromDao1, fromDao2);
    }

    @Test
    public void testDeleteHeadquarters() {
        Headquarters hq = new Headquarters();
        hq.setAddress("123 Super Street");
        hq.setPlanet("Earth");
        
        dao.addHeadquarters(hq);
        
        Headquarters fromDao = dao.getHeadquartersById(hq.getHeadquartersId());
        assertEquals(fromDao, hq);
        dao.deleteHeadquarters(hq.getHeadquartersId());
        assertNull(dao.getHeadquartersById(hq.getHeadquartersId()));
    }

    @Test
    public void testAddGetOrganization() {
        Headquarters hq = new Headquarters();
        hq.setAddress("133 Super Street");
        hq.setPlanet("Earth");
        
        dao.addHeadquarters(hq);

        List<Super> supers = spDao.getAllSupers();
        
        Organization org = new Organization();
        org.setName("Avengers");
        org.setDescription("We avenge things");
        org.setEmail("avengers@secret.com");
        org.setHeadquarters(hq);
        org.setSupers(supers);
        
        dao.addOrganization(org);
        
        Organization fromDao = dao.getOrganizationById(org.getOrganizationId());
        assertEquals(fromDao, org);
    }

    @Test
    public void testGetOrganizationsBySuperId() {
        Headquarters hq = new Headquarters();
        hq.setAddress("123 Super Street");
        hq.setPlanet("Earth");
        
        dao.addHeadquarters(hq);
        
        Power power = new Power();
        power.setDescription("Super Strength");
        
        spDao.addPower(power);
        
        Super super1 = new Super();
        super1.setName("Super Man");
        super1.setDescription("God");
        super1.setPower(spDao.getPowerById(power.getPowerId()));
        Super super2 = new Super();
        super2.setName("The Hulk");
        super2.setDescription("Green");
        super2.setPower(spDao.getPowerById(power.getPowerId()));
        
        spDao.addSuper(super1);
        spDao.addSuper(super2);
        
        List<Super> supers = spDao.getAllSupers();
        
        Organization org = new Organization();
        org.setName("Avengers");
        org.setDescription("We avenge things");
        org.setEmail("avengers@secret.com");
        org.setHeadquarters(hq);
        org.setSupers(supers);
        
        dao.addOrganization(org);
        
        assertEquals(dao.getOrganizationsBySuperId(super1.getSuperId()),
                dao.getOrganizationsBySuperId(super2.getSuperId()));
    }

    @Test
    public void testGetOrganizationsByHeadquartersId() {
        Headquarters hq1 = new Headquarters();
        hq1.setAddress("123 Super Street");
        hq1.setPlanet("Earth");
        
        dao.addHeadquarters(hq1);
        
        Power power = new Power();
        power.setDescription("Super Strength");
        
        spDao.addPower(power);
        
        Super super1 = new Super();
        super1.setName("Super Man");
        super1.setDescription("God");
        super1.setPower(spDao.getPowerById(power.getPowerId()));
        Super super2 = new Super();
        super2.setName("The Hulk");
        super2.setDescription("Green");
        super2.setPower(spDao.getPowerById(power.getPowerId()));
        
        spDao.addSuper(super1);
        spDao.addSuper(super2);
        
        List<Super> supers = spDao.getAllSupers();
        
        Organization org = new Organization();
        org.setName("Avengers");
        org.setDescription("We avenge things");
        org.setEmail("avengers@secret.com");
        org.setHeadquarters(hq1);
        org.setSupers(supers);
        
        dao.addOrganization(org);
        
        List<Organization> orgList = dao.getAllOrganizations();
        
        assertEquals(orgList, dao.getOrganizationsByHeadquartersId(hq1.getHeadquartersId()));
        
    }

    @Test
    public void testGetAllOrganizations() {
        Headquarters hq1 = new Headquarters();
        hq1.setAddress("111 Tall Lane");
        hq1.setPlanet("Earth");
        Headquarters hq2 = new Headquarters();
        hq2.setAddress("111 xxx aaa");
        hq2.setPlanet("The Moon");
        
        dao.addHeadquarters(hq1);
        dao.addHeadquarters(hq2);
        
        List<Super> supers = spDao.getAllSupers();
        
        Organization org1 = new Organization();
        org1.setName("Avengers");
        org1.setDescription("Vengeance");
        org1.setEmail("avengers@secret.com");
        org1.setHeadquarters(hq1);
        org1.setSupers(supers);
        Organization org2 = new Organization();
        org2.setName("Super Friends");
        org2.setDescription("Friendship");
        org2.setEmail("hey@there.com");
        org2.setHeadquarters(hq2);
        org2.setSupers(supers);
        
        dao.addOrganization(org1);
        dao.addOrganization(org2);
        
        assertEquals(dao.getAllOrganizations().size(), 2);
    }

    @Test
    public void testUpdateOrganization() {
        Headquarters hq = new Headquarters();
        hq.setAddress("133 Super Street");
        hq.setPlanet("Earth");
        
        dao.addHeadquarters(hq);

        List<Super> supers = spDao.getAllSupers();
        
        Organization org = new Organization();
        org.setName("Avengers");
        org.setDescription("We avenge things");
        org.setEmail("avengers@secret.com");
        org.setHeadquarters(hq);
        org.setSupers(supers);
        
        dao.addOrganization(org);
        
        Organization fromDao1 = dao.getOrganizationById(org.getOrganizationId());
        
        org.setName("Super Friends");
        
        dao.updateOrganization(org);
        
        Organization fromDao2 = dao.getOrganizationById(org.getOrganizationId());
        
        assertNotEquals(fromDao1, fromDao2);
    }

    @Test
    public void testDeleteOrganization() {
        Headquarters hq = new Headquarters();
        hq.setAddress("133 Super Street");
        hq.setPlanet("Earth");
        
        dao.addHeadquarters(hq);

        List<Super> supers = spDao.getAllSupers();
        
        Organization org = new Organization();
        org.setName("Avengers");
        org.setDescription("We avenge things");
        org.setEmail("avengers@secret.com");
        org.setHeadquarters(hq);
        org.setSupers(supers);
        
        dao.addOrganization(org);
        
        Organization fromDao = dao.getOrganizationById(org.getOrganizationId());
        
        assertEquals(fromDao, org);
        dao.deleteOrganization(org.getOrganizationId());
        assertNull(dao.getOrganizationById(org.getOrganizationId()));
    }
    
}
