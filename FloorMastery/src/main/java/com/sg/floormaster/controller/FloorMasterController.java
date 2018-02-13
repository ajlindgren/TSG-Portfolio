/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.controller;

import com.sg.floormaster.dao.FloorMasterOrderDao;
import com.sg.floormaster.service.FloorMasterServiceLayer;
import com.sg.floormaster.ui.FloorMasterView;

/**
 *
 * @author Alex
 */
public class FloorMasterController {
    
    private FloorMasterView view;
    private FloorMasterServiceLayer service;

    public FloorMasterController(FloorMasterServiceLayer service, FloorMasterView view) {
        this.service = service;
        this.view = view;
    }
    
    public void run(){
    
    }
    
}
