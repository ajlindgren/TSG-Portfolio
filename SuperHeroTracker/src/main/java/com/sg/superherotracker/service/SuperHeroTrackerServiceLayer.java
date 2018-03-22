/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherotracker.service;

import com.sg.superherotracker.model.Headquarters;
import com.sg.superherotracker.model.Location;
import com.sg.superherotracker.model.Organization;
import com.sg.superherotracker.model.Power;
import com.sg.superherotracker.model.Sighting;
import com.sg.superherotracker.model.Super;
import java.util.List;

/**
 *
 * @author Alex
 */
public interface SuperHeroTrackerServiceLayer {
    
    //SuperPowerDao methods
    public void addPower(Power power);
    public Power getPowerById(int powerId);
    public List<Power> getAllPowers();
    public void updatePower(Power power);
    public void deletePower(int powerId);
    
    public void addSuper(Super addSuper);
    public Super getSuperById(int superId);
    public List<Super> getAllSupers();
    public void updateSuper(Super upSuper);
    public void deleteSuper(int superId);
    
    //SightingLocationDao methods
    public void addLocation(Location loc);
    public Location getLocationById(int locId);
    public List<Location> getAllLocations();
    public void updateLocation(Location loc);
    public void deleteLocation(int locId);
    
    public void addSighting(Sighting sighting);
    public Sighting getSightingById(int sightingId);
    public List<Sighting> getSightingsBySuperId(int superId);
    public List<Sighting> getSightingsByLocationId(int locId);
    public List<Sighting> getAllSightings();
    public void updateSighting(Sighting sighting);
    public void deleteSighting(int sightingId);
    
    //OrganizationHeadquarters methods
    public void addHeadquarters(Headquarters hq);
    public Headquarters getHeadquartersById(int hqId);
    public List<Headquarters> getAllHeadquarters();
    public void updateHeadquarters(Headquarters hq);
    public void deleteHeadquarters(int hqId);
    
    public void addOrganization(Organization org);
    public Organization getOrganizationById(int orgId);
    public List<Organization> getOrganizationsBySuperId(int superId);
    public List<Organization> getOrganizationsByHeadquartersId(int hqId);
    public List<Organization> getAllOrganizations();
    public void updateOrganization(Organization org);
    public void deleteOrganization(int orgId);
    
}
