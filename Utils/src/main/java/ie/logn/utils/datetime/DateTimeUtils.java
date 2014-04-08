package ie.logn.utils.datetime;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/*This class calculates the business days between two dates.
 * only Saturdays and Sundays are taken into account. 
 * First date should be no later than the second date, otherwise a "0" will become output. 
 * The input Calendar's hour, minutes and milliseconds field should be cleared.
 */
public class DateTimeUtils {
    public static int calculateBusinessDays(Date firstDate, Date secondDate) {
        int dates = 0;

        Calendar firstCal = Calendar.getInstance();

        Calendar secondCal = Calendar.getInstance();

        firstCal.setTime(firstDate);

        secondCal.setTime(secondDate);

        while (firstCal.before(secondCal)) {
            // Not Saturday or Sunday
            if (secondCal.get(Calendar.DAY_OF_WEEK) != 7 && secondCal.get(Calendar.DAY_OF_WEEK) != 1)
                dates++;

            secondCal.add(Calendar.DATE, -1);
        }

        return dates;
    }

    public static Date getBusinessDaysBeforeAfter(Date date, int modifier) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        if (modifier < 0) {
            for (int i = 0; i > modifier; --i) {
                calendar.add(Calendar.DAY_OF_WEEK, -1);

                while (isOnWeekend(calendar.getTime())) {
                    calendar.add(Calendar.DAY_OF_WEEK, -1);
                }
            }
        } else if (modifier > 0) {
            for (int i = 0; i < modifier; ++i) {
                calendar.add(Calendar.DAY_OF_WEEK, 1);

                while (isOnWeekend(calendar.getTime())) {
                    calendar.add(Calendar.DAY_OF_WEEK, 1);
                }
            }
        }

        return calendar.getTime();
    }

    public static boolean isOnWeekend(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();

        calendar.setTime(date);

        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
            || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return true;
        }

        return false;
    }

    public static Calendar trimTimeFromCalendar(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

}