/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.consolevender.dao;

import com.sg.consolevender.dto.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import static java.math.RoundingMode.HALF_UP;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Alex
 */
public class ConsoleVenderDaoFileImpl implements ConsoleVenderDao {
    
    public static final String INVENTORY_FILE = "Inventory.txt";
    public static final String DELIMITER = "::";

    private Map<String, Product> products = new HashMap<>();
    
    @Override
    public List<Product> getAllProducts() throws ConsoleVenderPersistenceException {
        loadInventory();
        return new ArrayList<Product>(products.values());
    }

    @Override
    public Product getProduct(String productName) throws ConsoleVenderPersistenceException {
        loadInventory();
        return products.get(productName);
    }
    
    @Override
    public void loadInventory() throws ConsoleVenderPersistenceException {
        Scanner scanner;
        BigDecimal bigD = new BigDecimal(0);

        try {
            //create scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e) {
            throw new ConsoleVenderPersistenceException("-_- Could not load inventory data into memory.", e);
        }
        
        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            
            Product currentProduct = new Product(currentTokens[0]);
            currentProduct.setProductPrice(new BigDecimal(currentTokens[1]));
            currentProduct.setInStock(Integer.parseInt(currentTokens[2]));

            products.put(currentProduct.getProductName(), currentProduct);
        }
        // close scanner
        scanner.close();
    }
    
    @Override
    public void writeInventory() throws ConsoleVenderPersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        } catch (IOException e) {
            throw new ConsoleVenderPersistenceException("Could not save inventory data.", e);
        }

        List<Product> productList = new ArrayList<>(products.values());
        for (Product currentProduct : productList) {
            out.println(currentProduct.getProductName() + DELIMITER
                    + currentProduct.getProductPrice() + DELIMITER
                    + currentProduct.getInStock() + DELIMITER);
            out.flush();
        }
        out.close();
    }
    
}
