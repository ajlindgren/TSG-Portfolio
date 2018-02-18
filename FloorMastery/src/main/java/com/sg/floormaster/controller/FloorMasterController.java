/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.controller;

import com.sg.floormaster.dao.FloorMasterPersistenceException;
import com.sg.floormaster.dto.Material;
import com.sg.floormaster.dto.Order;
import com.sg.floormaster.dto.Tax;
import com.sg.floormaster.service.FloorMasterServiceLayer;
import com.sg.floormaster.ui.FloorMasterView;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
            this.service.loadAllOrderFiles();
        } catch (FloorMasterPersistenceException ex) {
            view.displayErrorMessage(ex.getMessage());
        }
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing) {
            try {

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
                        cancelOrder();
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
            } catch (FloorMasterPersistenceException e) {
                view.displayErrorMessage(e.getMessage());
            }
        }
        exitMessage();
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void listOrders() throws FloorMasterPersistenceException {
        view.displayDisplayAllBanner();
        LocalDate dateChoice = view.getOrderDateChoice();
//        service.loadOrderFile(dateChoice);
        List<Order> orderList = service.getAllOrdersFilterCancelled(dateChoice);
        if (orderList.size() > 0)
            view.displayOrderList(orderList);
        else
            view.displayNoEntryBanner();
    }

    private void addOrder() {
        view.displayAddOrderBanner();
        
        Tax tax = null;
        Material material = null;
        while (tax == null)
            tax = service.getTaxByState(service.validateTax(view.getStateChoice()));
        while (material == null)
            material = service.getMaterial(service.validateMaterial(view.getMaterialChoice()));
        Order order = new Order(view.getCustomerNameChoice(), view.getAreaChoice());

        Order completedOrder = service.calcOrderNumber(service.calcOrder(order, tax, material));
        if (view.displayOrder(completedOrder).compareToIgnoreCase("Y") == 0) {
            service.addOrder(completedOrder.getOrderNumber(), completedOrder);
        }
    }

    private void editOrder() throws FloorMasterPersistenceException {
        view.displayEditOrderBanner();
        
        String orderNumber = null;
        while (orderNumber == null)
            orderNumber = service.validateOrderNumber(view.getOrderNumberChoice());
        
//        service.loadOrderFile(LocalDate.parse(orderNumber.substring(0, 8), DateTimeFormatter.ofPattern("MMddyyyy")));
        String[] infoArray = view.getEditOrderInfo();

        Order order = service.getOrder(orderNumber);
        Tax tax = new Tax();
        tax.setRate(order.getTaxRate());
        tax.setState(order.getState());
        Material material = new Material();
        material.setMaterialType(order.getProductType());
        material.setCostMaterialSquareFoot(order.getCostMaterialSquareFoot());
        material.setCostLaborSquareFoot(order.getCostLaborSquareFoot());

        if (!infoArray[0].isEmpty()) {
            if (service.validateTax(infoArray[0]) != null)
                tax = service.getTaxByState(infoArray[0]);
            else
                view.displayInvalidEditStateEntry();
        }
        if (!infoArray[1].isEmpty()) {
            if (service.validateMaterial(infoArray[1]) != null)
                material = service.getMaterial(infoArray[1]);
            else
                view.displayInvalidEditMaterialEntry();
        }
        if (!infoArray[2].isEmpty()) {
            order.setCustomerName(infoArray[2]);
        }
        if (!infoArray[3].isEmpty()) {
            if (service.validateArea(infoArray[3]) != null)
                order.setArea(new BigDecimal(infoArray[3]));
            else
                view.displayInvalidEditAreaEntry();
        }

        Order finalOrder = service.calcOrder(order, tax, material);
        Order nullCheck = service.editOrder(orderNumber, finalOrder);
        if (nullCheck == null) {
            view.displayNoEntryBanner();
        } else {
            view.displayEditSuccessBanner();
        }
    }

    private void cancelOrder() throws FloorMasterPersistenceException {
        view.displayCancelOrderBanner();
        String orderNumber = view.getOrderNumberChoice();
        service.loadOrderFile(LocalDate.parse(orderNumber.substring(0, 8), DateTimeFormatter.ofPattern("MMddyyyy")));
        Order nullCheck = service.cancelOrder(orderNumber);
        if (nullCheck == null) {
            view.displayNoEntryBanner();
        } else {
            view.displayCancelSuccessBanner();
        }
    }

    private void saveOrderFiles() throws FloorMasterPersistenceException {
        service.saveOrderFile();
        view.displaySaveSuccessBanner();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
