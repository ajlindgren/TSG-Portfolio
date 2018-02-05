/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.consolevender;

import com.sg.consolevender.controller.ConsoleVenderController;
import com.sg.consolevender.dao.ConsoleVenderDao;
import com.sg.consolevender.dao.ConsoleVenderDaoFileImpl;
import com.sg.consolevender.service.ConsoleVenderServiceLayer;
import com.sg.consolevender.service.ConsoleVenderServiceLayerImpl;
import com.sg.consolevender.ui.ConsoleVenderView;
import com.sg.consolevender.ui.UserIO;
import com.sg.consolevender.ui.UserIOConsoleImpl;

/**
 *
 * @author Alex
 */
public class App {

    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        ConsoleVenderView myView = new ConsoleVenderView(myIo);
        ConsoleVenderDao myDao = new ConsoleVenderDaoFileImpl();
        ConsoleVenderServiceLayer myService = new ConsoleVenderServiceLayerImpl(myDao);
        ConsoleVenderController controller = new ConsoleVenderController(myService, myView);
        controller.run();
    }
}
