/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.dao;

import com.sg.floormaster.dto.Material;
import java.util.List;

/**
 *
 * @author Alex
 */
public interface FloorMasterMaterialDao {
    
    List<Material> getAllMaterials();
    Material getMaterial(String materialType);
    void loadMaterialFile() throws FloorMasterPersistenceException;
    
}
