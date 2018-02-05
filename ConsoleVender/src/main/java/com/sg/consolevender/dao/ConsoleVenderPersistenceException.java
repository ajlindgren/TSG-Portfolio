/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.consolevender.dao;

/**
 *
 * @author Alex
 */
public class ConsoleVenderPersistenceException extends Exception {
    
    public ConsoleVenderPersistenceException(String message){
        super(message);
    }
    
    public ConsoleVenderPersistenceException(String message, Throwable cause){
        super(message, cause);
    }
    
}
