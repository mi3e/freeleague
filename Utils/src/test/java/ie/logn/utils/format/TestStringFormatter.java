package ie.logn.utils.format;

import static org.junit.Assert.*;
import ie.logn.utils.object.ReflectionUtils;

import org.junit.Test;

public class TestStringFormatter {

    @Test
    public void testConvertMethodNameToSqlColumn() {
        String methodName = "setMyRedHotCar";

        String sqlColumn = ReflectionUtils.convertMethodNameToSqlColumnName(methodName, "set");

        assertEquals("MY_RED_HOT_CAR", sqlColumn);
    }

}
