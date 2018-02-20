/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.dao;

/**
 *
 * @author Alex
 */
public class FloorMasterTrainingDaoStubImpl implements FloorMasterTrainingDao {
    
    boolean configCheck;
    
    public FloorMasterTrainingDaoStubImpl() {
        configCheck = false;
    }

    @Override
    public boolean readTrainingConfig() throws FloorMasterPersistenceException {
        return configCheck;
    }
    
}
