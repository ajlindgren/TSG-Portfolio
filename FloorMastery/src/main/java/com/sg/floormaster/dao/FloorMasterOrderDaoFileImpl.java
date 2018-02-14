/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.dao;

import com.sg.floormaster.dto.Order;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Alex
 */
public class FloorMasterOrderDaoFileImpl implements FloorMasterOrderDao {

    //public static String WRITE_FILE;
    public static String READ_FILE;
    public static final String DELIMITER = ",";

    private Map<Integer, Order> orders = new HashMap<>();

    @Override
    public Order addOrder(Integer orderNumber, Order order) throws Exception {
        Order newOrder = orders.put(orderNumber, order);
        return newOrder;
    }

    @Override
    public Order removeOrder(Integer orderNumber) throws Exception {
        Order nullCheck = orders.remove(orderNumber);
        return nullCheck;
    }

    @Override
    public Order editOrder(Integer orderNumber, Order editedOrder) throws Exception {
        Order nullCheck = orders.replace(orderNumber, editedOrder);
        return nullCheck;
    }

    @Override
    public List<Order> getAllOrders() throws Exception {
        return new ArrayList<>(orders.values());
    }

    @Override
    public Order getOrder(Integer orderNumber) throws Exception {
        return orders.get(orderNumber);
    }

    @Override
    public void saveOrderFile() throws Exception {

        orders.values().stream()
                .collect(Collectors.groupingBy(Order::getOrderDate))
                .forEach((lds, ol) -> {
                    try {
                        writeOrders(lds.format(DateTimeFormatter.ofPattern("MMddyyyy")), ol);
                    } catch (Exception ex) {
                        Logger.getLogger(FloorMasterOrderDaoFileImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
    }

    @Override
    public void loadOrderFile(LocalDate ld) throws Exception {
        String readFile = "DataFiles/Orders_" + ld.format(DateTimeFormatter.ofPattern("MMddyyyy")) + ".txt";
        loadOrders(readFile);
    }

    private void loadOrders(String readFile) throws Exception {
        Scanner scanner;

        try {
            //create scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(readFile)));
        } catch (FileNotFoundException e) {
            throw new Exception("-_- Could not load order data into memory.", e);
        }

        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            Order currentOrder = new Order();
            currentOrder.setOrderNumber(Integer.parseInt(currentTokens[0]));
            currentOrder.setCustomerName(currentTokens[1]);
            currentOrder.setArea(new BigDecimal(currentTokens[2]));
            currentOrder.setProductType(currentTokens[3]);
            currentOrder.setCostMaterialSquareFoot(new BigDecimal(currentTokens[4]));
            currentOrder.setCostLaborSquareFoot(new BigDecimal(currentTokens[5]));
            currentOrder.setState(currentTokens[6]);
            currentOrder.setTaxRate(new BigDecimal(currentTokens[7]));
            currentOrder.setMaterialCost(new BigDecimal(currentTokens[8]));
            currentOrder.setLaborCost(new BigDecimal(currentTokens[9]));
            currentOrder.setTax(new BigDecimal(currentTokens[10]));
            currentOrder.setTotal(new BigDecimal(currentTokens[11]));

            orders.put(currentOrder.getOrderNumber(), currentOrder);
        }
        // close scanner
        scanner.close();
    }

    private void writeOrders(String writeFile, List<Order> orderList) throws Exception {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter("DataFiles/Orders_" + writeFile + ".txt"));
        } catch (IOException e) {
            throw new Exception("Could not save order data.", e);
        }

        for (Order currentOrder : orderList) {
            out.println(currentOrder.getOrderNumber() + DELIMITER
                    + currentOrder.getCustomerName() + DELIMITER
                    + currentOrder.getArea() + DELIMITER
                    + currentOrder.getProductType() + DELIMITER
                    + currentOrder.getCostMaterialSquareFoot() + DELIMITER
                    + currentOrder.getCostLaborSquareFoot() + DELIMITER
                    + currentOrder.getState() + DELIMITER
                    + currentOrder.getTaxRate() + DELIMITER
                    + currentOrder.getMaterialCost() + DELIMITER
                    + currentOrder.getLaborCost() + DELIMITER
                    + currentOrder.getTax() + DELIMITER
                    + currentOrder.getTotal() + DELIMITER);
            out.flush();
        }
        out.close();
    }

}
