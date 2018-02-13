/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.dao;

import com.sg.floormaster.dto.Tax;
import java.util.List;

/**
 *
 * @author Alex
 */
public interface FloorMasterTaxDao {
    
    List<Tax> getAllTaxes() throws Exception;
    Tax getTaxByState(String state) throws Exception;
    void saveToFile() throws Exception;
    void loadFromFile() throws Exception;
    
}
