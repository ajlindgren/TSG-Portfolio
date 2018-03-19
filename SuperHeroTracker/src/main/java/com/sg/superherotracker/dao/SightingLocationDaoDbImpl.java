/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherotracker.dao;

import com.sg.superherotracker.model.Location;
import com.sg.superherotracker.model.Sighting;
import com.sg.superherotracker.model.Super;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Alex
 */
public class SightingLocationDaoDbImpl implements SightingLocationDao {
    
    //prepared statements against Location
    private static final String SQL_INSERT_LOCATION = "insert into location (name, description, address, latitude, longitude) "
            + "VALUES (?,?,?,?,?)";
    private static final String SQL_SELECT_LOCATION = "select * from location where locationId = ?";
    private static final String SQL_SELECT_ALL_LOCATION = "select * from location";
    private static final String SQL_UPDATE_LOCATION = "update location set name = ?, description = ?, address = ?, "
            + "latitude = ?, longitude = ? where locationId = ?";
    private static final String SQL_DELETE_LOCATION = "delete from location where locationId = ?";
    //prepared statements against Sighting
    private static final String SQL_INSERT_SIGHTING = "insert into sighting "
            + "(dateTime, locationId) VALUES (?,?)";
    private static final String SQL_SELECT_SIGHTING = "select * from sighting where sightingId = ?";
    private static final String SQL_SELECT_ALL_SIGHTINGS = "select * from sighting";
    private static final String SQL_UPDATE_SIGHTING = "update sighting set dateTime = ?, locationId = ? "
            + "where sightingId = ?";
    private static final String SQL_DELETE_SIGHTING = "delete from sighting where sightingId = ?";
    //prepared statements for reference methods
    private static final String SQL_SELECT_SIGHTINGS_BY_SUPER_ID = "select si.dateTime, "
            + "si.locationId from sighting si join superSighting ss on si.sightingId = ss.sightingId "
            + "where ss.superId = ?";
    private static final String SQL_SELECT_SIGHTINGS_BY_LOCATION_ID = "select * from sighting "
            + "where locationId = ?";
    private static final String SQL_SELECT_LOCATION_BY_SIGHTING_ID = "select loc.locationId, loc.name, loc.description, "
            + "loc.address, loc.latitude, loc.longitude from location loc join sighting on loc.locationId = sighting.locationId "
            + "where sighting.sightingId = ?";
    private static final String SQL_SELECT_SUPERS_BY_SIGHTING_ID = "select s.superId, "
            + "s.name, s.description, s.powerId from super s join superSighting ss "
            + "on s.superId = ss.superId where ss.sightingId = ?";
    //update bridge table
    private static final String SQL_DELETE_SUPER_SIGHTING = "delete from superSighting where sightingId = ?";
    private static final String SQL_INSERT_SUPER_SIGHTING = "insert into superSighting "
            + "(superId, sightingId) values (?,?)";
    
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    @Transactional
    public void addLocation(Location loc) {
        jdbcTemplate.update(SQL_INSERT_LOCATION,
                loc.getName(),
                loc.getDescription(),
                loc.getAddress(),
                loc.getLatitude(),
                loc.getLongitude());
        int locId = jdbcTemplate.queryForObject(
                "select LAST_INSERT_ID()", Integer.class);
        
        loc.setLocationId(locId);
    }

