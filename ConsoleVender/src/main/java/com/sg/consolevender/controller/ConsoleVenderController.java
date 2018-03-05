/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.consolevender.controller;

import com.sg.vendingmachinespringmvc.dao.ConsoleVenderPersistenceException;
import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Product;
import com.sg.vendingmachinespringmvc.service.ConsoleVenderInsufficientFundsException;
import com.sg.vendingmachinespringmvc.service.ConsoleVenderServiceLayer;
import com.sg.vendingmachinespringmvc.service.ConsoleVenderZeroInventoryException;
import com.sg.consolevender.ui.ConsoleVenderView;
import java.math.BigDecimal;

/**
 *
 * @author Alex
 */
public class ConsoleVenderController {

    private ConsoleVenderView view;
    private ConsoleVenderServiceLayer service;

    public ConsoleVenderController(ConsoleVenderServiceLayer service, ConsoleVenderView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        Change change = new Change();

        try {
            entrySequence();
        } catch (ConsoleVenderPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }

        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listProducts();
                        break;
                    case 2:
                        deposit5(change);
                        break;
                    case 3:
                        deposit1(change);
                        break;
                    case 4:
                        depositQuarter(change);
                        break;
                    case 5:
                        depositDime(change);
                        break;
                    case 6:
                        depositNickel(change);
                        break;
                    case 7:
                        if (change.getTotal().compareTo(BigDecimal.ZERO) <= 0) {
                            displayInsertCoinsPrompt();
                            break;
                        }
                        purchaseSelection(change);
                        break;
                    case 8:
                        refundDeposit(change);
                        break;
                    case 9:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
        } catch (ConsoleVenderPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
        exitMessage();
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void entrySequence() throws ConsoleVenderPersistenceException {
        view.printEntryBanner();
        listProducts();
    }

    private void listProducts() throws ConsoleVenderPersistenceException {
        view.displayAllProducts(service.getAllProducts());
    }

    private void deposit5(Change change) {
        view.displayCurrentChange(service.deposit5(change));
    }

    private void deposit1(Change change) {
        view.displayCurrentChange(service.deposit1(change));
    }

    private void depositQuarter(Change change) {
        view.displayCurrentChange(service.depositQuarter(change));
    }

    private void depositDime(Change change) {
        view.displayCurrentChange(service.depositDime(change));
    }

    private void depositNickel(Change change) {
        view.displayCurrentChange(service.depositNickel(change));
    }

    //move validations to Service Layer, create exceptions to be thrown FROM service, and catch them here in Controller.
    private void purchaseSelection(Change change) {
        try {
        Product product = service.getProduct(view.getPurchaseSelection());
        view.displayChange(service.calcChange(product, change));
                change.setTotal(new BigDecimal("0.00"));
                service.updateStock(product);
        } catch (ConsoleVenderPersistenceException | ConsoleVenderZeroInventoryException e) {
            view.displayNoSuchProduct();
        } catch (ConsoleVenderInsufficientFundsException e) {
            view.displayInsufficientFundsPrompt();
            view.displayCurrentChange(change);
        }
    }

    private void refundDeposit(Change change) {
        if (change.getTotal().compareTo(BigDecimal.ZERO) > 0) {
            view.displayRefund(service.calcRefund(change));
        }
        change.setTotal(new BigDecimal("0.00"));
        view.displayCurrentChange(change);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    private void displayInsertCoinsPrompt() {
        view.displayInsertCoinsPrompt();
    }
}
