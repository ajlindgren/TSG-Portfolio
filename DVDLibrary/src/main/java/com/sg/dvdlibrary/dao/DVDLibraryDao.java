/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Alex
 */
public interface DVDLibraryDao {
    
    /**
     * Adds the given DVD to the Library and associates it with the given DVD title.
     * If there is already a DVD associated with the given title, it will return
     * that DVD object, otherwise it will return null.
     * 
     * @param dvdTitle title with which DVD is to be associated
     * @param dvd DVD to be added to the Library
     * @return the DVD object previously associated with the given title if it exists, null otherwise
     */
    DVD addDVD(String dvdTitle, DVD dvd) throws DVDLibraryDaoException;
    
    /**
     * Returns a String array containing the titles of all DVDs in the Library.
     * 
     * @return String array containing the titles of all the DVDs in the Library. 
     */
    List<DVD> getAllDVDs() throws DVDLibraryDaoException;
    
    /**
     * Returns the DVD object associated with the given title.
     * Returns null if no such DVD exists.
     * 
     * @param dvdTitle title of the DVD to retrieve
     * @return  the DVD object associated with the given title, null if no such DVD exists
     */
    DVD getDVD(String dvdTitle) throws DVDLibraryDaoException;
    
    /**
     * Removes from the Library the DVD associated with the given title.
     * Returns the DVD object that is being removed or null if there is 
     * no DVD associated with the given title
     * 
     * @param dvdTitle title of DVD to be removed
     */
    DVD removeDVD(String dvdTitle) throws DVDLibraryDaoException;
    
    /**
     * Sets the member fields of the DVD object associated with the given title.
     * Returns the edited DVD object or null if there is no DVD associated with the title.
     * 
     * @param dvdTitle title of DVD to be edited
     */
    DVD editDVD(String dvdTitle, DVD dvd) throws DVDLibraryDaoException;
    
    List<DVD> getDVDsNewerThan(int ageInYears) throws DVDLibraryDaoException;
    
    List<DVD> getDVDsByMpaaRating(String mpaaRating) throws DVDLibraryDaoException;
    
    //while searching by director, DVDs should be sorted into separate data structures by MPAA rating
    List<DVD> getDVDsByDirector(String director) throws DVDLibraryDaoException;
    
    List<DVD> getDVDsByStudio(String studio) throws DVDLibraryDaoException;
    
    double getAverageDVDAge() throws DVDLibraryDaoException;
    
    DVD getNewestDVD() throws DVDLibraryDaoException;
    
    DVD getOldestDVD() throws DVDLibraryDaoException;
    
    double getAverageUserNotes() throws DVDLibraryDaoException;
}
