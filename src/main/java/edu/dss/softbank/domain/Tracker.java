
package edu.dss.softbank.domain;

import org.apache.commons.lang3.Validate;

import edu.dss.softbank.domain.vo.DateTime;

/**
 *
 * @author stout
 */
public final class Tracker {
    private final Client client;
    private final StockOption stockOption;
    private final DateTime dateStart;
    
    public Tracker(Client c, StockOption s) {
        checkInvariants(c, s);
        client = c;
        stockOption = s;
        dateStart = new DateTime(); // will use now() for this Tracker
    }
    
    private void checkInvariants(Client c, StockOption s) {
        Validate.notNull(c,"Client cannot be null");
        Validate.notNull(s,"Stock Option cannot be null");
    }
}
