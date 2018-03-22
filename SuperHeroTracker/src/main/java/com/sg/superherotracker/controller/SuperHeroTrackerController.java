/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherotracker.controller;

import com.sg.superherotracker.model.Sighting;
import com.sg.superherotracker.service.SuperHeroTrackerServiceLayer;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Alex
 */
@Controller
public class SuperHeroTrackerController {
    
    SuperHeroTrackerServiceLayer service;
    
    @Inject
    public SuperHeroTrackerController(SuperHeroTrackerServiceLayer service) {
        this.service = service;
    }
    
    @GetMapping("/")
    public String displayHomePage(Model model) {
        List<Sighting> sightings = service.getAllSightings();
        model.addAttribute("sightingList", sightings);
        return "home";
    }
    
    @GetMapping("/displaySupers")
    public String displayHeroVillainPage(Model model) {
        //List<Super> supers = service.getAllSupers();
        //model.addAttribute("supers", supers);
        return "supers";
    }
    
    @GetMapping("/displayOrganizations")
    public String displayOrganizationsPage(Model model) {
        
        
        return "organizations";
    }
    
    @GetMapping("/displaySightingLocations")
    public String displaySightingLocationsPage(Model model) {
        
        
        return "sightingLocations";
    }
}
