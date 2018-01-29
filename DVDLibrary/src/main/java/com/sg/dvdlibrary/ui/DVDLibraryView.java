/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author Alex
 */
public class DVDLibraryView {

    private UserIO io;

    public DVDLibraryView(UserIO io) {
        this.io = io;
    }

    public DVD getNewDVDInfo() {
        String dvdTitle = io.readString("Please enter DVD Title");
        String directorName = io.readString("Please enter the Director's Name");
        String releaseDate = io.readString("Please enter DVD Release Date (mm/dd/yyyy)");
        String studio = io.readString("Please enter the DVD's Publishing Studio");
        String mpaaRating = io.readString("Please enter the DVD's MPAA Rating");
        String userNote = io.readString("Please enter a personal note for this DVD");

        DVD currentDVD = new DVD(dvdTitle);
        currentDVD.setDirectorName(directorName);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setStudio(studio);
        currentDVD.setMpaaRating(mpaaRating);
        currentDVD.setUserNote(userNote);
        return currentDVD;
    }

    public DVD getEditDVDInfo(DVD currentDVD) {
        String directorName = io.readString("Please enter the Director's Name");
        String releaseDate = io.readString("Please enter DVD Release Date (mm/dd/yyyy)");
        String studio = io.readString("Please enter the DVD's Publishing Studio");
        String mpaaRating = io.readString("Please enter the DVD's MPAA Rating");
        String userNote = io.readString("Please enter a personal note for this DVD");

        currentDVD.setDirectorName(directorName);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setStudio(studio);
        currentDVD.setMpaaRating(mpaaRating);
        currentDVD.setUserNote(userNote);
        return currentDVD;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List all DVDs in the Library");
        io.print("2. Search Library by DVD Title");
        io.print("3. Add a DVD to the Library");
        io.print("4. Remove a DVD from the Library");
        io.print("5. Edit the info for a DVD in the Library");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.", 1, 6);
    }

    public void displayAddDVDBanner() {
        io.print("~~~ Add a DVD ~~~");
    }

    public void displayAddSuccessBanner() {
        io.print("~~~ DVD Added ~~~");
    }

    public void displayDVDList(List<DVD> dvdList) {
        for (DVD currentDVD : dvdList) {
            io.print(currentDVD.getTitle() + " - Directed by: "
                    + currentDVD.getDirectorName() + " - Release date: "
                    + currentDVD.getReleaseDate() + " - Publishing studio: "
                    + currentDVD.getStudio() + " - MPAA Rating: "
                    + currentDVD.getMpaaRating() + " - Personal note: "
                    + currentDVD.getUserNote());
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayDisplayAllBanner() {
        io.print("~~~ Display All DVDs ~~~");
    }

    public void displayDVD(DVD dvd) {
        if (dvd != null) {
            io.print(dvd.getTitle() + " - Directed by: "
                    + dvd.getDirectorName() + " - Release date: "
                    + dvd.getReleaseDate() + " - Publishing studio: "
                    + dvd.getStudio() + " - MPAA Rating: "
                    + dvd.getMpaaRating() + " - Personal note: "
                    + dvd.getUserNote());
        } else {
            io.print("No such DVD in the Library.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayDisplayDVDBanner() {
        io.print("~~~ Display DVD ~~~");
    }

    public String getDVDTitleChoice() {
        return io.readString("Please enter the DVD Title.");
    }

    public void displayRemoveDVDBanner() {
        io.print("~~~ Remove a DVD ~~");
    }

    public void displayRemoveSuccessBanner() {
        io.readString("DVD successfully removed. Please hit enter to continue.");
    }

    public void displayNoEntryBanner() {
        io.readString("No such entry in the Library. Please hit enter to continue.");
    }

    public void displayEditDVDBanner() {
        io.print("~~~ Edit a DVD Entry ~~~");
    }

    public void displayEditSuccessBanner() {
        io.readString("DVD Entry successfully edited. Please hit enter to continue.");
    }

    public void displayExitBanner() {
        io.print("Closing DVD Library. Thank You.");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("-~= ERROR =~-");
        io.print(errorMsg);
    }
}
