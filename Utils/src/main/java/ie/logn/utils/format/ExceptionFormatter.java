package ie.logn.utils.format;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionFormatter {
    public static String exceptionStackTraceToString(Exception ex) {
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}
