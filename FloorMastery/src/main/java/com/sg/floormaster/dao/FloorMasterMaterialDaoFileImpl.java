/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.dao;

import com.sg.floormaster.dto.Material;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Alex
 */
public class FloorMasterMaterialDaoFileImpl implements FloorMasterMaterialDao {
    
    public static final String PERSISTENCE_FILE = "DataFiles/materialData.txt";
    public static final String DELIMITER = ",";
    
    private Map<String, Material> materials = new HashMap<>();
    
    @Override
    public List<Material> getAllMaterials() throws Exception {
        return new ArrayList<>(materials.values());
    }

    @Override
    public Material getMaterial(String materialType) throws Exception {
        return materials.get(materialType);
    }
    
    @Override
    public void loadMaterialFile() throws Exception {
        loadMaterials();
    }
    
    private void loadMaterials() throws Exception {
        Scanner scanner;

        try {
            //create scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(PERSISTENCE_FILE)));
        } catch (FileNotFoundException e) {
            throw new Exception("-_- Could not load material data into memory.", e);
        }
        
        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            
            Material currentMaterial = new Material();
            currentMaterial.setMaterialType(currentTokens[0]);
            currentMaterial.setCostMaterialSquareFoot(new BigDecimal(currentTokens[1]));
            currentMaterial.setCostLaborSquareFoot(new BigDecimal(currentTokens[2]));

            materials.put(currentMaterial.getMaterialType(), currentMaterial);
        }
        // close scanner
        scanner.close();
    }
    
    private void writeMaterials() throws Exception {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(PERSISTENCE_FILE));
        } catch (IOException e) {
            throw new Exception("Could not save material data.", e);
        }
        
        List<Material> materialList = new ArrayList<>(materials.values());
        for (Material currentMaterial : materialList) {
            out.println(currentMaterial.getMaterialType() + DELIMITER
                    + currentMaterial.getCostMaterialSquareFoot() + DELIMITER
                    + currentMaterial.getCostLaborSquareFoot() + DELIMITER);
            out.flush();
        }
        out.close();
    }
    
}
