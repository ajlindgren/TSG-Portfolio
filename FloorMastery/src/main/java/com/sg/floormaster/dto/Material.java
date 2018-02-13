/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Alex
 */
public class Material {
    
    private String materialType;
    private BigDecimal costMaterialSquareFoot;
    private BigDecimal costLaborSquareFoot;
    
    public Material() {
        
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public BigDecimal getCostMaterialSquareFoot() {
        return costMaterialSquareFoot;
    }

    public void setCostMaterialSquareFoot(BigDecimal costMaterialSquareFoot) {
        this.costMaterialSquareFoot = costMaterialSquareFoot;
    }
    
    public BigDecimal getCostLaborSquareFoot() {
        return costLaborSquareFoot;
    }
    
    public void setCostLaborSquareFoot(BigDecimal costLaborSquareFoot) {
        this.costLaborSquareFoot = costLaborSquareFoot;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.materialType);
        hash = 97 * hash + Objects.hashCode(this.costMaterialSquareFoot);
        hash = 97 * hash + Objects.hashCode(this.costLaborSquareFoot);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Material other = (Material) obj;
        if (!Objects.equals(this.materialType, other.materialType)) {
            return false;
        }
        if (!Objects.equals(this.costMaterialSquareFoot, other.costMaterialSquareFoot)) {
            return false;
        }
        if (!Objects.equals(this.costLaborSquareFoot, other.costLaborSquareFoot)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Type: " + materialType + " - Cost Per Square Foot (Material): " + costMaterialSquareFoot + " - Cost Per Square Foot (Labor): " + costLaborSquareFoot;
    }
    
}
