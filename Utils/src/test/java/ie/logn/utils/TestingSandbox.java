package ie.logn.utils;

import static org.junit.Assert.*;
import ie.logn.utils.logger.DefaultLogger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestingSandbox {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void exceptionTestOne() {
        try {
            int i = 1 / 0;
            assertNull(i);

        } catch (Exception ex) {
            DefaultLogger.info("Divide by zero");
        }
    }

}
