/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.dss.softbank.domain.vo;

/**
 *
 * @author stout
 */
public final class Duration {
    private final DateTime duration;
    
    public Duration() {
        duration = new DateTime();
    }

    public Duration(DateTime d) {
        duration = d;
    }

    public Duration(int durationDays) {
        duration = new DateTime(durationDays);
    }

    @Override
    public String toString() {
        return "Duration [duration=" + duration + "]";
    }

}
