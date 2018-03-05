/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.consolevender.advice;

import com.sg.vendingmachinespringmvc.dao.ConsoleVenderAuditDao;
import com.sg.vendingmachinespringmvc.dao.ConsoleVenderPersistenceException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author Alex
 */
public class LoggingAdvice {
    
    ConsoleVenderAuditDao auditDao;
    
    public LoggingAdvice(ConsoleVenderAuditDao auditDao) {
        this.auditDao = auditDao;
    }
    
    public void createAuditEntryException(JoinPoint jp, Exception ex) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": ";
        auditEntry += args[0] + " caused " + ex.getClass().getSimpleName();
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (ConsoleVenderPersistenceException e) {
            System.err.println("ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
    
    public void createAuditEntry(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": ";
        auditEntry += args[0];
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (ConsoleVenderPersistenceException e) {
            System.err.println("ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
    
}
