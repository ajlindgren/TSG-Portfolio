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
public class WebVenderInsufficientFundsException extends Exception{
    
    public WebVenderInsufficientFundsException(String message) {
        super(message);
    }
    
    public WebVenderInsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }
}
