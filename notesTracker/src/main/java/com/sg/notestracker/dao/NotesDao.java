/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.notestracker.dao;

import com.sg.notestracker.model.Notebook;
import com.sg.notestracker.model.Page;
import java.util.List;

/**
 *
 * @author Alex
 */
public interface NotesDao {
    
    public void addNotebook(Notebook notebook);
    
    public Notebook getNotebookById(int notebookId);
    
    public List<Notebook> getAllNotebooks();
    
    public void updateNotebook(Notebook notebook);
    
    public void deleteNotebook(int notebookId);
    
    public void addPage(Page page);
    
    public Page getPageById(int pageId);
    
    public List<Page> getAllPages();
    
//    public List<Page> getPagesByNotebookId(int notebookId);
    
    public void updatePage(Page page);
    
    public void deletePage(int pageId);
    
}
