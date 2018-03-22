/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherotracker.dao;

import com.sg.superherotracker.model.Location;
import com.sg.superherotracker.model.Power;
import com.sg.superherotracker.model.Sighting;
import com.sg.superherotracker.model.Super;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
public class SightingLocationDaoTest {
    
    SightingLocationDao dao;
    SuperPowerDao spDao;
    
    public SightingLocationDaoTest() {
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
        
        dao = ctx.getBean("sightingLocationDao", SightingLocationDao.class);
        spDao = ctx.getBean("superPowerDao", SuperPowerDao.class);
        
        
        //delete all supers
        List<Super> supers = spDao.getAllSupers();
        for(Super currentSuper : supers) {
            spDao.deleteSuper(currentSuper.getSuperId());
        }
        //delete all powers
        List<Power> powers = spDao.getAllPowers();
        for(Power currentPower : powers) {
            spDao.deletePower(currentPower.getPowerId());
        }
        //delete all sightings
        List<Sighting> sightings = dao.getAllSightings();
        for(Sighting currentSighting : sightings) {
            dao.deleteSighting(currentSighting.getSightingId());
        }
        //delete all locations
        List<Location> locations = dao.getAllLocations();
        for(Location currentLoc : locations) {
            dao.deleteLocation(currentLoc.getLocationId());
        }
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAddGetLocation () {
        Location loc = new Location();
        loc.setName("Central Park");
        loc.setDescription("Fight");
        loc.setAddress("123 central");
        loc.setLatitude(new BigDecimal("180.000000"));
        loc.setLongitude(new BigDecimal("180.000000"));
        
        dao.addLocation(loc);
        
        Location fromDao = dao.getLocationById(loc.getLocationId());
        assertEquals(fromDao, loc);
        
    }

    @Test
    public void testGetAllLocations() {
        Location loc1 = new Location();
        loc1.setName("Name");
        loc1.setDescription("Description");
        loc1.setAddress("123 Super Street");
        loc1.setLatitude(new BigDecimal("180.000000"));
        loc1.setLongitude(new BigDecimal("180.000000"));
        Location loc2 = new Location();
        loc2.setName("Name");
        loc2.setDescription("Description");
        loc2.setAddress("124 Super Ave");
        loc2.setLatitude(new BigDecimal("180.000000"));
        loc2.setLongitude(new BigDecimal("180.000000"));
        
        dao.addLocation(loc1);
        dao.addLocation(loc2);
        
        assertEquals(dao.getAllLocations().size(), 2);
    }

    @Test
    public void testUpdateLocation() {
        Location loc = new Location();
        loc.setName("Central Park");
        loc.setDescription("Fight");
        loc.setAddress("123 central");
        loc.setLatitude(new BigDecimal("180.000000"));
        loc.setLongitude(new BigDecimal("180.000000"));
        
        dao.addLocation(loc);
        Location fromDao1 = dao.getLocationById(loc.getLocationId());
        
        loc.setName("Trump Tower");
        dao.updateLocation(loc);
        
        Location fromDao2 = dao.getLocationById(loc.getLocationId());
        assertNotEquals(fromDao1, fromDao2);
    }

    @Test
    public void testDeleteLocation() {
        Location loc = new Location();
        loc.setName("Central Park");
        loc.setDescription("Fight");
        loc.setAddress("123 central");
        loc.setLatitude(new BigDecimal("180.000000"));
        loc.setLongitude(new BigDecimal("180.000000"));
        
        dao.addLocation(loc);
        
        Location fromDao = dao.getLocationById(loc.getLocationId());
        assertEquals(fromDao, loc);
        dao.deleteLocation(loc.getLocationId());
        assertNull(dao.getLocationById(loc.getLocationId()));
    }

    @Test
    public void testAddGetSighting() {
        Location loc = new Location();
        loc.setName("Central Park");
        loc.setDescription("Fight");
        loc.setAddress("123 central");
        loc.setLatitude(new BigDecimal("180.000000"));
        loc.setLongitude(new BigDecimal("180.000000"));
        
        dao.addLocation(loc);
        
        List<Super> supers = spDao.getAllSupers();
        
        Sighting sighting = new Sighting();
        sighting.setDateTime(LocalDateTime.of(2012, 12, 12, 12, 12));
        sighting.setLocation(loc);
        sighting.setSupers(supers);
        
        dao.addSighting(sighting);
        
        Sighting fromDao = dao.getSightingById(sighting.getSightingId());
        assertEquals(fromDao, sighting);
    }

    @Test
    public void testGetSightingsBySuperId() {
        Location loc = new Location();
        loc.setName("Central Park");
        loc.setDescription("Fight");
        loc.setAddress("123 central");
        loc.setLatitude(new BigDecimal("180.000000"));
        loc.setLongitude(new BigDecimal("180.000000"));
        
        dao.addLocation(loc);
        
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
        
        Sighting sighting = new Sighting();
        sighting.setDateTime(LocalDateTime.of(2012, 12, 12, 12, 12));
        sighting.setLocation(loc);
        sighting.setSupers(supers);
        
        dao.addSighting(sighting);
        
        assertEquals(dao.getSightingsBySuperId(super1.getSuperId()),
                dao.getSightingsBySuperId(super2.getSuperId()));
    }

    @Test
    public void testGetSightingsByLocationId() {
        Location loc = new Location();
        loc.setName("Central Park");
        loc.setDescription("Fight");
        loc.setAddress("123 central");
        loc.setLatitude(new BigDecimal("180.000000"));
        loc.setLongitude(new BigDecimal("180.000000"));
        
        dao.addLocation(loc);
        
        Power power = new Power();
        power.setDescription("Super Strength");
        
        spDao.addPower(power);
        
        Super super1 = new Super();
        super1.setName("Super Man");
        super1.setDescription("God");
        super1.setPower(power);
        Super super2 = new Super();
        super2.setName("The Hulk");
        super2.setDescription("Green");
        super2.setPower(power);
        
        spDao.addSuper(super1);
        spDao.addSuper(super2);
        
        List<Super> supers = spDao.getAllSupers();
        
        Sighting sighting = new Sighting();
        sighting.setDateTime(LocalDateTime.of(2012, 12, 12, 12, 12));
        sighting.setLocation(dao.getLocationById(loc.getLocationId()));
        sighting.setSupers(supers);
        
        dao.addSighting(sighting);
        
        List<Sighting> sightingList = dao.getAllSightings();
        
        assertEquals(sightingList, dao.getSightingsByLocationId(loc.getLocationId()));
        
        //create sightings at 2 different locations, then get sighting by location ID to get the specific one you're looking for, not selecting the whole table.
    }

    @Test
    public void testGetAllSightings() {
        Location loc1 = new Location();
        loc1.setName("Central Park");
        loc1.setDescription("Fight");
        loc1.setAddress("123 central");
        loc1.setLatitude(new BigDecimal("180.000000"));
        loc1.setLongitude(new BigDecimal("180.000000"));
        Location loc2 = new Location();
        loc2.setName("The Sky");
        loc2.setDescription("Up high");
        loc2.setAddress("WAY up high");
        loc2.setLatitude(new BigDecimal("180.000000"));
        loc2.setLongitude(new BigDecimal("180.000000"));
        
        dao.addLocation(loc1);
        dao.addLocation(loc2);
        
        List<Super> supers = spDao.getAllSupers();
        
        Sighting sighting1 = new Sighting();
        sighting1.setDateTime(LocalDateTime.of(2012, 12, 12, 12, 12));
        sighting1.setLocation(loc1);
        sighting1.setSupers(supers);
        Sighting sighting2 = new Sighting();
        sighting2.setDateTime(LocalDateTime.of(2012, 12, 12, 12, 12));
        sighting2.setLocation(loc2);
        sighting2.setSupers(supers);
        
        dao.addSighting(sighting1);
        dao.addSighting(sighting2);
        
        assertEquals(dao.getAllSightings().size(), 2);
    }

    @Test
    public void testUpdateSighting() {
        Location loc1 = new Location();
        loc1.setName("Central Park");
        loc1.setDescription("Fight");
        loc1.setAddress("123 central");
        loc1.setLatitude(new BigDecimal("180.000000"));
        loc1.setLongitude(new BigDecimal("180.000000"));
        Location loc2 = new Location();
        loc2.setName("The Sky");
        loc2.setDescription("Up high");
        loc2.setAddress("WAY up high");
        loc2.setLatitude(new BigDecimal("180.000000"));
        loc2.setLongitude(new BigDecimal("180.000000"));
        
        dao.addLocation(loc1);
        dao.addLocation(loc2);
        
        List<Super> supers = spDao.getAllSupers();
        
        Sighting sighting = new Sighting();
        sighting.setDateTime(LocalDateTime.of(2012, 12, 12, 12, 12));
        sighting.setLocation(loc1);
        sighting.setSupers(supers);
        
        dao.addSighting(sighting);
        
        Sighting fromDao1 = dao.getSightingById(sighting.getSightingId());
        
        sighting.setLocation(loc2);
        
        dao.updateSighting(sighting);
        
        Sighting fromDao2 = dao.getSightingById(sighting.getSightingId());
        
        assertNotEquals(fromDao1, fromDao2);
    }

    @Test
    public void testDeleteSighting() {
        Location loc = new Location();
        loc.setName("Central Park");
        loc.setDescription("Fight");
        loc.setAddress("123 central");
        loc.setLatitude(new BigDecimal("180.000000"));
        loc.setLongitude(new BigDecimal("180.000000"));
        
        dao.addLocation(loc);
        
        List<Super> supers = spDao.getAllSupers();
        
        Sighting sighting = new Sighting();
        sighting.setDateTime(LocalDateTime.of(2012, 12, 12, 12, 12));
        sighting.setLocation(loc);
        sighting.setSupers(supers);
        
        dao.addSighting(sighting);
        
        Sighting fromDao = dao.getSightingById(sighting.getSightingId());
        
        assertEquals(fromDao, sighting);
        dao.deleteSighting(sighting.getSightingId());
        assertNull(dao.getSightingById(sighting.getSightingId()));
    }
}
