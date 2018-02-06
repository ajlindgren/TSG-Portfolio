/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.consolevender.ui;

import com.sg.consolevender.dto.Change;
import com.sg.consolevender.dto.Product;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Alex
 */
public class ConsoleVenderView {

    UserIO io;

    public ConsoleVenderView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("1. Display Inventory");
        io.print("2. Insert $5");
        io.print("3. Insert $1");
        io.print("4. Insert $.25");
        io.print("5. Insert $.10");
        io.print("6. Insert $.05");
        io.print("7. Purchase Item");
        io.print("8. Refund Deposit");
        io.print("9. Exit Vending Machine");

        try {
            return io.readInt("Please select from the"
                    + " above choices.", 1, 9);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public String getPurchaseSelection() {
        io.print("");
        return io.readString("Enter the Name of your desired Product: ");
    }

    public void printEntryBanner() {
        io.print("Welcome to the Vend-o-Tron!!");
        io.print("----~~~~~=========~~~~~----");
    }

    public void displayCurrentChange(Change change) {
        io.print("");
        io.print("Current Deposit: $" + change.getTotal());
        io.print("");
    }

    public void displayAllProducts(List<Product> productList) {
        io.print("");
        for (Product currentProduct : productList) {
            io.print(currentProduct.getProductName() + ": $"
                    + currentProduct.getProductPrice() + " - Left in Stock: "
                    + currentProduct.getInStock());
        }
        io.print("");

    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public void displayChange(BigDecimal[] calcChange) {
        io.print("");
        io.print("Selection Vended!");
        io.print("You receive $" + calcChange[3] + " in change.");
        io.print("The machine dispenses " + calcChange[0] + " Quarter(s), " + calcChange[1] + " Dime(s), and " + calcChange[2] + " Nickel(s).");
        io.print("");
    }

    public void displayRefund(BigDecimal[] calcRefund) {
        io.print("");
        io.print("Refund Success!");
        io.print("You receive your $" + calcRefund[3] + " in change.");
        io.print("The machine dispenses " + calcRefund[0] + " Quarter(s), " + calcRefund[1] + " Dime(s), and " + calcRefund[2] + " Nickel(s).");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command.");
        io.print("");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayInsertCoinsPrompt() {
        io.print("");
        io.print("Please Insert Coins before making a selection.");
        io.print("");
    }

    public void displayInsufficientFundsPrompt() {
        io.print("");
        io.print("Insufficient Funds!");
        io.print("Please insert additional funds to purchase the product.");
    }
    
    public void displayZeroInventory(Product product) {
        io.print("");
        io.print(product.getProductName() + " is out of stock! Sorry!");
        io.print("");
    }
    
    public void displayNoSuchProduct() {
        io.print("");
        io.print("Sorry, that Product does not exist.");
        io.print("");
    }
}
