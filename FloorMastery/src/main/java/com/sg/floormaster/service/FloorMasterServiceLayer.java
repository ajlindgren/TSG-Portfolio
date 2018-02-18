/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.service;

import com.sg.floormaster.dao.FloorMasterPersistenceException;
import com.sg.floormaster.dto.Material;
import com.sg.floormaster.dto.Order;
import com.sg.floormaster.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Alex
 */
public interface FloorMasterServiceLayer {
    
    //OrderDao methods
    Order addOrder(String orderNumber, Order order);
    Order cancelOrder(String orderNumber);
    Order editOrder(String orderNumber, Order editedOrder);
    List<Order> getAllOrders();
    List<Order> getAllOrders(LocalDate ld);
    List<Order> getAllOrdersFilterCancelled(LocalDate ld);
    Order getOrder(String orderNumber) throws FloorMasterPersistenceException;
    void saveOrderFile() throws FloorMasterPersistenceException;
    void loadOrderFile(LocalDate ld) throws FloorMasterPersistenceException;
    void loadAllOrderFiles() throws FloorMasterPersistenceException;
    
    //MaterialDao methods
    List<Material> getAllMaterials();
    Material getMaterial(String materialType);
    void loadMaterialFile() throws FloorMasterPersistenceException;
    
    //TaxDao methods
    List<Tax> getAllTaxes();
    Tax getTaxByState(String state);
    void loadTaxFile() throws FloorMasterPersistenceException;
    
    //ServiceLayer methods
    Order calcOrder(Order order, Tax tax, Material material);
    Order calcOrderNumber(Order order);
    String validateTax(String input);
    String validateMaterial(String input);
    String validateArea(String input);
    String validateOrderNumber(String input);
     
}
