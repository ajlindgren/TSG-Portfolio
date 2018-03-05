/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

/**
 *
 * @author Alex
 */
public class WebVenderZeroInventoryException extends Exception {
    
    public WebVenderZeroInventoryException(String message) {
        super(message);
    }
    
    public WebVenderZeroInventoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
