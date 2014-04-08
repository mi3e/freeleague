package ie.logn.utils.datetime;

import static org.junit.Assert.*;
import ie.logn.utils.datetime.DateTimeUtils;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
import org.junit.Test;

public class DateTimeUtilsTest {

    @Test
    public void testCompareDatesWeekends() {
        // Month "0" means January...
        Calendar firstCal = new GregorianCalendar(2012, 0, 27, 0, 0);

        Calendar secondCal = new GregorianCalendar(2012, 0, 30, 0, 0);

        Date firstDate = firstCal.getTime();

        Date secondtDate = secondCal.getTime();

        // Not considering business holidays
        int dates = DateTimeUtils.calculateBusinessDays(firstDate, secondtDate);

        assertTrue(dates == 1);
    }

    @Test
    public void testCompareDatesWeekDays() {
        Calendar firstCal = new GregorianCalendar(2012, 0, 24, 0, 0);

        Calendar secondCal = new GregorianCalendar(2012, 0, 27, 0, 0);

        Date firstDate = firstCal.getTime();

        Date secondtDate = secondCal.getTime();

        // Not considering business holidays
        int dates = DateTimeUtils.calculateBusinessDays(firstDate, secondtDate);

        assertTrue(dates == 3);
    }

    @Test
    public void testCompareDatesWrongOrderInput() {
        Calendar firstCal = new GregorianCalendar(2012, 0, 30, 0, 0);

        Calendar secondCal = new GregorianCalendar(2012, 0, 27, 0, 0);

        Date firstDate = firstCal.getTime();

        Date secondtDate = secondCal.getTime();

        // Not considering business holidays
        int dates = DateTimeUtils.calculateBusinessDays(firstDate, secondtDate);

        assertTrue(dates == 0);
    }

    @Test
    public void testGetDateBeforeAfterNegative() {
        Calendar firstCal = new GregorianCalendar(2012, 4, 28);

        Date date = DateTimeUtils.getBusinessDaysBeforeAfter(firstCal.getTime(), -1);

        assertEquals(new GregorianCalendar(2012, 4, 25).getTime(), date);
    }

    @Test
    public void testGetDateBeforeAfterPositive() {
        Calendar firstCal = new GregorianCalendar(2012, 4, 25);

        Date date = DateTimeUtils.getBusinessDaysBeforeAfter(firstCal.getTime(), 1);

        assertEquals(new GregorianCalendar(2012, 4, 28).getTime(), date);
    }

    @Test
    public void testGetDateBeforeAfter() {
        Calendar firstCal = new GregorianCalendar(2012, 4, 25);

        Date date = DateTimeUtils.getBusinessDaysBeforeAfter(firstCal.getTime(), 7);

        assertEquals(new GregorianCalendar(2012, 5, 5).getTime(), date);
    }

}
