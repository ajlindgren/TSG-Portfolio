/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.controllers;

import com.sg.vendingmachinespringmvc.dao.ErrorMessage;
import com.sg.vendingmachinespringmvc.dao.WebVenderPersistenceException;
import com.sg.vendingmachinespringmvc.service.WebVenderInsufficientFundsException;
import com.sg.vendingmachinespringmvc.service.WebVenderZeroInventoryException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Alex
 * WebVender
 */

@ControllerAdvice
public class ControllerExceptionHandler {
    
    @ExceptionHandler(WebVenderPersistenceException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public ErrorMessage processWebVenderPersistenceException(WebVenderPersistenceException e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(e.getMessage());
        return errorMessage;
    }
    
    @ExceptionHandler(WebVenderZeroInventoryException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorMessage processWebVenderZeroInventoryException(WebVenderZeroInventoryException e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(e.getMessage());
        return errorMessage;
    }
    
    @ExceptionHandler(WebVenderInsufficientFundsException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorMessage processWebVenderInsufficientFundsException(WebVenderInsufficientFundsException e) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(e.getMessage());
        return errorMessage;
    }
    
}
