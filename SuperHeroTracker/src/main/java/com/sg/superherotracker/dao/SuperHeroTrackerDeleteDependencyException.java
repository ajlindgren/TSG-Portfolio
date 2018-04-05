/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherotracker.dao;

/**
 *
 * @author Alex
 */
public class SuperHeroTrackerDeleteDependencyException extends Exception {
    
    public SuperHeroTrackerDeleteDependencyException(String message){
        super(message);
    }
    
    public SuperHeroTrackerDeleteDependencyException(String message, Throwable cause){
        super(message, cause);
    }
    
}
