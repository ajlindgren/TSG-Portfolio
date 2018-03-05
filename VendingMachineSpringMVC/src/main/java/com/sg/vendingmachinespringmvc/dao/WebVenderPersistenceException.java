/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

/**
 *
 * @author Alex
 */
public class WebVenderPersistenceException extends Exception {
    
    public WebVenderPersistenceException(String message){
        super(message);
    }
    
    public WebVenderPersistenceException(String message, Throwable cause){
        super(message, cause);
    }
    
}
