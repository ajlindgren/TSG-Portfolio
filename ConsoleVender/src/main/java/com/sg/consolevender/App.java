/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.consolevender;

import com.sg.consolevender.controller.ConsoleVenderController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Alex
 */
public class App {

    public static void main(String[] args) {
//        UserIO myIo = new UserIOConsoleImpl();
//        ConsoleVenderView myView = new ConsoleVenderView(myIo);
//        ConsoleVenderDao myDao = new ConsoleVenderDaoFileImpl();
//        ConsoleVenderServiceLayer myService = new ConsoleVenderServiceLayerImpl(myDao);
//        ConsoleVenderController controller = new ConsoleVenderController(myService, myView);
//        controller.run();

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        ConsoleVenderController controller = ctx.getBean("controller", ConsoleVenderController.class);
        controller.run();
    }
}
