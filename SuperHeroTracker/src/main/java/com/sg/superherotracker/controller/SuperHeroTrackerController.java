/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherotracker.controller;

import com.sg.superherotracker.dao.SuperHeroTrackerDeleteDependencyException;
import com.sg.superherotracker.model.Headquarters;
import com.sg.superherotracker.model.Location;
import com.sg.superherotracker.model.Organization;
import com.sg.superherotracker.model.Power;
import com.sg.superherotracker.model.Sighting;
import com.sg.superherotracker.model.Super;
import com.sg.superherotracker.service.SuperHeroTrackerServiceLayer;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Alex
 */
@Controller
public class SuperHeroTrackerController {

    public static final String pictureFolder = "images/";

    SuperHeroTrackerServiceLayer service;

    @Inject
    public SuperHeroTrackerController(SuperHeroTrackerServiceLayer service) {
        this.service = service;
    }

    //Home page and Navigation Bar methods
    @GetMapping("/")
    public String displayHomePage(Model model) {
        List<Sighting> sightings = service.get10RecentSightings();
        model.addAttribute("sightingList", sightings);
        return "home";
    }

    @GetMapping("/displaySupers")
    public String displaySupersPage(Model model) {
        List<Organization> orgs = service.getAllOrganizations();
        List<Sighting> sightings = service.getAllSightings();
        List<Super> supers = service.getAllSupers();
        model.addAttribute("supers", supers);
        model.addAttribute("sightings", sightings);
        model.addAttribute("orgs", orgs);
        return "supers";
    }

    @GetMapping("/displayOrganizations")
    public String displayOrganizationsPage(Model model) {
        List<Organization> heroic = service.getOrganizationsByAlignment(true);
        List<Organization> villainous = service.getOrganizationsByAlignment(false);
        Organization org = service.getOrganizationById(1);
        model.addAttribute("heroicOrgs", heroic);
        model.addAttribute("villainousOrgs", villainous);
        model.addAttribute("org", org);
        return "organizations";
    }

    @GetMapping("/displaySightingLocations")
    public String displaySightingLocationsPage(Model model) {
        List<Sighting> sightings = service.getAllSightings();
        List<Location> locations = service.getAllLocations();
        List<Super> supers = service.getAllSupers();
        Location defaultLocation = service.getLocationById(1);
        List<Sighting> sightByLocId = service.getSightingsByLocationId(1);
        model.addAttribute("sightings", sightings);
        model.addAttribute("locations", locations);
        model.addAttribute("supers", supers);
        model.addAttribute("config", 0);
        model.addAttribute("defaultLocation", defaultLocation);
        model.addAttribute("sightByLocId", sightByLocId);

        return "sightingLocations";
    }

    @GetMapping("/displayManager")
    public String displayManagerPage(Model model) {
        List<Sighting> sightings = service.getAllSightings();
        List<Location> locations = service.getAllLocations();
        List<Super> supers = service.getAllSupers();
        List<Power> powers = service.getAllPowers();
        List<Organization> orgs = service.getAllOrganizations();
        List<Headquarters> hqs = service.getAllHeadquarters();

        model.addAttribute("sightings", sightings);
        model.addAttribute("locations", locations);
        model.addAttribute("supers", supers);
        model.addAttribute("powers", powers);
        model.addAttribute("orgs", orgs);
        model.addAttribute("hqs", hqs);

        return "manager";
    }

    //Supers page methods
    @GetMapping("/editSuper/{superId}")
    public String displayEditSuperForm(@PathVariable("superId") Integer superId, Model model) {
        Super upSuper = service.getSuperById(superId);
        model.addAttribute("powerList", service.getAllPowers());
        model.addAttribute("upSuper", upSuper);
        return "editSuper";
    }

    @PostMapping("/editSuper/{superId}")
    public String editSuper(@Valid @ModelAttribute("upSuper") Super upSuper,
            BindingResult result, HttpServletRequest request, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("powerList", service.getAllPowers());
            return "editSuper";
        }

