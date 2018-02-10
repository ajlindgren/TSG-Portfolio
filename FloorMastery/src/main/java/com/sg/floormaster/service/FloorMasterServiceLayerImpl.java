/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.service;

import com.sg.floormaster.dao.FloorMasterAuditDao;
import com.sg.floormaster.dao.FloorMasterDao;

/**
 *
 * @author Alex
 */
public class FloorMasterServiceLayerImpl implements FloorMasterServiceLayer {
    
    FloorMasterDao dao;
    FloorMasterAuditDao auditDao;

    public FloorMasterServiceLayerImpl(FloorMasterDao dao, FloorMasterAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
}
