
package edu.dss.softbank.domain.enumerations;

/**
 * These modifications to the enum make them more 'pallatable' to user interfaces
 *
 */
public enum AccountType {
    checking("Checking"),
    investment("Investment"),
    vip("VIP"),
    savings("Savings");

    private final String displayValue;

    private AccountType(String displayValue) {
        this.displayValue = displayValue;
    }
    
    public String getDisplayValue() {
        return displayValue;
    }
}
