/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author Alex
 */
public class DVD {
   
    private final String title;
    private String directorName;
    private LocalDate releaseDate;
    private String studio;
    private String mpaaRating;
    private String userNote;   
    
    public DVD(String title) {
        this.title = title;
    }

    //Title is a read-only field - the Title of a DVD object is set permanently upon instantiation.
    public String getTitle() {
        return title;
    }                           

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }
    
    public long getDVDAge() {
        Period p = releaseDate.until(LocalDate.now());
        return p.getYears();
    }
    
}
