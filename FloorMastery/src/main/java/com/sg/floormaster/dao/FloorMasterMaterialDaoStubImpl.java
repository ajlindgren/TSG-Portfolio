/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.dao;

import com.sg.floormaster.dto.Material;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Alex
 */
public class FloorMasterMaterialDaoStubImpl implements FloorMasterMaterialDao {
    
    Material onlyMaterial;
    Map<String, Material> materials = new HashMap<>();
    
    public FloorMasterMaterialDaoStubImpl() {
        onlyMaterial = new Material();
        onlyMaterial.setMaterialType("Wood");
        onlyMaterial.setCostMaterialSquareFoot(BigDecimal.ONE);
        onlyMaterial.setCostLaborSquareFoot(BigDecimal.ONE);
        
        materials.put(onlyMaterial.getMaterialType(), onlyMaterial);
    }

    @Override
    public List<Material> getAllMaterials() throws Exception {
        List<Material> result = new ArrayList<>(materials.values());
        return result;
    }

    @Override
    public Material getMaterial(String materialType) throws Exception {
        return materials.get(materialType);
    }

    @Override
    public void loadMaterialFile() throws Exception {
        //no need to load from file in Stub Implementation
    }
    
}
