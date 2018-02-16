/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.dao;

import com.sg.floormaster.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Alex
 */
public class FloorMasterOrderDaoStubImpl implements FloorMasterOrderDao {

    Order onlyOrder;
    Map<String, Order> orders = new HashMap<>();

    public FloorMasterOrderDaoStubImpl() {
        onlyOrder = new Order();
        onlyOrder.setOrderDate(LocalDate.of(1111, 11, 11));
        onlyOrder.setOrderNumber("1");
        onlyOrder.setArea(BigDecimal.ZERO);
        onlyOrder.setCostMaterialSquareFoot(BigDecimal.ZERO);
        onlyOrder.setCostLaborSquareFoot(BigDecimal.ZERO);
        onlyOrder.setTaxRate(BigDecimal.ZERO);
        onlyOrder.setMaterialCost(BigDecimal.ZERO);
        onlyOrder.setLaborCost(BigDecimal.ZERO);
        onlyOrder.setTax(BigDecimal.ZERO);
        onlyOrder.setTotal(BigDecimal.ZERO);
        
        orders.put(onlyOrder.getOrderNumber(), onlyOrder);
    }

    @Override
    public Order addOrder(String orderNumber, Order order) throws Exception {
        return orders.put(orderNumber, order);
    }

    @Override
    public Order removeOrder(String orderNumber) throws Exception {
        return orders.remove(orderNumber);
    }

    @Override
    public Order editOrder(String orderNumber, Order editedOrder) throws Exception {
        return orders.replace(orderNumber, editedOrder);
    }

    @Override
    public List<Order> getAllOrders() throws Exception {
        List<Order> result = new ArrayList<>(orders.values());
        return result;
    }

    @Override
    public Order getOrder(String orderNumber) throws Exception {
        return orders.get(orderNumber);
    }

    @Override
    public void saveOrderFile() throws Exception {
        //no need to write to file in Stub Implementation
    }

    @Override
    public void loadOrderFile(LocalDate ld) throws Exception {
        //no need to read from file in Stub Implementation
    }

}
