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
public class ChangeData {
    
    private BigDecimal quarters;
    private BigDecimal dimes;
    private BigDecimal nickels;
    private BigDecimal total;
    
    public ChangeData() {
        
    }
    
    public ChangeData(BigDecimal[] input) {
        this.quarters = input[0];
        this.dimes = input[1];
        this.nickels = input[2];
        this.total = input[3];
    }

    public BigDecimal getQuarters() {
        return quarters;
    }

    public void setQuarters(BigDecimal quarters) {
        this.quarters = quarters;
    }

    public BigDecimal getDimes() {
        return dimes;
    }

    public void setDimes(BigDecimal dimes) {
        this.dimes = dimes;
    }

    public BigDecimal getNickels() {
        return nickels;
    }

    public void setNickels(BigDecimal nickels) {
        this.nickels = nickels;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    
    
}
