/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alex
 */
@Component
public class WebVenderDaoFileImpl implements WebVenderDao {

    public static final String INVENTORY_FILE = "Inventory.txt";
    public static final String DELIMITER = "::";

    private Map<String, Product> products = new HashMap<>();

    @Override
    public List<Product> getAllProducts() throws WebVenderPersistenceException {
        loadInventory();
        return new ArrayList<Product>(products.values());
    }

    @Override
    public Product getProduct(String productName) throws WebVenderPersistenceException {
        loadInventory();
        return products.get(productName);
    }

    //needs an updateInventory public method, loadInventory and writeInventory need to be private.
    @Override
    public Product updateInventory(Product product) throws WebVenderPersistenceException {
        Product nullCheck = products.replace(product.getName(), product);
        writeInventory();
        return nullCheck;
    }

    private void loadInventory() throws WebVenderPersistenceException {
        Scanner scanner;
        BigDecimal bigD = new BigDecimal(0);

        try {
            //create scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(getClass().getClassLoader().getResource(INVENTORY_FILE).getFile()
            )));
        } catch (FileNotFoundException e) {
            throw new WebVenderPersistenceException("-_- Could not load inventory data into memory.", e);
        }

        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            Product currentProduct = new Product(currentTokens[1]);
            currentProduct.setId(Integer.parseInt(currentTokens[0]));
            currentProduct.setPrice(new BigDecimal(currentTokens[2]));
            currentProduct.setQuantity(Integer.parseInt(currentTokens[3]));

            products.put(currentProduct.getName(), currentProduct);
        }
        // close scanner
        scanner.close();
    }

    private void writeInventory() throws WebVenderPersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(getClass().getClassLoader().getResource(INVENTORY_FILE).getFile()
            ));
        } catch (IOException e) {
            throw new WebVenderPersistenceException("Could not save inventory data.", e);
        }

        List<Product> productList = new ArrayList<>(products.values());
        for (Product currentProduct : productList) {
            out.println(currentProduct.getId() + DELIMITER
                    + currentProduct.getName() + DELIMITER
                    + currentProduct.getPrice() + DELIMITER
                    + currentProduct.getQuantity() + DELIMITER);
            out.flush();
        }
        out.close();
    }

}
