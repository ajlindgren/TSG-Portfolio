/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.service;

import com.sg.floormaster.dto.Material;
import com.sg.floormaster.dto.Order;
import com.sg.floormaster.dto.Tax;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Alex
 */
public interface FloorMasterServiceLayer {
    
    //OrderDao methods
    Order addOrder(Integer orderNumber, Order order) throws Exception;
    Order removeOrder(Integer orderNumber) throws Exception;
    Order editOrder(Integer orderNumber, Order editedOrder) throws Exception;
    List<Order> getAllOrders(LocalDate ld) throws Exception;
    Order getOrder(Integer orderNumber) throws Exception;
    void saveOrderFile() throws Exception;
    void loadOrderFile(LocalDate ld) throws Exception;
    
    //MaterialDao methods
    List<Material> getAllMaterials() throws Exception;
    Material getMaterial(String materialType) throws Exception;
    void loadMaterialFile() throws Exception;
    
    //TaxDao methods
    List<Tax> getAllTaxes() throws Exception;
    Tax getTaxByState(String state) throws Exception;
    void loadTaxFile() throws Exception;
    
    //ServiceLayer methods
    //Order calcOrder(Order order, Tax tax, Material material) throws Exception;
     
}
