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
public class Tile {
    
    private String floorType;
    private double area;
    private BigDecimal price;
    
    public Tile(double area) {
        //in case it's appropriate for the user to compare tile prices or something
    }
    
    public Tile(String floorType, double area) {
        //constructing a Tile in this way will allow us to calculate its price field
    }

    public String getFloorType() {
        return floorType;
    }

    public void setFloorType(String floorType) {
        this.floorType = floorType;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public BigDecimal getPrice() {
        return price;
    }
    
    //price will be read-only, and calculated upon instantiation.
//    public void setPrice(BigDecimal price) {
//        this.price = price;
//    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.floorType);
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.area) ^ (Double.doubleToLongBits(this.area) >>> 32));
        hash = 67 * hash + Objects.hashCode(this.price);
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
        final Tile other = (Tile) obj;
        if (Double.doubleToLongBits(this.area) != Double.doubleToLongBits(other.area)) {
            return false;
        }
        if (!Objects.equals(this.floorType, other.floorType)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return this.area + " sq. ft of " + this.floorType + " flooring";
    }
    
}
