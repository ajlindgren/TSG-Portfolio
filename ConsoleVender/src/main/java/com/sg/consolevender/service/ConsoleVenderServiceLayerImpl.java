/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.consolevender.service;

import com.sg.consolevender.dao.ConsoleVenderAuditDao;
import com.sg.consolevender.dao.ConsoleVenderDao;
import com.sg.consolevender.dao.ConsoleVenderPersistenceException;
import com.sg.consolevender.dto.Change;
import static com.sg.consolevender.dto.Coin.DIME;
import static com.sg.consolevender.dto.Coin.NICKEL;
import static com.sg.consolevender.dto.Coin.QUARTER;
import com.sg.consolevender.dto.Product;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Alex
 */
public class ConsoleVenderServiceLayerImpl implements ConsoleVenderServiceLayer {

    ConsoleVenderDao dao;
    ConsoleVenderAuditDao auditDao;

    public ConsoleVenderServiceLayerImpl(ConsoleVenderDao dao, ConsoleVenderAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public List<Product> getAllProducts() throws ConsoleVenderPersistenceException {
        return dao.getAllProducts().stream()
                .filter(p -> p.getInStock() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public Product getProduct(String productName) throws ConsoleVenderPersistenceException, ConsoleVenderZeroInventoryException {
        Product product = dao.getProduct(productName);
        if (product != null)
            return product;
        else
            throw new ConsoleVenderZeroInventoryException("That item is out of stock!");
    }

    /**
     * try to combine these deposit methods into one method, potentially calling
     * ENUMs to differentiate.
     *
     */
    public Change deposit5(Change change) {
        BigDecimal bigD = new BigDecimal("5.00");
        change.addToTotal(bigD);
        return change;
    }

    public Change deposit1(Change change) {
        BigDecimal bigD = new BigDecimal("1.00");
        change.addToTotal(bigD);
        return change;
    }

    public Change depositQuarter(Change change) {
        BigDecimal bigD = QUARTER.getValue();
        change.addToTotal(bigD);
        return change;
    }

    public Change depositDime(Change change) {
        BigDecimal bigD = DIME.getValue();
        change.addToTotal(bigD);
        return change;
    }

    public Change depositNickel(Change change) {
        BigDecimal bigD = NICKEL.getValue();
        change.addToTotal(bigD);
        return change;
    }

    public BigDecimal[] calcChange(Product product, Change change) throws ConsoleVenderInsufficientFundsException {
        BigDecimal currentChange = change.getTotal().subtract(product.getProductPrice());

        if (currentChange.compareTo(BigDecimal.ZERO) >= 0) {
            BigDecimal[] firstDiv = currentChange.divideAndRemainder(QUARTER.getValue());
            BigDecimal quarters = firstDiv[0];
            BigDecimal[] secondDiv = firstDiv[1].divideAndRemainder(DIME.getValue());
            BigDecimal dimes = secondDiv[0];
            BigDecimal nickels = secondDiv[1].divide(NICKEL.getValue());

            BigDecimal[] changeData = {quarters, dimes, nickels, currentChange};
            return changeData;
        } else {
            throw new ConsoleVenderInsufficientFundsException("Insufficient Funds!");
        }
    }

    public BigDecimal[] calcRefund(Change change) {
        BigDecimal[] firstDiv = change.getTotal().divideAndRemainder(QUARTER.getValue());
        BigDecimal quarters = firstDiv[0];
        BigDecimal[] secondDiv = firstDiv[1].divideAndRemainder(DIME.getValue());
        BigDecimal dimes = secondDiv[0];
        BigDecimal nickels = secondDiv[1].divide(NICKEL.getValue());

        BigDecimal[] changeData = {quarters, dimes, nickels, change.getTotal()};
        return changeData;
    }

    public void updateStock(Product product) throws ConsoleVenderPersistenceException {
        product.setInStock(product.getInStock() - 1);
        dao.updateInventory(product);
    }

//    private void validateProductData(Product product) throws ConsoleVenderDataValidationException {
//        if (product.getProductName() == null
//            || product.getProductName().trim().length() == 0
//            || product.getProductPrice() == null
//            || product.getProductPrice().equals(new BigDecimal(0))
//            || product.getInStock() == 0) {
//            throw new ConsoleVenderDataValidationException("ERROR: All fields [ProductName, ProductPrice, and inStock] are required.");
//        }
//    }
}
