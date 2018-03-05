/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.model;

import java.math.BigDecimal;

/**
 *
 * @author Alex
 */
public enum Coin {
    QUARTER(new BigDecimal("0.25")), 
    DIME(new BigDecimal("0.10")), 
    NICKEL(new BigDecimal("0.05"));
    
    private BigDecimal value;
    
    Coin(BigDecimal value) {
        this.value = value;
    }
    
    public BigDecimal getValue() {
        return value;
    }
}
