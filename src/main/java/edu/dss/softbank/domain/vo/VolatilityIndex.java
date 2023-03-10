/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.dss.softbank.domain.vo;

import org.apache.commons.lang3.Validate;

/**
 *
 * @author stout
 */
public final class VolatilityIndex {
    private final double volatilityIndex;
    
    public VolatilityIndex() {
        this(0.0);
    }

    public VolatilityIndex(double v) {
        Validate.inclusiveBetween(0, 1, v, "The value must be between 0 and 1");
        volatilityIndex = v;
    }

    @Override
    public String toString() {
        return "VolatilityIndex [volatilityIndex=" + volatilityIndex + "]";
    }

}
