/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherotracker.dao;

import com.sg.superherotracker.model.Headquarters;
import com.sg.superherotracker.model.Organization;
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
public class OrganizationHqDaoDbImpl implements OrganizationHqDao {
    
    //prepared statements against Headquarters
    private static final String SQL_INSERT_HQ = "insert into headquarters (address, planet) VALUES (?,?)";
    private static final String SQL_SELECT_HQ = "select * from headquarters where headquartersId = ?";
    private static final String SQL_SELECT_ALL_HQ = "select * from headquarters";
    private static final String SQL_UPDATE_HQ = "update headquarters set address = ?, planet = ? "
            + "where headquartersId = ?";
    private static final String SQL_DELETE_HQ = "delete from headquarters where headquartersId = ?";
    //prepared statements against Organization
    private static final String SQL_INSERT_ORGANIZATION = "insert into organization "
            + "(name, description, email, headquartersId) VALUES (?,?,?,?)";
    private static final String SQL_SELECT_ORGANIZATION = "select * from organization where organizationId = ?";
    private static final String SQL_SELECT_ALL_ORGANIZATIONS = "select * from organization";
    private static final String SQL_UPDATE_ORGANIZATION = "update organization set name = ?, description = ?, "
            + "email = ?, headquartersId = ? where organizationId = ?";
    private static final String SQL_DELETE_ORGANIZATION = "delete from organization where organizationId = ?";
    //prepared statements for reference methods
    private static final String SQL_SELECT_ORGANIZATIONS_BY_SUPER_ID = "select org.name, "
            + "org.description, org.email, org.headquartersId from organization org "
            + "join superOrganization so on org.organizationId = so.organizationId "
            + "where so.superId = ?";
    private static final String SQL_SELECT_ORGANIZATIONS_BY_HQ_ID = "select * from organization "
            + "where headquartersId = ?";
    private static final String SQL_SELECT_HQ_BY_ORGANIZATION_ID = "select hq.headquartersId, hq.address, hq.planet "
            + "from headquarters hq join organization on hq.headquartersId = organization.headquartersId "
            + "where organization.organizationId = ?";
    private static final String SQL_SELECT_SUPERS_BY_ORGANIZATION_ID = "select s.superId, "
            + "s.name, s.description, s.powerId from super s join superOrganization so "
            + "on s.superId = so.superId where so.organizationId = ?";
    //update bridge table
    private static final String SQL_DELETE_SUPER_ORGANIZATION = "delete from superOrganization where organizationId = ?";
    private static final String SQL_INSERT_SUPER_ORGANIZATION = "insert into superOrganization "
            + "(superId, organizationId) values (?,?)";
    
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    @Transactional
    public void addHeadquarters(Headquarters hq) {
        jdbcTemplate.update(SQL_INSERT_HQ,
                hq.getAddress(),
                hq.getPlanet());
        int hqId = jdbcTemplate.queryForObject(
                "select LAST_INSERT_ID()", Integer.class);
        
        hq.setHeadquartersId(hqId);
    }

    @Override
    public Headquarters getHeadquartersById(int hqId) {
        try {
            return jdbcTemplate.queryForObject(
                    SQL_SELECT_HQ, new HqMapper(), hqId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Headquarters> getAllHeadquarters() {
        List<Headquarters> hqList = jdbcTemplate.query(
                SQL_SELECT_ALL_HQ, new HqMapper());
        return hqList;
    }

    @Override
    public void updateHeadquarters(Headquarters hq) {
        jdbcTemplate.update(SQL_UPDATE_HQ,
                hq.getAddress(),
                hq.getPlanet(), 
                hq.getHeadquartersId());
    }

    @Override
    public void deleteHeadquarters(int hqId) {
        jdbcTemplate.update(SQL_DELETE_HQ, hqId);
    }

    @Override
    @Transactional
    public void addOrganization(Organization org) {
        jdbcTemplate.update(SQL_INSERT_ORGANIZATION,
                org.getName(),
                org.getDescription(),
                org.getEmail(),
                org.getHeadquarters().getHeadquartersId());
        int orgId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        
        org.setOrganizationId(orgId);
    }

    @Override
    public Organization getOrganizationById(int orgId) {
        try {
            Organization org = jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION, 
                    new OrganizationMapper(), orgId);
            org.setHeadquarters(findHeadquartersForOrganization(org));
            org.setSupers(findSupersForOrganization(org));
            return org;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    
    @Override
    public List<Organization> getOrganizationsBySuperId(int superId) {
        List<Organization> orgList = jdbcTemplate.query(SQL_SELECT_ORGANIZATIONS_BY_SUPER_ID, new OrganizationMapper(), superId);
        return associateHeadquartersAndSupersWithOrganizations(orgList);
    }
    
    @Override
    public List<Organization> getOrganizationsByHeadquartersId(int hqId) {
        List<Organization> orgList = jdbcTemplate.query(SQL_SELECT_ORGANIZATIONS_BY_HQ_ID, new OrganizationMapper(), hqId);
        return associateHeadquartersAndSupersWithOrganizations(orgList);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        List<Organization> orgList = jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATIONS,
                new OrganizationMapper());
        return associateHeadquartersAndSupersWithOrganizations(orgList);
    }

    @Override
    @Transactional
    public void updateOrganization(Organization org) {
        jdbcTemplate.update(SQL_UPDATE_ORGANIZATION,
                org.getName(),
                org.getDescription(),
                org.getEmail(),
                org.getHeadquarters().getHeadquartersId(),
                org.getOrganizationId());
        jdbcTemplate.update(SQL_DELETE_SUPER_ORGANIZATION, org.getOrganizationId());
        insertSuperOrganization(org);
    }

    @Override
    public void deleteOrganization(int orgId) {
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION, orgId);
    }
    
    private void insertSuperOrganization(Organization org) {
        final int orgId = org.getOrganizationId();
        final List<Super> supers = org.getSupers();
        
        for (Super currentSuper : supers) {
            jdbcTemplate.update(SQL_INSERT_SUPER_ORGANIZATION, orgId, currentSuper.getSuperId());
        }
    }
    
    private List<Super> findSupersForOrganization(Organization org) {
        return jdbcTemplate.query(SQL_SELECT_SUPERS_BY_ORGANIZATION_ID, new SuperMapper(), org.getOrganizationId());
    }
    
    private Headquarters findHeadquartersForOrganization(Organization org) {
        return jdbcTemplate.queryForObject(SQL_SELECT_HQ_BY_ORGANIZATION_ID, new HqMapper(), org.getOrganizationId());
    }
    
    private List<Organization> associateHeadquartersAndSupersWithOrganizations(List<Organization> orgList) {
        for (Organization currentOrg : orgList) {
            currentOrg.setHeadquarters(findHeadquartersForOrganization(currentOrg));
            currentOrg.setSupers(findSupersForOrganization(currentOrg));
        }
        return orgList;
    }
    
    private static final class OrganizationMapper implements RowMapper<Organization> {
        
        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization org = new Organization();
            org.setOrganizationId(rs.getInt("organizationId"));
            org.setName(rs.getString("name"));
            org.setDescription(rs.getString("description"));
            org.setEmail(rs.getString("email"));
            return org;
        }
    }
    
    private static final class HqMapper implements RowMapper<Headquarters> {
        
        @Override
        public Headquarters mapRow(ResultSet rs, int i) throws SQLException {
            Headquarters hq = new Headquarters();
            hq.setHeadquartersId(rs.getInt("headquartersId"));
            hq.setAddress(rs.getString("address"));
            hq.setPlanet(rs.getString("planet"));
            return hq;
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