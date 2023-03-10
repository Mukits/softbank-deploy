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
public final class Interest {
    private final double interest;
    
    public Interest() {
        this(0.0);
    }
    
    public Interest(double i) {
        Validate.inclusiveBetween(0, 1, i, "The value must be between 0 and 1");
        interest = i;
    }
}
