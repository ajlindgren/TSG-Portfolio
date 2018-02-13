/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.dao;

import com.sg.floormaster.dto.Tax;
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
public class FloorMasterTaxDaoFileImpl implements FloorMasterTaxDao {
    
    public static final String PERSISTENCE_FILE = "DataFiles/taxData.txt";
    public static final String DELIMITER = ",";
    
    private Map<String, Tax> taxes = new HashMap<>();
    
    @Override
    public List<Tax> getAllTaxes() throws Exception {
        return new ArrayList<>(taxes.values());
    }

    @Override
    public Tax getTaxByState(String state) throws Exception {
        return taxes.get(state);
    }
    
    @Override
    public void saveToFile() throws Exception {
        writeTaxes();
    }
    
    @Override
    public void loadFromFile() throws Exception {
        loadTaxes();
    }
    
    private void loadTaxes() throws Exception {
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
            
            Tax currentTax = new Tax();
            currentTax.setState(currentTokens[0]);
            currentTax.setRate(new BigDecimal(currentTokens[1]));

            taxes.put(currentTax.getState(), currentTax);
        }
        // close scanner
        scanner.close();
    }
    
    private void writeTaxes() throws Exception {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(PERSISTENCE_FILE));
        } catch (IOException e) {
            throw new Exception("Could not save material data.", e);
        }
        
        List<Tax> taxList = new ArrayList<>(taxes.values());
        for (Tax currentTax : taxList) {
            out.println(currentTax.getState() + DELIMITER
                    + currentTax.getRate() + DELIMITER);
            out.flush();
        }
        out.close();
    }
    
}
