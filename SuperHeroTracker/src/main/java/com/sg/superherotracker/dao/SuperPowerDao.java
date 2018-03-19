/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherotracker.dao;

import com.sg.superherotracker.model.Power;
import com.sg.superherotracker.model.Super;
import java.util.List;

/**
 *
 * @author Alex
 */
public interface SuperPowerDao {
    
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
    
}
