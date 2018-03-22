/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherotracker.service;

import com.sg.superherotracker.dao.OrganizationHqDao;
import com.sg.superherotracker.dao.SightingLocationDao;
import com.sg.superherotracker.dao.SuperPowerDao;
import com.sg.superherotracker.model.Headquarters;
import com.sg.superherotracker.model.Location;
import com.sg.superherotracker.model.Organization;
import com.sg.superherotracker.model.Power;
import com.sg.superherotracker.model.Sighting;
import com.sg.superherotracker.model.Super;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alex
 */
@Repository
public class SuperHeroTrackerServiceLayerImpl implements SuperHeroTrackerServiceLayer {
    
    SuperPowerDao spDao;
    SightingLocationDao slDao;
    OrganizationHqDao ohDao;
    
    @Inject
    public SuperHeroTrackerServiceLayerImpl(SuperPowerDao spDao, SightingLocationDao slDao, OrganizationHqDao ohDao) {
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-persistence.xml");
//        
//        spDao = ctx.getBean("superPowerDao", SuperPowerDao.class);
//        slDao = ctx.getBean("sightingLocationDao", SightingLocationDao.class);
//        ohDao = ctx.getBean("organizationHqDao", OrganizationHqDao.class);
        
        this.spDao = spDao;
        this.slDao = slDao;
        this.ohDao = ohDao;
    }

    @Override
    public void addPower(Power power) {
        spDao.addPower(power);
        
    }

    @Override
    public Power getPowerById(int powerId) {
        return spDao.getPowerById(powerId);
    }

    @Override
    public List<Power> getAllPowers() {
        return spDao.getAllPowers();
    }

    @Override
    public void updatePower(Power power) {
        spDao.updatePower(power);
    }

    @Override
    public void deletePower(int powerId) {
        spDao.deletePower(powerId);
    }

    @Override
    public void addSuper(Super addSuper) {
        spDao.addSuper(addSuper);
    }

    @Override
    public Super getSuperById(int superId) {
        return spDao.getSuperById(superId);
    }

    @Override
    public List<Super> getAllSupers() {
        return spDao.getAllSupers();
    }

    @Override
    public void updateSuper(Super upSuper) {
        spDao.updateSuper(upSuper);
    }

    @Override
    public void deleteSuper(int superId) {
        spDao.deleteSuper(superId);
    }

    @Override
    public void addLocation(Location loc) {
        slDao.addLocation(loc);
    }

    @Override
    public Location getLocationById(int locId) {
        return slDao.getLocationById(locId);
    }

    @Override
    public List<Location> getAllLocations() {
        return slDao.getAllLocations();
    }

    @Override
    public void updateLocation(Location loc) {
        slDao.updateLocation(loc);
    }

    @Override
    public void deleteLocation(int locId) {
        slDao.deleteLocation(locId);
    }

    @Override
    public void addSighting(Sighting sighting) {
        slDao.addSighting(sighting);
    }

    @Override
    public Sighting getSightingById(int sightingId) {
        return slDao.getSightingById(sightingId);
    }

    @Override
    public List<Sighting> getSightingsBySuperId(int superId) {
        return slDao.getSightingsBySuperId(superId);
    }

    @Override
    public List<Sighting> getSightingsByLocationId(int locId) {
        return slDao.getSightingsByLocationId(locId);
    }

    @Override
    public List<Sighting> getAllSightings() {
        return slDao.getAllSightings();
    }

    @Override
    public void updateSighting(Sighting sighting) {
        slDao.updateSighting(sighting);
    }

    @Override
    public void deleteSighting(int sightingId) {
        slDao.deleteSighting(sightingId);
    }

    @Override
    public void addHeadquarters(Headquarters hq) {
        ohDao.addHeadquarters(hq);
    }

    @Override
    public Headquarters getHeadquartersById(int hqId) {
        return ohDao.getHeadquartersById(hqId);
    }

    @Override
    public List<Headquarters> getAllHeadquarters() {
        return ohDao.getAllHeadquarters();
    }

    @Override
    public void updateHeadquarters(Headquarters hq) {
        ohDao.updateHeadquarters(hq);
    }

    @Override
    public void deleteHeadquarters(int hqId) {
        ohDao.deleteHeadquarters(hqId);
    }

    @Override
    public void addOrganization(Organization org) {
        ohDao.addOrganization(org);
    }

    @Override
    public Organization getOrganizationById(int orgId) {
        return ohDao.getOrganizationById(orgId);
    }

    @Override
    public List<Organization> getOrganizationsBySuperId(int superId) {
        return ohDao.getOrganizationsBySuperId(superId);
    }

    @Override
    public List<Organization> getOrganizationsByHeadquartersId(int hqId) {
        return ohDao.getOrganizationsByHeadquartersId(hqId);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return ohDao.getAllOrganizations();
    }

    @Override
    public void updateOrganization(Organization org) {
        ohDao.updateOrganization(org);
    }

    @Override
    public void deleteOrganization(int orgId) {
        ohDao.deleteOrganization(orgId);
    }
}
