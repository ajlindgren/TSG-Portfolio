/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author Alex
 */
public class FloorMasterTrainingDaoFileImpl implements FloorMasterTrainingDao {
    
    public static String READ_FILE = "DataFiles/trainingConfig.txt";
    
    private boolean trainingConfig = false;

    @Override
    public boolean readTrainingConfig() throws FloorMasterPersistenceException {
        loadTrainingConfig();
        return trainingConfig;
    }
    
    private void loadTrainingConfig() throws FloorMasterPersistenceException {
        Scanner scanner;

        try {
            //create scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(READ_FILE)));
        } catch (FileNotFoundException e) {
            throw new FloorMasterPersistenceException("-_- Could not load config data into memory.", e);
        }

        String currentLine;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();

            if (currentLine.compareToIgnoreCase("True") == 0)
                trainingConfig = true;
        }
        // close scanner
        scanner.close();
    }
    
}
