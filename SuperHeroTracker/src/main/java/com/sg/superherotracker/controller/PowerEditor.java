/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherotracker.controller;

import com.sg.superherotracker.dao.SuperPowerDao;
import com.sg.superherotracker.model.Power;
import java.beans.PropertyEditorSupport;
import javax.inject.Inject;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alex
 */
@Component
public class PowerEditor extends PropertyEditorSupport {
    
    @Inject
    SuperPowerDao spDao;
    
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Power pow = spDao.getPowerById(Integer.parseInt(text));
        
        setValue(pow);
    }
}
