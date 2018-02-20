/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.ui;

import com.sg.floormaster.dto.Material;
import com.sg.floormaster.dto.Order;
import com.sg.floormaster.dto.Tax;
import java.math.BigDecimal;
import static java.math.RoundingMode.HALF_UP;
import java.time.LocalDate;
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
    
    public void printEntryBanner() {
        io.print("");
        io.print("<<Flooring Program>>");
    }

    public int printMenuAndGetSelection() {
        io.print("");
        io.print("1. Display Orders (by Date)");
        io.print("2. Add an Order to the system");
        io.print("3. Edit an Order in the system");
        io.print("4. Cancel an Order in the system");
        io.print("5. Save Current Changes to File");
        io.print("6. Quit Program");

        return io.readInt("Please select from the above choices.", 1, 6);
    }

    public void displayOrderList(List<Order> orderList) {
        io.print("");
        for (Order currentOrder : orderList) {
            io.print(currentOrder.getOrderNumber() + " - Name: "
                    + currentOrder.getCustomerName() + ", State: "
                    + currentOrder.getState() + ", Area: "
                    + currentOrder.getArea().setScale(2, HALF_UP) + ", Flooring Type: "
                    + currentOrder.getProductType() + ". Cost: "
                    + "$" + currentOrder.getCostMaterialSquareFoot() + " per Sq. Ft. (Materials) - "
                    + "$" + currentOrder.getCostLaborSquareFoot() + " per Sq. Ft. (Labor) - "
                    + "Tax Rate: " + currentOrder.getTaxRate() + "%. Calculated costs: "
                    + "$" + currentOrder.getMaterialCost() + " (Materials), "
                    + "$" + currentOrder.getLaborCost() + " (Labor), "
                    + "$" + currentOrder.getTax() + " (Tax). Order Total: "
                    + "$" + currentOrder.getTotal());
        }
    }

    public void displayDisplayAllBanner() {
        io.print("");
        io.print("~~~ Display All Orders ~~~");
    }

    public LocalDate getOrderDateChoice() {
        return io.readDate("Please enter the Date. (MMddyyyy)");
    }

    public void displayAddOrderBanner() {
        io.print("");
        io.print("~~~ Add an Order ~~~");
    }
    
    public String getStateChoice() {
        return io.readString("Please enter the State from which the order is being placed.");
    }
    
    public String getMaterialChoice() {
        return io.readString("Please enter the type of flooring to be installed.");
    }
    
    public String getCustomerNameChoice() {
        return io.readString("Please enter the Customer's Name.");
    }
    
    public BigDecimal getAreaChoice() {
        return io.readBig("Please enter the area (in Sq. Ft.) of the desired flooring work.");
    }
    
//    public String[] getNewOrderInfo(List<Tax> taxes, List<Material> materials) {
//        String state = "";
//        String material = "";
//        String customerName = "";
//        BigDecimal area = BigDecimal.ZERO;
//        String tempState;
//        String tempMaterial;
//
//        tempState = io.readString("Please enter the State from which the order is being placed.");
//        while (state.equals("")) {
//            if (taxes.stream().anyMatch(t -> t.getState().compareToIgnoreCase(tempState) == 0)) {
//                state = tempState;
//                break;
//            }
//        }
//
//        tempMaterial = io.readString("Please enter the type of flooring to be installed.");
//        while (material.equals("")) {
//            if (materials.stream().anyMatch(m -> m.getMaterialType().compareToIgnoreCase(tempMaterial) == 0)) {
//                material = tempMaterial;
//                break;
//            }
//        }
//
//        customerName = io.readString("Please enter the Customer's Last Name");
//        area = io.readBig("Please enter the area (in Sq. Ft.) of the desired flooring work.");
//
//        String[] currentOrder = {state, material, customerName, area.toString()};
//        return currentOrder;
//    }

    public String displayOrder(Order currentOrder) {
        io.print("");
        if (currentOrder != null) {
            io.print(currentOrder.getOrderNumber() + " - Name: "
                    + currentOrder.getCustomerName() + ", State: "
                    + currentOrder.getState() + ", Area: "
                    + currentOrder.getArea().setScale(2, HALF_UP) + ", Flooring Type: "
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

    public String[] getEditOrderInfo() {
        String customerName = io.readString("Please enter the Customer's Name");
        String area = io.readString("Please enter the desired Area to be floored.");
        String productType = io.readString("Please enter the material to be used.");
        String state = io.readString("Please enter the State in which the work will be done.");

        String[] infoArray = new String[4];
        if (state != null) {
            infoArray[0] = state;
        }
        if (productType != null) {
            infoArray[1] = productType;
        }
        if (customerName != null) {
            infoArray[2] = customerName;
        }
        if (area != null) {
            infoArray[3] = area;
        }

//        currentOrder.setCustomerName(customerName);
//        currentOrder.setArea(area);
//        currentOrder.setProductType(productType);
//        currentOrder.setState(state);
        return infoArray;
    }
    
    public void displayInvalidEntry() {
        io.print("");
        io.print("Invalid Entry.");
    }
    
    public void displayInvalidEditStateEntry() {
        io.print("Invalid input. This Order's State field will remain unchanged.");
    }
    
    public void displayInvalidEditMaterialEntry() {
        io.print("Invalid input. This Order's Flooring Type field will remain unchanged.");
    }
    
    public void displayInvalidEditAreaEntry() {
        io.print("Invalid input. This Order's Area field will remain unchanged.");
    }

    public void displayCancelOrderBanner() {
        io.print("~~~ Cancel an Order ~~~");
    }

    public void displayCancelSuccessBanner() {
        io.readString("Order successfully cancelled. Please hit enter to continue.");
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
    
    public void displayTrainingConfig() {
        io.print("Program is in Training Mode. No changes will be saved to file.");
    }
}
