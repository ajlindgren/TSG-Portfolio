/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.advice;

import com.sg.floormaster.dao.FloorMasterAuditDao;

/**
 *
 * @author Alex
 */
public class LoggingAdvice {
    
    FloorMasterAuditDao auditDao;
    
    public LoggingAdvice(FloorMasterAuditDao auditDao) {
        this.auditDao = auditDao;
    }
    
}
