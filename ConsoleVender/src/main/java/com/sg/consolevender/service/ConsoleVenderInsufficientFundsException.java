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
public class ConsoleVenderInsufficientFundsException extends Exception{
    
    public ConsoleVenderInsufficientFundsException(String message) {
        super(message);
    }
    
    public ConsoleVenderInsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }
}
