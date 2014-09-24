package org.september.util;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String getMonthName(Date date) {
        return new SimpleDateFormat("MMMM").format(date);
    }

    public static String getMonthName(int month) {
        return new DateFormatSymbols().getMonths()[month];
    }
}
