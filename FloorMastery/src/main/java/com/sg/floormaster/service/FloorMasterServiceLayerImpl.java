/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.service;

import com.sg.floormaster.dao.FloorMasterAuditDao;
import com.sg.floormaster.dao.FloorMasterMaterialDao;
import com.sg.floormaster.dao.FloorMasterOrderDao;
import com.sg.floormaster.dao.FloorMasterTaxDao;

/**
 *
 * @author Alex
 */
public class FloorMasterServiceLayerImpl implements FloorMasterServiceLayer {
    
    FloorMasterOrderDao orderDao;
    FloorMasterMaterialDao materialDao;
    FloorMasterTaxDao taxDao;
    FloorMasterAuditDao auditDao;

    public FloorMasterServiceLayerImpl(FloorMasterOrderDao orderDao, FloorMasterMaterialDao materialDao, FloorMasterTaxDao taxDao, FloorMasterAuditDao auditDao) {
        this.orderDao = orderDao;
        this.materialDao = materialDao;
        this.taxDao = taxDao;
        this.auditDao = auditDao;
    }
    
}
