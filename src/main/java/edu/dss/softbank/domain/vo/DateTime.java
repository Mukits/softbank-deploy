
package edu.dss.softbank.domain.vo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.Validate;

public final class DateTime {
    private final LocalDateTime dateTime;
    
    public DateTime() {
        dateTime = LocalDateTime.now();
    }

    public DateTime(int plusDays) {
        dateTime = LocalDateTime.now();
        dateTime.plusDays(plusDays);
    }

    public DateTime(int y, int m, int d, int h, int min) {
        int year = (LocalDateTime.now()).getYear(); // get current year
        int ymin = year-Properties.YEAR_BOUNDARY;   // check boundaries
        int ymax = year+Properties.YEAR_BOUNDARY;
        Validate.isTrue(y >= ymin && y <= ymax, "The value must be between %d and %d", ymin, ymax);
        Validate.isTrue(m >= 1 && m <= 12, "The value must be between %d and %d", 1, 12);
        Validate.isTrue(h >= 0 && h <= 23, "The value must be between %d and %d", 0, 23);
        Validate.isTrue(min >= 0 && min <= 59, "The value must be between %d and %d", 0, 59);
        dateTime = LocalDateTime.of(y, m, d, h, min);
        Validate.notNull(dateTime);
    }
    
    public String getDateTime() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
        return dateTime.format(format);
    }

    @Override
    public String toString() {
        return "DateTime{" + "dateTime=" + getDateTime() + '}';
    }
    
    
}
