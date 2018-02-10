/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary;

import com.sg.dvdlibrary.controller.DVDLibraryController;
import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryDaoFileImpl;
import com.sg.dvdlibrary.ui.DVDLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Alex
 */
public class App {
    
    public static void main(String[] args) {
        
//        //Dependency injection -- Inject IO implementation into View, and View and Dao into Controller
//        UserIO myIO = new UserIOConsoleImpl();
//        DVDLibraryView myView = new DVDLibraryView(myIO);
//        DVDLibraryDao myDao = new DVDLibraryDaoFileImpl();
//        DVDLibraryController controller = new DVDLibraryController(myDao, myView);
//        //Run the DVD Library program
//        controller.run();
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        DVDLibraryController controller = ctx.getBean("controller", DVDLibraryController.class);
        controller.run();
    }
}