    @Override
    public Location getLocationById(int locId) {
        try {
            return jdbcTemplate.queryForObject(
                    SQL_SELECT_LOCATION, new LocationMapper(), locId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations() {
        List<Location> locList = jdbcTemplate.query(
                SQL_SELECT_ALL_LOCATION, new LocationMapper());
        return locList;
    }

    @Override
    public void updateLocation(Location loc) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION,
                loc.getName(),
                loc.getDescription(),
                loc.getAddress(),
                loc.getLatitude(),
                loc.getLongitude(),
                loc.getLocationId());
    }

    @Override
    public void deleteLocation(int locId) {
        jdbcTemplate.update(SQL_DELETE_LOCATION, locId);
    }

    @Override
    @Transactional
    public void addSighting(Sighting sighting) {
        Timestamp timestamp = Timestamp.valueOf(sighting.getDateTime());
        jdbcTemplate.update(SQL_INSERT_SIGHTING,
                timestamp,
                sighting.getLocation().getLocationId());
        int superId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        
        sighting.setSightingId(superId);
    }

    @Override
    public Sighting getSightingById(int sightingId) {
        try {
            Sighting sighting = jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING, 
                    new SightingMapper(), sightingId);
            sighting.setLocation(findLocationForSighting(sighting));
            sighting.setSupers(findSupersForSighting(sighting));
            return sighting;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    
    @Override
    public List<Sighting> getSightingsBySuperId(int superId) {
        List<Sighting> sightingList = jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_SUPER_ID, new SightingMapper(), superId);
        return associateLocationAndSupersWithSightings(sightingList);
    }
    
    @Override
    public List<Sighting> getSightingsByLocationId(int locId) {
        List<Sighting> sightingList = jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_LOCATION_ID, new SightingMapper(), locId);
        return associateLocationAndSupersWithSightings(sightingList);
    }

    @Override
    public List<Sighting> getAllSightings() {
        List<Sighting> sightingList = jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS,
                new SightingMapper());
        return associateLocationAndSupersWithSightings(sightingList);
    }

    @Override
    @Transactional
    public void updateSighting(Sighting sighting) {
        Timestamp timestamp = Timestamp.valueOf(sighting.getDateTime());
        jdbcTemplate.update(SQL_UPDATE_SIGHTING,
                timestamp,
                sighting.getLocation().getLocationId(),
                sighting.getSightingId());
        jdbcTemplate.update(SQL_DELETE_SUPER_SIGHTING, sighting.getSightingId());
        insertSuperSighting(sighting);
    }

    @Override
    public void deleteSighting(int sightingId) {
        jdbcTemplate.update(SQL_DELETE_SIGHTING, sightingId);
    }
    
    private void insertSuperSighting(Sighting sighting) {
        final int sightingId = sighting.getSightingId();
        final List<Super> supers = sighting.getSupers();
        
        for (Super currentSuper : supers) {
            jdbcTemplate.update(SQL_INSERT_SUPER_SIGHTING, sightingId, currentSuper.getSuperId());
        }
    }
    
    private List<Super> findSupersForSighting(Sighting sighting) {
        return jdbcTemplate.query(SQL_SELECT_SUPERS_BY_SIGHTING_ID, new SuperMapper(), sighting.getSightingId());
    }
    
    private Location findLocationForSighting(Sighting sighting) {
        return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_SIGHTING_ID, new LocationMapper(), sighting.getSightingId());
    }
    
    private List<Sighting> associateLocationAndSupersWithSightings(List<Sighting> sightingList) {
        for (Sighting currentSighting : sightingList) {
            currentSighting.setLocation(findLocationForSighting(currentSighting));
            currentSighting.setSupers(findSupersForSighting(currentSighting));
        }
        return sightingList;
    }
    
    private static final class SightingMapper implements RowMapper<Sighting> {
        
        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setSightingId(rs.getInt("sightingId"));
            sighting.setDateTime(rs.getTimestamp("dateTime").toLocalDateTime());
            return sighting;
        }
    }
    
    private static final class LocationMapper implements RowMapper<Location> {
        
        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location loc = new Location();
            loc.setLocationId(rs.getInt("locationId"));
            loc.setName(rs.getString("name"));
            loc.setDescription(rs.getString("description"));
            loc.setAddress(rs.getString("address"));
            loc.setLatitude(rs.getBigDecimal("latitude"));
            loc.setLongitude(rs.getBigDecimal("longitude"));
            return loc;
        }
    }
    
    private static final class SuperMapper implements RowMapper<Super> {
        
        @Override
        public Super mapRow(ResultSet rs, int i) throws SQLException {
            Super newSuper = new Super();
            newSuper.setSuperId(rs.getInt("superId"));
            newSuper.setName(rs.getString("name"));
            newSuper.setDescription(rs.getString("description"));
            return newSuper;
        }
    }
    
}