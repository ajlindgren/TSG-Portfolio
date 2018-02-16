/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.controller;

import com.sg.floormaster.dto.Material;
import com.sg.floormaster.dto.Order;
import com.sg.floormaster.dto.Tax;
import com.sg.floormaster.service.FloorMasterServiceLayer;
import com.sg.floormaster.ui.FloorMasterView;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        
        try {
        this.service.loadMaterialFile();
        this.service.loadTaxFile();
        } catch (Exception ex) { 
            Logger.getLogger(FloorMasterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void run() {     
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {
                
                menuSelection = getMenuSelection();
                
                switch (menuSelection) {
                    case 1:
                        listOrders();
                        break;
                    case 2:
                        addOrder();
                        break;
                    case 3:
                        editOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        saveOrderFiles();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        } catch (Exception e) {
            view.displayErrorMessage(e.getMessage());
        }        
    }
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private void listOrders() throws Exception {
        view.displayDisplayAllBanner();
        LocalDate dateChoice = view.getOrderDateChoice();
        if (service.getAllOrders(dateChoice).isEmpty())
            service.loadOrderFile(dateChoice);
        List<Order> orderList = service.getAllOrders(dateChoice);
        view.displayOrderList(orderList);
    }
    
    private void addOrder() throws Exception {
//        service.loadMaterialFile();
//        service.loadTaxFile();
        
        view.displayAddOrderBanner();
        String[] infoArray = view.getNewOrderInfo();
        
        Order order = new Order(infoArray[2], new BigDecimal(infoArray[3]));
        Tax tax = service.getTaxByState(infoArray[0]);
        Material material = service.getMaterial(infoArray[1]);
        Order completedOrder = service.calcOrderNumber(service.calcOrder(order, tax, material));
        if (view.displayOrder(completedOrder).compareToIgnoreCase("Y") == 0)
            service.addOrder(completedOrder.getOrderNumber(), completedOrder);   
    }
    
    private void editOrder() throws Exception {
//        service.loadMaterialFile();
//        service.loadTaxFile();
        view.displayEditOrderBanner();
        
        String orderNumber = view.getOrderNumberChoice();
        Order editedOrder = view.getEditOrderInfo(service.getOrder(orderNumber));
        service.loadOrderFile(editedOrder.getOrderDate());
        
        Tax tax = service.getTaxByState(editedOrder.getState());
        Material material = service.getMaterial(editedOrder.getProductType());
        
        Order finalOrder = service.calcOrder(editedOrder, tax, material);
        Order nullCheck = service.editOrder(editedOrder.getOrderNumber(), finalOrder);
        if (nullCheck == null)
            view.displayNoEntryBanner();
        else
            view.displayEditSuccessBanner();
    }
    
    private void removeOrder() throws Exception {
        view.displayRemoveOrderBanner();
        String orderNumber = view.getOrderNumberChoice();
        Order nullCheck = service.removeOrder(orderNumber);
        if (nullCheck == null)
            view.displayNoEntryBanner();
        else
            view.displayRemoveSuccessBanner();
    }
    
    private void saveOrderFiles() throws Exception {
        service.saveOrderFile();
        view.displaySaveSuccessBanner();
    }
    
    private void unknownCommand() throws Exception {
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage() throws Exception {
        view.displayExitBanner();
    }
    
}
