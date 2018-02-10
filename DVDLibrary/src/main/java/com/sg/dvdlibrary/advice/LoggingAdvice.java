/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.advice;

import com.sg.dvdlibrary.dao.DVDLibraryAuditDao;
import com.sg.dvdlibrary.dao.DVDLibraryDaoException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author Alex
 */
public class LoggingAdvice {
    
    DVDLibraryAuditDao auditDao;
    
    public LoggingAdvice(DVDLibraryAuditDao auditDao) {
        this.auditDao = auditDao;
    }
    
    public void createAuditEntry(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": ";
        auditEntry += args[0];
        
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (DVDLibraryDaoException e) {
            System.err.println("ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
    
}
