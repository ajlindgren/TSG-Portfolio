/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.notestracker.dao;

import com.sg.notestracker.model.Notebook;
import com.sg.notestracker.model.Page;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Alex
 */
@Component
public class NotesDaoDbImpl implements NotesDao {

    private static final String SQL_INSERT_NOTEBOOK = "insert into notebook (notebookId, "
            + "subject, color) VALUES (?,?,?)";
    private static final String SQL_SELECT_NOTEBOOK = "select * from notebook where notebookId = ?";
    private static final String SQL_SELECT_ALL_NOTEBOOKS = "select * from notebook";
    private static final String SQL_UPDATE_NOTEBOOK = "update notebook set subject = ?, color = ? "
            + "where notebookId = ?";
    private static final String SQL_DELETE_NOTEBOOK = "delete from notebook where notebookId = ?";
    
    private static final String SQL_INSERT_PAGE = "insert into page (date, content, notebookId) "
            + "VALUES (?,?,?)";
    private static final String SQL_SELECT_PAGE = "select * from page where pageId = ?";
    private static final String SQL_SELECT_ALL_PAGES = "select * from page";
    private static final String SQL_UPDATE_PAGE = "update page set date = ?, content = ?, notebookId = ? "
            + "where pageId = ?";
    private static final String SQL_DELETE_PAGE = "delete from page where pageId = ?";
    
    private static final String SQL_SELECT_PAGES_BY_NOTEBOOK_ID = "select * from pages where notebookId = ?";
    
    @Inject
    private JdbcTemplate dbTemp;
    
    @Override
    @Transactional
    public void addNotebook(Notebook nb) {
        dbTemp.update(SQL_INSERT_NOTEBOOK,
                nb.getSubject(),
                nb.getColor());
        int notebookId = dbTemp.queryForObject("select LAST_INSERT_ID()", Integer.class);
        
        nb.setNotebookId(notebookId);
    }

    @Override
    public Notebook getNotebookById(int notebookId) {
        try {
            return dbTemp.queryForObject(SQL_SELECT_NOTEBOOK, new NotebookMapper(), notebookId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Notebook> getAllNotebooks() {
        List<Notebook> notebookList = dbTemp.query(SQL_SELECT_ALL_NOTEBOOKS, new NotebookMapper());
        return associatePagesWithNotebooks(notebookList);
    }

//    @Override
//    public List<Page> getPagesByNotebookId(int notebookId) {
//        List<Page> pageList = dbTemp.query(SQL_SELECT_PAGES_BY_NOTEBOOK_ID, new PageMapper(), notebookId);
//        return associatePagesWithNotebooks(pageList);
//    }

    @Override
    public void updateNotebook(Notebook nb) {
        dbTemp.update(SQL_UPDATE_NOTEBOOK,
                nb.getSubject(),
                nb.getColor(),
                nb.getNotebookId());
    }

    @Override
    public void deleteNotebook(int notebookId) {
        dbTemp.update(SQL_DELETE_NOTEBOOK, notebookId);
    }

    @Override
    @Transactional
    public void addPage(Page page) {
        dbTemp.update(SQL_INSERT_PAGE,
                page.getDate(),
                page.getContent(),
                page.getNotebookId());
    }

    @Override
    public Page getPageById(int pageId) {
        try {
            return dbTemp.queryForObject(SQL_SELECT_PAGE, new PageMapper(), pageId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Page> getAllPages() {
        return dbTemp.query(SQL_SELECT_ALL_PAGES, new PageMapper());
    }

    @Override
    public void updatePage(Page page) {
        dbTemp.update(SQL_UPDATE_PAGE,
                page.getDate(),
                page.getContent(),
                page.getNotebookId(),
                page.getPageId());
    }

    @Override
    public void deletePage(int pageId) {
        dbTemp.update(SQL_DELETE_PAGE, pageId);
    }
    
    private List<Page> findPagesForNotebook(Notebook notebook) {
        return dbTemp.query(SQL_SELECT_PAGES_BY_NOTEBOOK_ID, new PageMapper(), notebook.getNotebookId());
    }
    
    private List<Notebook> associatePagesWithNotebooks(List<Notebook> notebookList) {
        for (Notebook currentNb : notebookList) {
            currentNb.setPages(findPagesForNotebook(currentNb));
        }
        return notebookList;      
    }
    
    private static final class NotebookMapper implements RowMapper<Notebook> {

        @Override
        public Notebook mapRow(ResultSet rs, int i) throws SQLException {
            Notebook nb = new Notebook();
            nb.setNotebookId(rs.getInt("notebookId"));
            nb.setSubject(rs.getString("subject"));
            nb.setColor(rs.getString("color"));
            return nb;
        }
        
    }
    
    private static final class PageMapper implements RowMapper<Page> {

        @Override
        public Page mapRow(ResultSet rs, int i) throws SQLException {
            Page p = new Page();
            p.setPageId(rs.getInt("pageId"));
            p.setDate(rs.getTimestamp("date").toLocalDateTime().toLocalDate());
            p.setContent(rs.getString("content"));
            p.setNotebookId(rs.getInt("notebookId"));
            return p;
        }
        
    }
    
}
