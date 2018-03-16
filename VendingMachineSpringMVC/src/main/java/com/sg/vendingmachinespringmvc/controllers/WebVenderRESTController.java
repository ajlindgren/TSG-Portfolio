/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.controllers;

import com.sg.vendingmachinespringmvc.dao.WebVenderPersistenceException;
import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.ChangeData;
import com.sg.vendingmachinespringmvc.model.Product;
import com.sg.vendingmachinespringmvc.service.WebVenderInsufficientFundsException;
import com.sg.vendingmachinespringmvc.service.WebVenderServiceLayer;
import com.sg.vendingmachinespringmvc.service.WebVenderZeroInventoryException;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alex
 */
@CrossOrigin
@RestController
public class WebVenderRESTController {

    private WebVenderServiceLayer service;

    @Inject
    public WebVenderRESTController(WebVenderServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value="/items", method=RequestMethod.GET)
    public List<Product> getAllProducts() throws WebVenderPersistenceException {
            return service.getAllProducts();
    }

    @GetMapping("/money/{amount}/item/{name}")
    public ChangeData purchaseSelection(@PathVariable("amount") BigDecimal amount, @PathVariable("name") String name)
            throws WebVenderPersistenceException, WebVenderZeroInventoryException, WebVenderInsufficientFundsException {
        Change change = new Change();
        change.setTotal(amount);
        Product product = service.getProduct(name);
        ChangeData changeData = new ChangeData(service.calcChange(product, change));
        service.updateStock(product);
        return changeData;
    }
}
