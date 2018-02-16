/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.ui;

import com.sg.floormaster.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author Alex
 */
public class FloorMasterView {
    
    UserIO io;

    public FloorMasterView(UserIO io) {
        this.io = io;
    }
    
    public int printMenuAndGetSelection() {
        io.print("<<Flooring Program>>");
        io.print("1. Display Orders (by Date)");
        io.print("2. Add an Order to the system");
        io.print("3. Edit an Order in the system");
        io.print("4. Remove an Order from the system");
        io.print("5. Save Current Changes to File");
        io.print("6. Quit Program");

        return io.readInt("Please select from the above choices.", 1, 6);
    }
    
    public void displayOrderList(List<Order> orderList) {
        for (Order currentOrder : orderList) {
            io.print(currentOrder.getOrderNumber() + " - Name: "
                    + currentOrder.getCustomerName() + ", State: "
                    + currentOrder.getState() + ", Area: "
                    + currentOrder.getArea() + ", Flooring Type: "
                    + currentOrder.getProductType() + ". Cost: "
                    + "$" + currentOrder.getCostMaterialSquareFoot() + " per Sq. Ft. (Materials) - "
                    + "$" + currentOrder.getCostLaborSquareFoot() + " per Sq. Ft. (Labor) - "
                    + "Tax Rate: " + currentOrder.getTaxRate() + ". Calculated costs: "
                    + "$" + currentOrder.getMaterialCost() + " (Materials), "
                    + "$" + currentOrder.getLaborCost() + " (Labor), "
                    + "$" + currentOrder.getTax() + " (Tax). Order Total: "
                    + "$" + currentOrder.getTotal());
        }
    }
    
    public void displayDisplayAllBanner() {
        io.print("~~~ Display All Orders ~~~");
    }
    
    public LocalDate getOrderDateChoice() {
        return io.readDate("Please enter the Date. (MMddyyyy)");
    }
    
    public void displayAddOrderBanner() {
        io.print("~~~ Add an Order ~~~");
    }
    
    public String[] getNewOrderInfo() {
        String state = io.readString("Please enter the State from which the order is being placed.");
        String material = io.readString("Please enter the type of flooring to be installed.");
        String customerName = io.readString("Please enter the Customer's Last Name");
        String area = io.readString("Please enter the area (in Sq. Ft.) of the desired flooring work.");

        String[] currentOrder = {state, material, customerName, area};
        return currentOrder;
    }
    
    public String displayOrder(Order currentOrder) {
        if (currentOrder != null) {
            io.print(currentOrder.getOrderNumber() + " - Name: "
                    + currentOrder.getCustomerName() + ", State: "
                    + currentOrder.getState() + ", Area: "
                    + currentOrder.getArea() + ", Flooring Type: "
                    + currentOrder.getProductType() + ". Cost: "
                    + "$" + currentOrder.getCostMaterialSquareFoot() + " per Sq. Ft. (Materials) - "
                    + "$" + currentOrder.getCostLaborSquareFoot() + " per Sq. Ft. (Labor) - "
                    + "Tax Rate: " + currentOrder.getTaxRate() + ". Calculated costs: "
                    + "$" + currentOrder.getMaterialCost() + " (Materials), "
                    + "$" + currentOrder.getLaborCost() + " (Labor), "
                    + "$" + currentOrder.getTax() + " (Tax). Order Total: "
                    + "$" + currentOrder.getTotal());
        } else {
            io.print("No such Order on File.");
        } 
        return io.readString("Enter 'y' to confirm this order. Enter anything else to discard it.");
    }
    
    public void displayEditOrderBanner() {
        io.print("~~~ Edit an Order Entry ~~~");
    }
    
    public String getOrderNumberChoice() {
        return io.readString("Please enter the Order Number.");
    }
    
    public Order getEditOrderInfo(Order currentOrder) {
        String customerName = io.readString("Please enter the Name on the Order");
        BigDecimal area = io.readBig("Please enter the desired Area to be floored.");
        String productType = io.readString("Please enter the material to be used.");
        String state = io.readString("Please enter the State in which the work will be done.");

        currentOrder.setCustomerName(customerName);
        currentOrder.setArea(area);
        currentOrder.setProductType(productType);
        currentOrder.setState(state);
        return currentOrder;
    }
    
    public void displayRemoveOrderBanner() {
        io.print("~~~ Remove an Order ~~");
    }
    
    public void displayRemoveSuccessBanner() {
        io.readString("Order successfully removed. Please hit enter to continue.");
    }
    
    public void displayNoEntryBanner() {
        io.readString("No such Order on file. Please hit enter to continue.");
    }
    
    public void displayEditSuccessBanner() {
        io.readString("Order Entry successfully edited. Please hit enter to continue.");
    }
    
    public void displaySaveSuccessBanner() {
        io.readString("All changes made this session have been saved to file. Please hit enter to continue.");
    }
    
    public void displayExitBanner() {
        io.print("Closing Floor Master Program. Thank You.");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("-~= ERROR =~-");
        io.print(errorMsg);
    }
}
