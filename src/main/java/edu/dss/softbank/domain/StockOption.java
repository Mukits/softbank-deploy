
package edu.dss.softbank.domain;

import edu.dss.softbank.domain.enumerations.VolatilityType;
import edu.dss.softbank.domain.vo.AskingPrice;
import edu.dss.softbank.domain.vo.Duration;
import edu.dss.softbank.domain.vo.Interest;
import edu.dss.softbank.domain.vo.VolatilityIndex;

/**
 *
 * @author stout
 */
public final class StockOption {
    private Long id;
    private final VolatilityType vType;
    private final Duration duration;
    private final AskingPrice askingPrice;
    private final Interest interest;
    private final boolean available;
    private final VolatilityIndex volatilityIndex;
    
    public StockOption(int durationDays, double price) {
        duration = new Duration(durationDays);
        askingPrice = new AskingPrice(price);
        interest = new Interest();
        volatilityIndex = new VolatilityIndex();
        vType = VolatilityType.stable;
        available = true;
    }

    @Override
    public String toString() {
        return "StockOption [id=" + id + ", vType=" + vType + ", duration=" + duration + ", askingPrice=" + askingPrice
                + ", interest=" + interest + ", available=" + available + ", volatilityIndex=" + volatilityIndex + "]";
    }
    
}
