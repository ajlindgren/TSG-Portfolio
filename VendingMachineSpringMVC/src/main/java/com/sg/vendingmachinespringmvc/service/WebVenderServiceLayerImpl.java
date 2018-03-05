/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.WebVenderPersistenceException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import com.sg.vendingmachinespringmvc.dao.WebVenderDao;
import com.sg.vendingmachinespringmvc.model.Change;
import static com.sg.vendingmachinespringmvc.model.Coin.DIME;
import static com.sg.vendingmachinespringmvc.model.Coin.NICKEL;
import static com.sg.vendingmachinespringmvc.model.Coin.QUARTER;
import com.sg.vendingmachinespringmvc.model.Product;
import javax.inject.Inject;
import org.jboss.logging.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alex
 */
@Repository
public class WebVenderServiceLayerImpl implements WebVenderServiceLayer {

    WebVenderDao dao;

    @Inject
    public WebVenderServiceLayerImpl(WebVenderDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Product> getAllProducts() throws WebVenderPersistenceException {
        return dao.getAllProducts().stream()
                //.filter(p -> p.getQuantity() > 0)
                .sorted((p1, p2) -> p1.getId()-p2.getId())
                .collect(Collectors.toList());
    }

    @Override
    public Product getProduct(String productName) throws WebVenderPersistenceException, WebVenderZeroInventoryException {
        Product product = dao.getProduct(productName);
        if (product.getQuantity() > 0)
            return product;
        else
            throw new WebVenderZeroInventoryException("OUT OF STOCK!!!");
    }

    @Override
    public Change deposit(Change change, BigDecimal amount) {
        change.addToTotal(amount);
        return change;
    }
    @Override
    public Change deposit5(Change change) {
        BigDecimal bigD = new BigDecimal("5.00");
        change.addToTotal(bigD);
        return change;
    }
    @Override
    public Change deposit1(Change change) {
        BigDecimal bigD = new BigDecimal("1.00");
        change.addToTotal(bigD);
        return change;
    }
    @Override
    public Change depositQuarter(Change change) {
        BigDecimal bigD = QUARTER.getValue();
        change.addToTotal(bigD);
        return change;
    }
    @Override
    public Change depositDime(Change change) {
        BigDecimal bigD = DIME.getValue();
        change.addToTotal(bigD);
        return change;
    }
    @Override
    public Change depositNickel(Change change) {
        BigDecimal bigD = NICKEL.getValue();
        change.addToTotal(bigD);
        return change;
    }
    @Override
    public BigDecimal[] calcChange(Product product, Change change) throws WebVenderInsufficientFundsException {
        BigDecimal currentChange = change.getTotal().subtract(product.getPrice());

        if (currentChange.compareTo(BigDecimal.ZERO) >= 0) {
            BigDecimal[] firstDiv = currentChange.divideAndRemainder(QUARTER.getValue());
            BigDecimal quarters = firstDiv[0];
            BigDecimal[] secondDiv = firstDiv[1].divideAndRemainder(DIME.getValue());
            BigDecimal dimes = secondDiv[0];
            BigDecimal nickels = secondDiv[1].divide(NICKEL.getValue());

            BigDecimal[] changeData = {quarters, dimes, nickels, currentChange};
            return changeData;
        } else {
            throw new WebVenderInsufficientFundsException("Please insert: $" + currentChange.abs());
        }
    }
    @Override
    public BigDecimal[] calcRefund(Change change) {
        BigDecimal[] firstDiv = change.getTotal().divideAndRemainder(QUARTER.getValue());
        BigDecimal quarters = firstDiv[0];
        BigDecimal[] secondDiv = firstDiv[1].divideAndRemainder(DIME.getValue());
        BigDecimal dimes = secondDiv[0];
        BigDecimal nickels = secondDiv[1].divide(NICKEL.getValue());

        BigDecimal[] changeData = {quarters, dimes, nickels, change.getTotal()};
        return changeData;
    }
    @Override
    public void updateStock(Product product) throws WebVenderPersistenceException {
        product.setQuantity(product.getQuantity() - 1);
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
