
package edu.dss.softbank.domain.vo;

import javax.persistence.Column;

import org.apache.commons.lang3.Validate;

public final class Balance {
    @Column(length=63)
    private final Double balance;
    
    public Balance() {
        this(0.0);
    }

    public Balance(String b) {
        this(Double.parseDouble(b));
    }

    public Balance(Double b) {
        Validate.notNull(b);
        Validate.isTrue(b.doubleValue() >= 0 && b.doubleValue() <= Double.MAX_VALUE, "The value must be between %d and %f", 0, Double.MAX_VALUE);
        balance = b;
    }

    public Double getBalance() {
        return balance;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Balance that = (Balance) o;
        return balance.equals(that.balance);
    }

    @Override
    public int hashCode() {
        return balance.hashCode();
    }

    @Override
    public String toString() {
        return balance.toString();
    }
}
