/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.service;

import com.sg.floormaster.dao.FloorMasterAuditDao;
import com.sg.floormaster.dao.FloorMasterMaterialDao;
import com.sg.floormaster.dao.FloorMasterOrderDao;
import com.sg.floormaster.dao.FloorMasterTaxDao;
import com.sg.floormaster.dto.Material;
import com.sg.floormaster.dto.Order;
import com.sg.floormaster.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
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
    public Order addOrder(String orderNumber, Order order) throws Exception {
        return orderDao.addOrder(orderNumber, order);
    }

    @Override
    public Order removeOrder(String orderNumber) throws Exception {
        return orderDao.removeOrder(orderNumber);
    }

    @Override
    public Order editOrder(String orderNumber, Order editedOrder) throws Exception {
        return orderDao.editOrder(orderNumber, editedOrder);
    }

    @Override
    public List<Order> getAllOrders() throws Exception {
        return orderDao.getAllOrders();
    }

    @Override
    public List<Order> getAllOrders(LocalDate ld) throws Exception {
        return orderDao.getAllOrders().stream()
                .filter(o -> o.getOrderDate().equals(ld))
                .collect(Collectors.toList());
    }

    @Override
    public Order getOrder(String orderNumber) throws Exception {
        return orderDao.getOrder(orderNumber);
    }

    @Override
    public void saveOrderFile() throws Exception {
        orderDao.saveOrderFile();
    }

    @Override
    public void loadOrderFile(LocalDate ld) throws Exception {
        orderDao.loadOrderFile(ld);
    }

    //MATERIAL DAO METHODS
    @Override
    public List<Material> getAllMaterials() throws Exception {
        return materialDao.getAllMaterials();
    }

    @Override
    public Material getMaterial(String materialType) throws Exception {
        return materialDao.getMaterial(materialType);
    }

    @Override
    public void loadMaterialFile() throws Exception {
        materialDao.loadMaterialFile();
    }

    //TAX DAO METHODS
    @Override
    public List<Tax> getAllTaxes() throws Exception {
        return taxDao.getAllTaxes();
    }

    @Override
    public Tax getTaxByState(String state) throws Exception {
        return taxDao.getTaxByState(state);
    }

    @Override
    public void loadTaxFile() throws Exception {
        taxDao.loadTaxFile();
    }

    @Override
    public Order calcOrder(Order order, Tax tax, Material material) throws Exception {
        order.setOrderDate(LocalDate.now());
        order.setProductType(material.getMaterialType()); 
        order.setCostMaterialSquareFoot(material.getCostMaterialSquareFoot());
        order.setCostLaborSquareFoot(material.getCostLaborSquareFoot());
        order.setState(tax.getState());
        order.setTaxRate(tax.getRate());

        BigDecimal materialCost = material.getCostMaterialSquareFoot().multiply(order.getArea());
        order.setMaterialCost(materialCost);

        BigDecimal laborCost = material.getCostLaborSquareFoot().multiply(order.getArea());
        order.setLaborCost(laborCost);

        BigDecimal taxTotal = (order.getMaterialCost().add(order.getLaborCost())).multiply(tax.getRate());
        order.setTax(taxTotal);

        BigDecimal total = order.getMaterialCost().add(order.getLaborCost()).add(order.getTax());
        order.setTotal(total);

        return order;
    }

    @Override
    public Order calcOrderNumber(Order completeOrder) throws Exception {
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

}
