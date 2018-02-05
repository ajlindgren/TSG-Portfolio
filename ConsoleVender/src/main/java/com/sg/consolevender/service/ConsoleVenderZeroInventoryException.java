/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.consolevender.service;

/**
 *
 * @author Alex
 */
public class ConsoleVenderZeroInventoryException extends Exception {
    
    public ConsoleVenderZeroInventoryException(String message) {
        super(message);
    }
    
    public ConsoleVenderZeroInventoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
