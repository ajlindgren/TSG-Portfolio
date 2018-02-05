/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Alex
 */
public class DVDLibraryDaoFileImpl implements DVDLibraryDao {
    
    public static final String LIBRARY_FILE = "library.txt";
    public static final String DELIMITER = "::";
    
    private Map<String, DVD> dvds = new HashMap<>();
    
    @Override
    public DVD addDVD(String dvdTitle, DVD dvd) throws DVDLibraryDaoException {
        DVD newDVD = dvds.put(dvdTitle, dvd);
        writeLibrary();
        return newDVD;
    }

    @Override
    public List<DVD> getAllDVDs() throws DVDLibraryDaoException {
        loadLibrary();
        return new ArrayList<>(dvds.values());
    }

    @Override
    public DVD getDVD(String dvdTitle) throws DVDLibraryDaoException {
        loadLibrary();
        return dvds.get(dvdTitle);
    }

    @Override
    public DVD removeDVD(String dvdTitle) throws DVDLibraryDaoException {
        loadLibrary();
        DVD nullCheck = dvds.remove(dvdTitle);
        writeLibrary();
        return nullCheck;
    }

    @Override
    public DVD editDVD(String dvdTitle, DVD dvd) throws DVDLibraryDaoException {
        DVD nullCheck = dvds.replace(dvdTitle, dvd);
        writeLibrary();
        return nullCheck;
    }
    
    private void loadLibrary() throws DVDLibraryDaoException {
        Scanner scanner;
        
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryDaoException(":'( Could not load library data into memory.", e);
        }
        String currentLine;
        String[] currentTokens;
        while(scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            DVD currentDVD = new DVD(currentTokens[0]);
            currentDVD.setDirectorName(currentTokens[1]);
            currentDVD.setReleaseDate(LocalDate.parse(currentTokens[2], DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            currentDVD.setStudio(currentTokens[3]);
            currentDVD.setMpaaRating(currentTokens[4]);
            currentDVD.setUserNote(currentTokens[5]);
            
            dvds.put(currentDVD.getTitle(), currentDVD);
        }
        scanner.close();
    }
    
    private void writeLibrary() throws DVDLibraryDaoException {
        PrintWriter out;
        List<DVD> dvdList = this.getAllDVDs();
        
        try {
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch (IOException e) {
            throw new DVDLibraryDaoException("Could not save library data.", e);
        }
        
        
        for (DVD currentDVD : dvdList) {
            out.println(currentDVD.getTitle() + DELIMITER
                        + currentDVD.getDirectorName() + DELIMITER
                        + currentDVD.getReleaseDate() + DELIMITER
                        + currentDVD.getStudio() + DELIMITER
                        + currentDVD.getMpaaRating() + DELIMITER
                        + currentDVD.getUserNote() + DELIMITER);
            out.flush();
        }
        out.close();
    }

    @Override
    public List<DVD> getDVDsNewerThan(int ageInYears) throws DVDLibraryDaoException {
        return dvds.values()
                        .stream()
                        .filter(d -> d.getDVDAge() <= ageInYears)
                        .collect(Collectors.toList());
    }

    @Override
    public List<DVD> getDVDsByMpaaRating(String mpaaRating) throws DVDLibraryDaoException {
        return dvds.values()
                        .stream()
                        .filter(d -> d.getMpaaRating().equalsIgnoreCase(mpaaRating))
                        .collect(Collectors.toList());
    }

    @Override
    public List<DVD> getDVDsByDirector(String director) throws DVDLibraryDaoException {
        return dvds.values()
                        .stream()
                        .filter(s -> s.getDirectorName().equalsIgnoreCase(director))
                        .collect(Collectors.toList());
    }

    @Override
    public List<DVD> getDVDsByStudio(String studio) throws DVDLibraryDaoException {
        return dvds.values()
                        .stream()
                        .filter(s -> s.getStudio().equalsIgnoreCase(studio))
                        .collect(Collectors.toList());
    }

    @Override
    public double getAverageDVDAge() throws DVDLibraryDaoException {
        return dvds.values()
                        .stream()
                        .mapToLong(s -> s.getDVDAge())
                      //.mapToLong(DVD::getDVDAge)
                        .average()
                        .getAsDouble();
    }

    @Override
    public DVD getNewestDVD() throws DVDLibraryDaoException {
        DVD newest = dvds.values()
                    .stream()
                    .collect(Collectors.minBy(Comparator.comparing((DVD::getDVDAge))))
                  //.collect(Collectors.minBy(d -> d.getDVDAge());
                    .get();
        return newest;
    }

    @Override
    public DVD getOldestDVD() throws DVDLibraryDaoException {
        return dvds.values()
                    .stream()
                    .collect(Collectors.maxBy(Comparator.comparing((DVD::getDVDAge))))
                    .get();
    }

    @Override
    public double getAverageUserNotes() throws DVDLibraryDaoException {
        return dvds.values()
                        .stream()
                        .mapToLong(s -> s.getUserNote().length())
                      //.mapToLong(DVD::getUserNote.length()) ??????????
                        .average()
                        .getAsDouble();
    }
    
    
    
}
