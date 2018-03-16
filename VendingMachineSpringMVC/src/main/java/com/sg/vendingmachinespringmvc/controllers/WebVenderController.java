/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.controllers;

import com.sg.vendingmachinespringmvc.dao.WebVenderPersistenceException;
import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Product;
import com.sg.vendingmachinespringmvc.service.WebVenderInsufficientFundsException;
import com.sg.vendingmachinespringmvc.service.WebVenderServiceLayer;
import com.sg.vendingmachinespringmvc.service.WebVenderZeroInventoryException;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Alex
 */
@Controller
public class WebVenderController {

    WebVenderServiceLayer service;
    private Change change = new Change();
    private BigDecimal zero = BigDecimal.ZERO;
    private BigDecimal[] changeData = {zero, zero, zero};
    private Product selectedProduct;
    private String errorMsg = "";

    @Inject
    public WebVenderController(WebVenderServiceLayer service) {
        this.service = service;
    }

    @GetMapping("/")
    public String displayVenderHome(Model model) throws WebVenderPersistenceException {
        List<Product> productList = service.getAllProducts();
        model.addAttribute("productList", productList);
        model.addAttribute("change", change);
        model.addAttribute("quarters", changeData[0]);
        model.addAttribute("dimes", changeData[1]);
        model.addAttribute("nickels", changeData[2]);
        model.addAttribute("selectedProduct", selectedProduct);
        model.addAttribute("errorMsg", errorMsg);
        errorMsg = "";
        return "index";
    }

//    @PutMapping("/addQuarter")
//    public String deposit(BigDecimal deposit, Model model) {
//        //pseudo
//        BigDecimal bigD = new BigDecimal("~~~~button ID~~~~");
//        
//        model.addAttribute(bigD)
//        
//        return "redirect:/";
//    }
    @PostMapping("/deposit")
    public String deposit(String amount, Model model) {
        BigDecimal bigD = new BigDecimal(amount);
        model.addAttribute("change", service.deposit(change, bigD));
        return "redirect:/";
    }

    @PostMapping("/selectProduct")
    public String selectProduct(String productName, Model model) {
        try {
            selectedProduct = service.getProduct(productName);
        } catch (WebVenderPersistenceException | WebVenderZeroInventoryException e) {
            errorMsg = e.getMessage();
        }
        return "redirect:/";
    }

    //should be POST mapping - 
    @GetMapping("/purchaseSelection")
    public String purchaseSelection(Product product, Model model) {
        try {
            changeData = service.calcChange(selectedProduct, change);
            service.updateStock(selectedProduct);
            selectedProduct = null;
            change.setTotal(BigDecimal.ZERO);
            errorMsg = "Thank You!";
        } catch (WebVenderInsufficientFundsException | WebVenderPersistenceException ex) {
            errorMsg = ex.getMessage();
        }
        return "redirect:/";
    }

    @PostMapping("/refundDeposit")
    public String refundDeposit(Model model) {
        changeData = service.calcRefund(change);
        change.setTotal(BigDecimal.ZERO);
        selectedProduct = null;
        return "redirect:/";
    }
}
