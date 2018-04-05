/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherotracker.dao;

import com.sg.superherotracker.model.Location;
import com.sg.superherotracker.model.Sighting;
import java.util.List;

/**
 *
 * @author Alex
 */
public interface SightingLocationDao {
    
    public void addLocation(Location loc);
    public Location getLocationById(int locId);
    public List<Location> getAllLocations();
    public void updateLocation(Location loc);
    public void deleteLocation(int locId) throws SuperHeroTrackerDeleteDependencyException;
    
    public void addSighting(Sighting sighting);
    public Sighting getSightingById(int sightingId);
    public List<Sighting> getSightingsBySuperId(int superId);
    public List<Sighting> getSightingsByLocationId(int locId);
    public List<Sighting> getAllSightings();
    public List<Sighting> get10RecentSightings();
    public void updateSighting(Sighting sighting);
    public void deleteSighting(int sightingId);
}
