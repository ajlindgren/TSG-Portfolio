/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.dao;

import com.sg.floormaster.dto.Order;
import java.io.BufferedReader;
import java.io.File;
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

    private Map<String, Order> orders = new HashMap<>();

    @Override
    public Order addOrder(String orderNumber, Order order) {
        Order newOrder = orders.put(orderNumber, order);
        return newOrder;
    }

    @Override
    public Order cancelOrder(String orderNumber, Order cancelledOrder) {
        Order nullCheck = orders.replace(orderNumber, cancelledOrder);
        return nullCheck;
    }

    @Override
    public Order editOrder(String orderNumber, Order editedOrder) {
        Order nullCheck = orders.replace(orderNumber, editedOrder);
        return nullCheck;
    }

    @Override
    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }

    @Override
    public Order getOrder(String orderNumber) {
        return orders.get(orderNumber);
    }

    @Override
    public void saveOrderFile() throws FloorMasterPersistenceException {

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
    public void loadOrderFile(LocalDate ld) throws FloorMasterPersistenceException {
        loadOrders(ld.format(DateTimeFormatter.ofPattern("MMddyyyy")));
    }

    @Override
    public void clearMemory() {
        orders.clear();
    }

    @Override
    public void loadAllOrderFiles() throws FloorMasterPersistenceException {
        loadAllOrders();
    }

    private void loadAllOrders() throws FloorMasterPersistenceException {
        Scanner scanner;

//        try (Stream<Path> paths = Files.walk(Paths.get("/home/you/Desktop"))) {
//    paths
//        .filter(Files::isRegularFile)
//        .forEach(System.out::println); }
        File folder = new File("DataFiles");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            String fileDate = file.getPath().substring(file.getPath().length()-12, file.getPath().length()-4);
            if (file.getPath().contains("Orders_")) {
                try {
                    //create scanner for reading the file
                    scanner = new Scanner(new BufferedReader(new FileReader(file.getPath())));
                } catch (FileNotFoundException e) {
                    throw new FloorMasterPersistenceException("-_- Could not load order data into memory.", e);
                }

                String currentLine;
                String[] currentTokens;

                while (scanner.hasNextLine()) {
                    currentLine = scanner.nextLine();
                    currentTokens = currentLine.split(DELIMITER);

                    Order currentOrder = new Order();
                    currentOrder.setOrderNumber(currentTokens[0]);
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
                    currentOrder.setOrderDate(LocalDate.parse(fileDate, DateTimeFormatter.ofPattern("MMddyyyy")));

                    orders.put(currentOrder.getOrderNumber(), currentOrder);
                }
            }
        }

    }

    private void loadOrders(String readDate) throws FloorMasterPersistenceException {
        Scanner scanner;

        try {
            //create scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader("DataFiles/Orders_" + readDate + ".txt")));
        } catch (FileNotFoundException e) {
            throw new FloorMasterPersistenceException("-_- Could not load order data into memory.", e);
        }

        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            Order currentOrder = new Order();
            currentOrder.setOrderNumber(currentTokens[0]);
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
            currentOrder.setOrderDate(LocalDate.parse(readDate, DateTimeFormatter.ofPattern("MMddyyyy")));

            orders.put(currentOrder.getOrderNumber(), currentOrder);
        }
        // close scanner
        scanner.close();
    }

    private void writeOrders(String writeFile, List<Order> orderList) throws FloorMasterPersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter("DataFiles/Orders_" + writeFile + ".txt"));
        } catch (IOException e) {
            throw new FloorMasterPersistenceException("Could not save order data.", e);
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
                    + currentOrder.getTotal());
            out.flush();
        }
        out.close();
    }

}
