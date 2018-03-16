/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.notestracker;

import com.sg.notestracker.dao.NotesDao;
import com.sg.notestracker.model.Notebook;
import com.sg.notestracker.model.Page;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Alex
 */
@Controller
public class NotesController {
 
    @Inject
    NotesDao dao;
    
    @GetMapping("/")
    public String getMainPage(Model model) {
        model.addAttribute("notebookList", dao.getAllNotebooks());
        model.addAttribute("pageList", dao.getAllPages());
        return "index";
    }
    
    @PostMapping("/createNotebook")
    public String createNotebook(Notebook notebook) {
        dao.addNotebook(notebook);
        return "redirect:/";
    }
    
    @PostMapping("/deleteNotebook")
    public String deleteNotebook(Integer id) {
        dao.deleteNotebook(id);
        return "redirect:/";
    }
    
    @PostMapping("/editNotebook")
    public String editNotebook(Integer id, Model model) {
        Notebook notebook = dao.getNotebookById(id);
        model.addAttribute("notebook", notebook);
        return "editNotebook";
    }
    
    @PostMapping("/createPage")
    public String createPage(Page page) {
        dao.addPage(page);
        return "redirect:/";
    }
    
    @PostMapping("/deletePage")
    public String deletePage(Integer id) {
        dao.deletePage(id);
        return "redirect:/";
    }
    
    @PostMapping("/editPage")
    public String editPage(Page page) {
        dao.updatePage(page);
        return "redirect:/";
    }
     
}
