package ie.logn.utils.format;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

public class NumberFormatter {
    public static BigDecimal nullToZero(BigDecimal value) {
        return value == null ? BigDecimal.ZERO : value;
    }

    /**
     * Checks, if provided number is a valid whole number.
     * 
     * @param textNumber
     *            String representation of a whole number.
     * 
     * @return true, if entered customised number is valid
     */
    public static boolean isWholeNumber(String textNumber) {
        try {
            getLong(textNumber);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Checks, if provided number is valid.
     * 
     * @param textNumber
     *            String representation of a number.
     * 
     * @return true, if entered customised number is valid.
     */
    public static boolean isNumber(String textNumber) {
        try {
            getBigDecimal(textNumber);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * @param num
     *            Number to format
     * @return String representation of num with commas
     */
    public static String formatWithCommas(BigDecimal num) {
        return String.format("%,f", num);
    }

    public static String setDpAndFormatWithCommas(BigDecimal num, int dp) {
        BigDecimal withDpSet = format(num, dp);

        return String.format("%,." + dp + "f", withDpSet);
    }

    /**
     * Converts a string to a number. It will convert a number of different
     * formats for the numbers. E.g.
     * 
     * <ul>
     * <li>12345</li>
     * <li>12,345</li>
     * <li>12.345k</li>
     * <li>12345K</li>
     * <li>12345m</li>
     * <li>12345M</li>
     * <li>1,234.5K</li>
     * <li>12345</li>
     * <li>12345.89</li>
     * </ul>
     * 
     * @param value
     *            String to be converted to a number.
     * 
     * @return The number whole represented by the string.
     * 
     * @throws IllegalArgumentException
     *             Null of blank string passed in.
     * @throws NumberFormatException
     *             Sting isn't a valid whole number.
     */
    public static BigDecimal getBigDecimal(String value) {
        if (org.apache.commons.lang3.StringUtils.isBlank(value)) {
            throw new IllegalArgumentException("NumberFormatter.getNumber: passed null or empty string.");
        }

        String num = preprocessString(value);
        BigDecimal multiplier = BigDecimal.ONE;
        if (num.endsWith(MILLION_CHAR)) {
            num = StringUtils.chop(num);
            multiplier = MILLION_BD;
        } else if (num.endsWith(THOUSAND_CHAR)) {
            num = StringUtils.chop(num);
            multiplier = THOUSAND_BD;
        }
        return new BigDecimal(num).multiply(multiplier);
    }

    /**
     * Converts a string to a number. It will convert a number of different
     * formats for the numbers. E.g.
     * 
     * <ul>
     * <li>12345</li>
     * <li>12,345</li>
     * <li>12.345k</li>
     * <li>12345K</li>
     * <li>12345m</li>
     * <li>12345M</li>
     * <li>1,234.5K</li>
     * <li>12345</li>
     * <li>12345</li>
     * </ul>
     * 
     * @param value
     *            String to be converted to a number.
     * 
     * @return The number whole represented by the string.
     * 
     * @throws IllegalArgumentException
     *             Null of blank string passed in.
     * @throws NumberFormatException
     *             Sting isn't a valid whole number.
     */
    public static long getLong(String value) {
        BigDecimal num = getBigDecimal(value);
        try {
            return num.longValueExact();
        } catch (ArithmeticException e) {
            throw new NumberFormatException("Number should be a whole value.");
        }
    }

    /**
     * Converts a number to a string. It will convert a number to a shortened
     * formats of the string. E.g.
     * 
     * <ul>
     * <li>12345</li>
     * <li>12k</li>
     * <li>123m</li>
     * <li>1.234m</li>
     * <li>12.3k</li>
     * </ul>
     * 
     * @param value
     *            Number to convert to a string.
     * 
     * @return A string representation of the given number.
     */
    public static String getString(long value) {
        if (value > MILLION) {
            return shorten(value, MILLION_BD, MILLION_CHAR);
        }
        if (value > THOUSAND) {
            return shorten(value, THOUSAND_BD, THOUSAND_CHAR);
        }
        return String.valueOf(value);
    }

    /**
     * @param str
     *            String to preprocess.
     * 
     * @return Lower case string minus any spaces and commas.
     */
    private static String preprocessString(String str) {
        return StringUtils.replace(StringUtils.replace(str.toUpperCase(), ",", ""), " ", "");
    }

    /**
     * Re-writes the value to be the shortest possible string which doesn't
     * loose and precision.
     * 
     * @param value
     *            The value to convert to a short string.
     * @param size
     *            The 'units' we're going to use e.g. 1,000
     * @param suffix
     *            The suffix to use for the shortened string e.g. k
     * 
     * @return A string version of the number shortened as much as possible.
     */
    private static String shorten(long value, BigDecimal size, String suffix) {
        int threshold = 2;
        String base = String.valueOf(value);
        String shortend = BigDecimal.valueOf(value).divide(size).toPlainString();
        if (StringUtils.contains(shortend, ".")) {
            shortend = StringUtils.stripEnd(shortend, "0");
            threshold = 1;
        }
        if (base.length() - shortend.length() < threshold) {
            return base;
        }
        return shortend + suffix;
    }

    public static BigDecimal format(BigDecimal value, int dp) {
        return value.setScale(dp, BigDecimal.ROUND_HALF_UP);
    }

    private static final long THOUSAND = 1000;
    private static BigDecimal THOUSAND_BD = BigDecimal.valueOf(THOUSAND);
    private static String THOUSAND_CHAR = "K";
    private static final long MILLION = 1000000;
    private static BigDecimal MILLION_BD = BigDecimal.valueOf(MILLION);
    private static String MILLION_CHAR = "M";
}
