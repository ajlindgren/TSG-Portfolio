/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherotracker.dao;

import com.sg.superherotracker.model.Power;
import com.sg.superherotracker.model.Super;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Alex
 */
public class SuperPowerDaoDbImpl implements SuperPowerDao {
    
    //prepared statements against Power
    private static final String SQL_INSERT_POWER = "insert into power (description) VALUE (?)";
    private static final String SQL_SELECT_POWER = "select * from power where powerId = ?";
    private static final String SQL_SELECT_ALL_POWERS = "select * from power";
    private static final String SQL_UPDATE_POWER = "update power set description = ? "
            + "where powerId = ?";
    private static final String SQL_DELETE_POWER = "delete from power where powerId = ?";
    //prepared statements against Super
    private static final String SQL_INSERT_SUPER = "insert into super "
            + "(name, description, powerId) VALUES (?,?,?)";
    private static final String SQL_SELECT_SUPER = "select * from super where superId = ?";
    private static final String SQL_SELECT_ALL_SUPERS = "select * from super";
    private static final String SQL_UPDATE_SUPER = "update super set name = ?, description = ?, "
            + "powerId = ? where superId = ?";
    private static final String SQL_DELETE_SUPER = "delete from super where superId = ?";
    
    private static final String SQL_SELECT_POWER_BY_SUPER_ID = "select power.powerId, power.description "
            + "from power join super on power.powerId = super.powerId where super.superId = ?";
    
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    @Transactional
    public void addPower(Power power) {
        jdbcTemplate.update(SQL_INSERT_POWER,
                power.getDescription());
        int powerId = jdbcTemplate.queryForObject(
                "select LAST_INSERT_ID()", Integer.class);
        
        power.setPowerId(powerId);
    }

    @Override
    public Power getPowerById(int powerId) {
        try {
            return jdbcTemplate.queryForObject(
                    SQL_SELECT_POWER, new PowerMapper(), powerId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Power> getAllPowers() {
        List<Power> powerList = jdbcTemplate.query(
                SQL_SELECT_ALL_POWERS, new PowerMapper());
        return powerList;
    }

    @Override
    public void updatePower(Power power) {
        jdbcTemplate.update(SQL_UPDATE_POWER,
                power.getDescription(),
                power.getPowerId());
    }

    @Override
    public void deletePower(int powerId) {
        jdbcTemplate.update(SQL_DELETE_POWER, powerId);
    }

    @Override
    @Transactional
    public void addSuper(Super xSuper) {
        jdbcTemplate.update(SQL_INSERT_SUPER,
                xSuper.getName(),
                xSuper.getDescription(),
                xSuper.getPower().getPowerId());
        xSuper.setSuperId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        
    }

    @Override
    public Super getSuperById(int superId) {
        try {
            Super xSuper = jdbcTemplate.queryForObject(SQL_SELECT_SUPER, 
                    new SuperMapper(), superId);
            xSuper.setPower(findPowerForSuper(xSuper));
            return xSuper;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Super> getAllSupers() {
        List<Super> superList = jdbcTemplate.query(SQL_SELECT_ALL_SUPERS,
                new SuperMapper());
        return associatePowersWithSupers(superList);
    }

    @Override
    public void updateSuper(Super upSuper) {
        jdbcTemplate.update(SQL_UPDATE_SUPER,
                upSuper.getName(),
                upSuper.getDescription(),
                upSuper.getPower().getPowerId(),
                upSuper.getSuperId());
    }

    @Override
    public void deleteSuper(int superId) {
        jdbcTemplate.update(SQL_DELETE_SUPER, superId);
    }
    
    private Power findPowerForSuper(Super xSuper) {
        return jdbcTemplate.queryForObject(SQL_SELECT_POWER_BY_SUPER_ID, new PowerMapper(), xSuper.getSuperId());
    }
    
    private List<Super> associatePowersWithSupers(List<Super> superList) {
        for (Super currentSuper : superList) {
            currentSuper.setPower(findPowerForSuper(currentSuper));
        }
        return superList;
    }
    
    private static final class PowerMapper implements RowMapper<Power> {
        
        @Override
        public Power mapRow(ResultSet rs, int i) throws SQLException {
            Power power = new Power();
            power.setPowerId(rs.getInt("powerId"));
            power.setDescription(rs.getString("description"));
            return power;
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
