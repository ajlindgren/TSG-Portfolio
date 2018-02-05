/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryDaoException;
import com.sg.dvdlibrary.dao.DVDLibraryDaoFileImpl;
import com.sg.dvdlibrary.dto.DVD;
import com.sg.dvdlibrary.ui.DVDLibraryView;
import java.util.List;

/**
 *
 * @author Alex
 */
public class DVDLibraryController {
    
    DVDLibraryDao dao;
    DVDLibraryView view;
    
    public DVDLibraryController(DVDLibraryDao dao, DVDLibraryView view) {
        this.dao = dao;
        this.view = view;
    }
    
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {
                
                menuSelection = getMenuSelection();
                
                switch (menuSelection) {
                    case 1:
                        listDVDs();
                        break;
                    case 2:
                        viewDVD();
                        break;
                    case 3:
                        addDVD();
                        break;
                    case 4:
                        removeDVD();
                        break;
                    case 5:
                        editDVD();
                        break;
                    case 6:
                        filterMenu();
                        break;
                    case 7:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        } catch (DVDLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }        
    }
    
    private void filterMenu() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
        while (keepGoing) {
            
            menuSelection = getFilterMenuSelection();
            
            switch (menuSelection) {
                case 1:
                    filterNewerThan10();
                    break;
                case 2:
                    filterNewerThan20();
                    break;
                case 3:
                    filterByDirector();
                    break;
                case 4:
                    filterByStudio();
                    break;
                case 5:
                    filterByMpaaRating();
                    break;
                case 6:
                    filterNewestDVD();
                    break;
                case 7:
                    filterOldestDVD();
                    break;
                case 8:
                    filterAverageDVDAge();
                case 9:
                    filterAverageUserNoteLength();
                case 10:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }
        }
        } catch (DVDLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private int getFilterMenuSelection() {
        return view.printFilterMenuAndGetSelection();
    }
    
    private void addDVD() throws DVDLibraryDaoException {
        view.displayAddDVDBanner();
        DVD newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD.getTitle(), newDVD);
        view.displayAddSuccessBanner();
    }
    
    private void listDVDs() throws DVDLibraryDaoException {
        view.displayDisplayAllBanner();
        List<DVD> dvdList = dao.getAllDVDs();
        view.displayDVDList(dvdList);
    }
    
    private void viewDVD() throws DVDLibraryDaoException {
        view.displayDisplayDVDBanner();
        String dvdTitle = view.getDVDTitleChoice();
        DVD dvd = dao.getDVD(dvdTitle);
        view.displayDVD(dvd);
    }
    
    private void removeDVD() throws DVDLibraryDaoException {
        view.displayRemoveDVDBanner();
        String dvdTitle = view.getDVDTitleChoice();
        DVD nullCheck = dao.removeDVD(dvdTitle);
        if (nullCheck == null)
            view.displayNoEntryBanner();
        else
            view.displayRemoveSuccessBanner();
    }
    
    private void editDVD() throws DVDLibraryDaoException {
        view.displayEditDVDBanner();
        String dvdTitle = view.getDVDTitleChoice();
        DVD dvd = dao.getDVD(dvdTitle);
        DVD editedDVD = view.getEditDVDInfo(dvd);
        DVD nullCheck = dao.editDVD(dvdTitle, editedDVD);
        if (nullCheck == null)
            view.displayNoEntryBanner();
        else
            view.displayEditSuccessBanner();
    }
    
    private void unknownCommand() throws DVDLibraryDaoException {
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage() throws DVDLibraryDaoException {
        view.displayExitBanner();
    }

    private void filterNewerThan10() throws DVDLibraryDaoException {
        List<DVD> dvdList = dao.getDVDsNewerThan(10);
        view.displayDVDList(dvdList);
    }

    private void filterNewerThan20() throws DVDLibraryDaoException {
        List<DVD> dvdList = dao.getDVDsNewerThan(20);
        view.displayDVDList(dvdList);
    }

    private void filterByDirector() throws DVDLibraryDaoException {
        String director = view.getDirectorName();
        List<DVD> dvdList = dao.getDVDsByDirector(director);
        view.displayDVDList(dvdList);
    }

    private void filterByStudio() throws DVDLibraryDaoException {
        String studio = view.getStudio();
        List<DVD> dvdList = dao.getDVDsByStudio(studio);
        view.displayDVDList(dvdList);
    }

    private void filterByMpaaRating() throws DVDLibraryDaoException {
        String mpaaRating = view.getMpaaRating();
        List<DVD> dvdList = dao.getDVDsByDirector(mpaaRating);
        view.displayDVDList(dvdList);
    }

    private void filterNewestDVD() throws DVDLibraryDaoException {
        DVD dvd = dao.getNewestDVD();
        view.displayDVD(dvd);
    }

    private void filterOldestDVD() throws DVDLibraryDaoException {
        DVD dvd = dao.getOldestDVD();
        view.displayDVD(dvd);
    }

    private void filterAverageDVDAge() throws DVDLibraryDaoException {
        double avg = dao.getAverageDVDAge();
        view.displayAverageDVDAge(avg);
    }

    private void filterAverageUserNoteLength() throws DVDLibraryDaoException {
        double avg = dao.getAverageUserNotes();
        view.displayAverageUserNotes(avg);
    }
    
}
