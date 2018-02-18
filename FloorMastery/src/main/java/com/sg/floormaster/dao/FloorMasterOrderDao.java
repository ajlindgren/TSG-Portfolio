/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.dao;

import com.sg.floormaster.dto.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Alex
 */
public interface FloorMasterOrderDao {
    
    Order addOrder(String orderNumber, Order order);
    Order cancelOrder(String orderNumber, Order cancelledOrder);
    Order editOrder(String orderNumber, Order editedOrder);
    List<Order> getAllOrders();
    Order getOrder(String orderNumber);
    void clearMemory();
    void saveOrderFile() throws FloorMasterPersistenceException;
    void loadOrderFile(LocalDate ld) throws FloorMasterPersistenceException;
    void loadAllOrderFiles() throws FloorMasterPersistenceException;
    
}
