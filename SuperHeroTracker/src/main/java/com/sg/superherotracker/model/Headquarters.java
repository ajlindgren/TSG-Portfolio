/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherotracker.model;

import java.util.Objects;

/**
 *
 * @author Alex
 */
public class Headquarters {
    
    private int headquartersId;
    private String address;
    private String planet;

    public int getHeadquartersId() {
        return headquartersId;
    }

    public void setHeadquartersId(int headquartersId) {
        this.headquartersId = headquartersId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.headquartersId;
        hash = 29 * hash + Objects.hashCode(this.address);
        hash = 29 * hash + Objects.hashCode(this.planet);
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
        final Headquarters other = (Headquarters) obj;
        if (this.headquartersId != other.headquartersId) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.planet, other.planet)) {
            return false;
        }
        return true;
    }
    
    
}
