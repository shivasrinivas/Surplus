package edu.njit.surplus.utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by srini on 11/12/17.
 */

public class Utilities {

    public static Date getDate(String dateStr) {
        try {
            DateFormat format = new SimpleDateFormat("YYYY-mm-dd HH:MM:SS", Locale.ENGLISH);
            return format.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getDateDifference(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        int diff = 0;
        try {
            Calendar cal = Calendar.getInstance();
            int today = cal.get(Calendar.DAY_OF_MONTH);
            cal.setTime(date);
            diff = cal.get(Calendar.DAY_OF_MONTH) - today;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return diff;
    }
}
