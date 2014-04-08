package ie.logn.utils.format;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * SimpleFormatter
 * 
 * Simple static formatting utilities
 * 
 */
public final class DateFormatter {

    /** Format for detailed Dates. */
    private static final String DETAILED_FORMAT_STRING = "yyyy-MM-dd HH:mm:ss";
    /** Format for default dates. */
    private static final String DEFAULT_FORMAT_STRING = "yyyy-MM-dd";

    /**
     * don't instantiate me
     */
    private DateFormatter() {
        super();
    }

    /**
     * format dates for consumption
     * 
     * @param dateStr
     *            in the format (i.e. yyyy-MM-dd)
     * 
     * @return date
     * 
     * @throws ParseException
     */
    public static Date formatDate(final String dateStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_FORMAT_STRING);
        return format.parse(dateStr);
    }

    /**
     * @param date
     * @return Stringified date
     */
    public static String parseDate(final Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_FORMAT_STRING);
        return format.format(date);
    }

    /**
     * format dates for imt consumption
     * 
     * @param dateStr
     *            in the format (yyyy-MM-dd HH:mm:ss)
     * 
     * @return date
     * 
     * @throws ParseException
     */
    public static Date formatDetailedDate(final String dateStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(DETAILED_FORMAT_STRING);
        return format.parse(dateStr);
    }

    /**
     * format dates for consumption
     * 
     * @param date
     *            in the format (yyyy-MM-dd HH:mm:ss)
     * 
     * @return date as a string representation
     */
    public static String parseDetailedDate(final Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DETAILED_FORMAT_STRING);
        return format.format(date);
    }
}
