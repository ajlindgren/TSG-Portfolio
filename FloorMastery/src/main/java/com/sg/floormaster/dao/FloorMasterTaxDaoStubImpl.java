/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.dao;

import com.sg.floormaster.dto.Tax;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Alex
 */
public class FloorMasterTaxDaoStubImpl implements FloorMasterTaxDao {
    
    Tax onlyTax;
    Map<String, Tax> taxes = new HashMap<>();
    
    public FloorMasterTaxDaoStubImpl() {
        onlyTax = new Tax();
        onlyTax.setState("OH");
        onlyTax.setRate(BigDecimal.ONE);
        
        taxes.put(onlyTax.getState(), onlyTax);
    }
    
    @Override
    public List<Tax> getAllTaxes() {
        List<Tax> result = new ArrayList<>(taxes.values());
        return result;
    }

    @Override
    public Tax getTaxByState(String state) {
        return taxes.get(state);
    }

    @Override
    public void loadTaxFile() {
        //no need to load from file in Stub Implementation
    }
    
}
