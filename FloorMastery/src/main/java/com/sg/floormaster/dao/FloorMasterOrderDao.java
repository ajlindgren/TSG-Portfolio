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
    
    Order addOrder(Integer orderNumber, Order order) throws Exception;
    Order removeOrder(Integer orderNumber) throws Exception;
    Order editOrder(Integer orderNumber, Order editedOrder) throws Exception;
    List<Order> getAllOrders() throws Exception;
    Order getOrder(Integer orderNumber) throws Exception;
    void saveOrderFile() throws Exception;
    void loadOrderFile(LocalDate ld) throws Exception;
    
}
