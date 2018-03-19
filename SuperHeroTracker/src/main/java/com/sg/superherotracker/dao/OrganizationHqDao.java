/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherotracker.dao;

import com.sg.superherotracker.model.Headquarters;
import com.sg.superherotracker.model.Organization;
import java.util.List;

/**
 *
 * @author Alex
 */
public interface OrganizationHqDao {
    
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
