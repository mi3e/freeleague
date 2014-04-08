package ie.logn.utils.format;

import static org.junit.Assert.assertEquals;
import ie.logn.utils.format.NumberFormatter;

import java.math.BigDecimal;
import java.text.DecimalFormatSymbols;

import org.junit.Test;

public class TestNumberFormatter {
    @Test
    public void testSetDpAndFormatWithCommas() {
        BigDecimal amount = new BigDecimal("10000.999");

        String strAmount = NumberFormatter.setDpAndFormatWithCommas(amount, 0);

        assertEquals("10" + DecimalFormatSymbols.getInstance().getGroupingSeparator() + "001", strAmount);
    }
}
