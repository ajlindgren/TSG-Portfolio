/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.service;

import com.sg.floormaster.dao.FloorMasterMaterialDao;
import com.sg.floormaster.dao.FloorMasterOrderDao;
import com.sg.floormaster.dao.FloorMasterPersistenceException;
import com.sg.floormaster.dao.FloorMasterTaxDao;
import com.sg.floormaster.dto.Material;
import com.sg.floormaster.dto.Order;
import com.sg.floormaster.dto.Tax;
import java.math.BigDecimal;
import static java.math.RoundingMode.HALF_UP;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Alex
 */
public class FloorMasterServiceLayerImpl implements FloorMasterServiceLayer {

    FloorMasterOrderDao orderDao;
    FloorMasterMaterialDao materialDao;
    FloorMasterTaxDao taxDao;

    public FloorMasterServiceLayerImpl(FloorMasterOrderDao orderDao, FloorMasterMaterialDao materialDao, FloorMasterTaxDao taxDao) {
        this.orderDao = orderDao;
        this.materialDao = materialDao;
        this.taxDao = taxDao;
    }

    //ORDER DAO METHODS
    @Override
    public Order addOrder(String orderNumber, Order order) {
        return orderDao.addOrder(orderNumber, order);
    }

    @Override
    public Order cancelOrder(String orderNumber) {
        Order result = orderDao.getOrder(orderNumber);
        String currentName = result.getCustomerName();
        result.setCustomerName("(Cancelled)" + currentName);
        return orderDao.cancelOrder(orderNumber, result);
    }

    @Override
    public Order editOrder(String orderNumber, Order editedOrder) {
        return orderDao.editOrder(orderNumber, editedOrder);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }

    @Override
    public List<Order> getAllOrders(LocalDate ld) {
        return orderDao.getAllOrders().stream()
                .filter(o -> o.getOrderDate().equals(ld))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Order> getAllOrdersFilterCancelled(LocalDate ld) {
        String cancelledFlag = "(Cancelled)";
        
        return orderDao.getAllOrders().stream()
                .filter(o -> o.getOrderDate().equals(ld))
                .filter(o -> o.getCustomerName().indexOf(cancelledFlag) == -1)
                .collect(Collectors.toList());
    }

    @Override
    public Order getOrder(String orderNumber) {
        return orderDao.getOrder(orderNumber);
    }

    @Override
    public void saveOrderFile() throws FloorMasterPersistenceException {
        orderDao.saveOrderFile();
    }

    @Override
    public void loadOrderFile(LocalDate ld) throws FloorMasterPersistenceException {
        List<Order> loadCheck = orderDao.getAllOrders().stream()
                                    .filter(o -> o.getOrderDate().equals(ld))
                                    .collect(Collectors.toList());
        if(loadCheck.isEmpty())
            orderDao.loadOrderFile(ld);
    }
    
    @Override
    public void loadAllOrderFiles() throws FloorMasterPersistenceException {
        orderDao.loadAllOrderFiles();
    }

    //MATERIAL DAO METHODS
    @Override
    public List<Material> getAllMaterials() {
        return materialDao.getAllMaterials();
    }

    @Override
    public Material getMaterial(String materialType) {
        return materialDao.getMaterial(materialType);
    }

    @Override
    public void loadMaterialFile() throws FloorMasterPersistenceException {
        materialDao.loadMaterialFile();
    }

    //TAX DAO METHODS
    @Override
    public List<Tax> getAllTaxes() {
        return taxDao.getAllTaxes();
    }

    @Override
    public Tax getTaxByState(String state) {
        return taxDao.getTaxByState(state);
    }

    @Override
    public void loadTaxFile() throws FloorMasterPersistenceException {
        taxDao.loadTaxFile();
    }

    @Override
    public Order calcOrder(Order order, Tax tax, Material material) {
        order.setOrderDate(LocalDate.now());
        order.setProductType(material.getMaterialType()); 
        order.setCostMaterialSquareFoot(material.getCostMaterialSquareFoot().setScale(2, HALF_UP));
        order.setCostLaborSquareFoot(material.getCostLaborSquareFoot().setScale(2, HALF_UP));
        order.setState(tax.getState());
        order.setTaxRate(tax.getRate().setScale(2, HALF_UP));

        BigDecimal materialCost = material.getCostMaterialSquareFoot().multiply(order.getArea());
        order.setMaterialCost(materialCost.setScale(2, HALF_UP));

        BigDecimal laborCost = material.getCostLaborSquareFoot().multiply(order.getArea());
        order.setLaborCost(laborCost.setScale(2, HALF_UP));
        
        BigDecimal total = (order.getMaterialCost().add(order.getLaborCost())).multiply(tax.getCalcRate());
        order.setTotal(total.setScale(2, HALF_UP));

        BigDecimal taxTotal = (total.subtract(materialCost.add(laborCost)));
        order.setTax(taxTotal.setScale(2, HALF_UP));

        return order;
    }

    @Override
    public Order calcOrderNumber(Order completeOrder) {
        List<Order> reference = getAllOrders(completeOrder.getOrderDate());
        int lastOrderNumber = 0;

        for (Order currentOrder : reference) {
            int relevantLength = currentOrder.getOrderDate().format(DateTimeFormatter.ofPattern("MMddyyyy")).length();
            if (currentOrder.getOrderNumber().length() > 1) {
                if (Integer.parseInt(currentOrder.getOrderNumber().substring(relevantLength)) >= lastOrderNumber) {
                    lastOrderNumber = Integer.parseInt(currentOrder.getOrderNumber().substring(relevantLength));
                }
            }
        }

        completeOrder.setOrderNumber(completeOrder.getOrderDate().format(DateTimeFormatter.ofPattern("MMddyyyy")) + (lastOrderNumber+1));

        return completeOrder;
    }

    @Override
    public String validateTax(String input) {
        if (taxDao.getTaxByState(input) != null)
            return input;
        else
            return null;
    }

    @Override
    public String validateMaterial(String input) {
        if (materialDao.getMaterial(input) != null)
            return input;
        else
            return null;
    }

    @Override
    public String validateArea(String input) {
        try {
            BigDecimal validation = new BigDecimal(input);
        } catch (NumberFormatException e) {
            return null;
        }
        return input;
    }
    
    @Override
    public String validateOrderNumber(String input) {
        if (orderDao.getOrder(input) != null)
            return input;
        else
            return null;
    }
    

}