        Power pow = service.getPowerById(Integer.parseInt(request.getParameter("powerId")));
        upSuper.setPower(pow);
        service.updateSuper(upSuper);

        return "redirect:/displaySupers";
    }

    @PostMapping("/addIcon/{superId}")
    public String addPicture(HttpServletRequest request,
            Model model, @PathVariable("superId") Integer superId,
            @RequestParam("picture") MultipartFile pictureFile) {

        // only save the pictureFile if the user actually uploaded something
        if (!pictureFile.isEmpty()) {
            try {
                // we want to put the uploaded image into the 
                // <pictureFolder> folder of our application. getRealPath
                // returns the full path to the directory under Tomcat
                // where we can save files.
                String savePath = request
                        .getSession()
                        .getServletContext()
                        .getRealPath("/") + pictureFolder;
                File dir = new File(savePath);
                // if <pictureFolder> directory is not there, 
                // go ahead and create it
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                // get the filename of the uploaded file - we'll use the
                // same name on the server.
                String filename = pictureFile.getOriginalFilename();
                // transfer the contents of the uploaded pictureFile to 
                // the server
                pictureFile.transferTo(new File(savePath + filename));

                // we successfully saved the pictureFile, now save a 
                // Picture to the DAO
                Super currentSuper = service.getSuperById(superId);
                currentSuper.setIconFile(pictureFolder + filename);
                service.updateSuper(currentSuper);

                // redirect to home page to redisplay the entire album
                return "redirect:/displaySupers";
            } catch (Exception e) {
                // if we encounter an exception, add the error message 
                // to the model and return back to the pictureFile upload 
                // form page
                model.addAttribute("errorMsg", "File upload failed: "
                        + e.getMessage());
                return "supers";
            }
        } else {
            // if the user didn't upload anything, add the error 
            // message to the model and return back to the pictureFile 
            // upload form page
            model.addAttribute("errorMsg",
                    "Please specify a non-empty file.");
            return "supers";
        }

    }

    //Organizations page methods
    @GetMapping("/displayOrganizations/{orgId}")
    public String displayClickedOrganization(@PathVariable("orgId") Integer orgId, Model model) {
        List<Organization> heroic = service.getOrganizationsByAlignment(true);
        List<Organization> villainous = service.getOrganizationsByAlignment(false);
        Organization org = service.getOrganizationById(orgId);
        model.addAttribute("heroicOrgs", heroic);
        model.addAttribute("villainousOrgs", villainous);
        model.addAttribute("org", org);
        return "organizations";
    }
    
    @GetMapping("/editOrganization/{orgId}")
    public String displayEditOrganizationForm(@PathVariable("orgId") Integer orgId, Model model) {
        Organization org = service.getOrganizationById(orgId);
        model.addAttribute("hqs", service.getAllHeadquarters());
        model.addAttribute("supers", service.getAllSupers());
        model.addAttribute("org", org);
        return "editOrganization";
    }

    @PostMapping("editOrganization/{orgId}")
    public String editOrganization(@Valid @ModelAttribute("org") Organization org,
            BindingResult result, HttpServletRequest request, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("org", org);
            model.addAttribute("supers", service.getAllSupers());
            model.addAttribute("hqs", service.getAllHeadquarters());
            return "editOrganization";
        }
        
        if (org.getAlignment() == null) {
            org.setAlignment(false);
        }

        Headquarters hq = service.getHeadquartersById(Integer.parseInt(request.getParameter("hqId")));
        org.setHeadquarters(hq);
        
        List<Integer> superIds = new ArrayList<>();
        for (String currentId : request.getParameterValues("superIds")) {
            superIds.add(Integer.parseInt(currentId));
        }
        
        List<Super> supers = new ArrayList<>();
        for (int id : superIds) {
            supers.add(service.getSuperById(id));
        }
        
        org.setSupers(supers);
        
        service.updateOrganization(org);

        return "redirect:/displayOrganizations";
    }

    //SightingLocations page methods
    @GetMapping("/displaySightingLocations/locations/{locId}")
    public String displayClickedLocation(@PathVariable("locId") Integer locId, Model model) {
        List<Sighting> sightings = service.getAllSightings();
        List<Location> locations = service.getAllLocations();
        List<Super> supers = service.getAllSupers();
        List<Sighting> sightByLocId = service.getSightingsByLocationId(locId);
        model.addAttribute("sightings", sightings);
        model.addAttribute("locations", locations);
        model.addAttribute("supers", supers);
        model.addAttribute("sightByLocId", sightByLocId);
        model.addAttribute("clickedLocation", service.getLocationById(locId));
        model.addAttribute("config", 1);
        return "sightingLocations";
    }

    @GetMapping("/displaySightingLocations/sightings/{superId}")
    public String displayClickedSighting(@PathVariable("superId") Integer superId, Model model) {
        List<Sighting> sightings = service.getAllSightings();
        List<Location> locations = service.getAllLocations();
        List<Super> supers = service.getAllSupers();
        List<Sighting> sightingsForSuper = service.getSightingsBySuperId(superId);
        Super clickedSuper = service.getSuperById(superId);
        model.addAttribute("sightings", sightings);
        model.addAttribute("locations", locations);
        model.addAttribute("supers", supers);
        model.addAttribute("clickedSuper", clickedSuper);
        model.addAttribute("sightingsForSuper", sightingsForSuper);
        model.addAttribute("config", 2);
        return "sightingLocations";
    }
    
    @GetMapping("/editSighting/{sightingId}")
    public String displayEditSightingForm(@PathVariable("sightingId") Integer sightingId, Model model) {
        Sighting sighting = service.getSightingById(sightingId);
        model.addAttribute("locations", service.getAllLocations());
        model.addAttribute("supers", service.getAllSupers());
        model.addAttribute("sighting", sighting);
        return "editSighting";
    }

    @PostMapping("/editSighting/edit/{sightingId}")
    public String editSighting(@Valid @ModelAttribute("sighting") Sighting sighting,
            BindingResult result, Model model, HttpServletRequest request) {

//        if (result.hasErrors()) {
//            model.addAttribute("sighting", sighting);
//            model.addAttribute("supers", service.getAllSupers());
//            model.addAttribute("locations", service.getAllLocations());
//            return "editSighting";
//        }
        
        LocalDateTime ldt = LocalDateTime.parse(request.getParameter("dateTime"));
        sighting.setDateTime(ldt);

        Location location = service.getLocationById(Integer.parseInt(request.getParameter("locationId")));
        sighting.setLocation(location);
        
        List<Integer> superIds = new ArrayList<>();
        for (String currentId : request.getParameterValues("superIds")) {
            superIds.add(Integer.parseInt(currentId));
        }
        
        List<Super> supers = new ArrayList<>();
        for (int id : superIds) {
            supers.add(service.getSuperById(id));
        }
        
        sighting.setSupers(supers);
        
        service.updateSighting(sighting);

        return "redirect:/displaySightingLocations";
    }

    //Manager page methods
    @PostMapping("/addPower")
    public String addPower(HttpServletRequest request, Model model) {
        Power pow = new Power();
        pow.setDescription(request.getParameter("powerDescription"));
        service.addPower(pow);

        return "redirect:/displayManager";
    }

    @PostMapping("/deletePower")
    public String deletePower(HttpServletRequest request, Model model) throws SuperHeroTrackerDeleteDependencyException {
        service.deletePower(Integer.parseInt(request.getParameter("powerId")));

        return "redirect:/displayManager";
    }

    @PostMapping("/addSuper")
    public String addSuper(@Valid @ModelAttribute("upSuper") Super upSuper,
            HttpServletRequest request, Model model) {
        
//        Super sup = new Super();
//        sup.setName(request.getParameter("name"));
//        sup.setDescription(request.getParameter("description"));

        int powerId = Integer.parseInt(request.getParameter("powerId"));
        upSuper.setPower(service.getPowerById(powerId));
        service.addSuper(upSuper);

        return "redirect:/displayManager";
    }

    @PostMapping("/deleteSuper")
    public String deleteSuper(HttpServletRequest request, Model model) {
        service.deleteSuper(Integer.parseInt(request.getParameter("superId")));

        return "redirect:/displayManager";
    }

    @PostMapping("/addHq")
    public String addHeadquarters(HttpServletRequest request, Model model) {
        Headquarters hq = new Headquarters();
        hq.setAddress(request.getParameter("hqAddress"));
        hq.setPlanet(request.getParameter("hqPlanet"));
        service.addHeadquarters(hq);

        return "redirect:/displayManager";
    }

    @PostMapping("/deleteHq")
    public String deleteHeadquarters(HttpServletRequest request, Model model) throws SuperHeroTrackerDeleteDependencyException {
        service.deleteHeadquarters(Integer.parseInt(request.getParameter("hqId")));

        return "redirect:/displayManager";
    }

    @PostMapping("/addOrg")
    public String addOrganization(HttpServletRequest request, Model model) {
        Organization org = new Organization();
        org.setName(request.getParameter("orgName"));
        org.setDescription(request.getParameter("orgDescription"));
        org.setEmail(request.getParameter("orgEmail"));

        int hqId = Integer.parseInt(request.getParameter("hqId"));
        org.setHeadquarters(service.getHeadquartersById(hqId));

        List<Integer> superIds = new ArrayList<>();
        for (String currentId : request.getParameterValues("superIds")) {
            superIds.add(Integer.parseInt(currentId));
        }

        List<Super> supers = new ArrayList<>();
        for (int id : superIds) {
            supers.add(service.getSuperById(id));
        }

        if (request.getParameter("alignment").equals("heroic")) {
            org.setAlignment(true);
        } else {
            org.setAlignment(false);
        }

        org.setSupers(supers);
        service.addOrganization(org);

        return "redirect:/displayManager";
    }

    @PostMapping("/deleteOrg")
    public String deleteOrganization(HttpServletRequest request, Model model) {
        service.deleteOrganization(Integer.parseInt(request.getParameter("orgId")));

        return "redirect:/displayManager";
    }

    @PostMapping("/addLocation")
    public String addLocation(HttpServletRequest request, Model model) {
        Location loc = new Location();
        loc.setName(request.getParameter("locName"));
        loc.setDescription(request.getParameter("locDescription"));
        loc.setAddress(request.getParameter("locAddress"));
        loc.setLatitude(new BigDecimal(request.getParameter("locLatitude")));
        loc.setLongitude(new BigDecimal(request.getParameter("locLongitude")));
        service.addLocation(loc);

        return "redirect:/displayManager";
    }

    @PostMapping("/deleteLocation")
    public String deleteLocation(HttpServletRequest request, Model model) throws SuperHeroTrackerDeleteDependencyException {
        service.deleteLocation(Integer.parseInt(request.getParameter("locationId")));

        return "redirect:/displayManager";
    }

    @PostMapping("/addSighting")
    public String addSighting(HttpServletRequest request, Model model) {
        Sighting sighting = new Sighting();

//        LocalDate date = LocalDate.parse(request.getParameter("sightingDate"));
//        LocalTime time = LocalTime.parse(request.getParameter("sightingTime"));
//        sighting.setDateTime(LocalDateTime.of(date, time));

        sighting.setDateTime(LocalDateTime.parse(request.getParameter("dateTime")));

        int locId = Integer.parseInt(request.getParameter("locId"));
        sighting.setLocation(service.getLocationById(locId));

        List<Integer> superIds = new ArrayList<>();
        for (String currentId : request.getParameterValues("superIds")) {
            superIds.add(Integer.parseInt(currentId));
        }

        List<Super> supers = new ArrayList<>();
        for (int id : superIds) {
            supers.add(service.getSuperById(id));
        }

        sighting.setSupers(supers);
        service.addSighting(sighting);

        return "redirect:/displayManager";
    }

    @PostMapping("/deleteSighting")
    public String deleteSighting(HttpServletRequest request, Model model) {
        service.deleteSighting(Integer.parseInt(request.getParameter("sightingId")));

        return "redirect:/displayManager";
    }
}
