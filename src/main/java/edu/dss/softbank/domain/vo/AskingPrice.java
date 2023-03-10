
package edu.dss.softbank.domain.vo;

import org.apache.commons.lang3.Validate;

/**
 *
 * @author stout
 */
public final class AskingPrice {
    private double askingPrice;
    
    public AskingPrice(double v) {
        Validate.inclusiveBetween(0, 1e6, v, "The value must be between 0 and 1 million");
        askingPrice = v;
    }

    @Override
    public String toString() {
        return "AskingPrice [askingPrice=" + askingPrice + "]";
    }

}
