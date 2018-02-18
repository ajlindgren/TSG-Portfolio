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
public class FloorMasterPersistenceException extends Exception {
    
    public FloorMasterPersistenceException(String message){
        super(message);
    }
    
    public FloorMasterPersistenceException(String message, Throwable cause){
        super(message, cause);
    }
    
}
