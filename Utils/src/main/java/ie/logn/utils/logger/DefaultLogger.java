package ie.logn.utils.logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.MessageFormat;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Utility methods for logging. Uses the commons logging library.
 * 
 */
public class DefaultLogger {

    /**
     * Log a message with fatal level.
     * 
     * @param msg
     *            Object to log.
     */
    public static void fatal(Throwable msg) {
        _defaultLogger.fatal(getMessage(msg));
    }

    /**
     * @param msg
     * @param obj
     */
    public static void fatal(String msg, Throwable obj) {
        _defaultLogger.fatal(msg + CRTAB + getMessage(obj, true));
    }

    /**
     * Log a message with fatal level using params as parameters to
     * MessageFormat.
     * 
     * @param msg
     *            Object to log.
     * @param params
     *            Parameters passed to MessageFormat.format.
     */
    public static void fatal(String msg, Object... params) {
        _defaultLogger.fatal(MessageFormat.format(msg, params));
    }

    /**
     * Log an fatal level message against a specified Logger.
     * 
     * @param logger
     *            The logger name to log the message against.
     * @param msg
     *            The message to log
     */
    public static void fatalEx(String logger, Object msg) {
        _factory.getInstance(logger).fatal(msg);
    }

    /**
     * Log an fatal level message against a specified Logger using a
     * parameterised message string.
     * 
     * @param logger
     *            The logger name to log the message against.
     * @param msg
     *            The message to log
     * @param params
     *            Params passed into MessageFormat.format call.
     */
    public static void fatalEx(String logger, String msg, Object... params) {
        _factory.getInstance(logger).fatal(MessageFormat.format(msg, params));
    }

    /**
     * Log a message with error level.
     * 
     * @param msg
     *            Object to log.
     */
    public static void error(Throwable msg) {
        _defaultLogger.error(getMessage(msg));
    }

    /**
     * @param msg
     * @param obj
     */
    public static void error(String msg, Throwable obj) {
        _defaultLogger.error(msg + CRTAB + getMessage(obj, true));
    }

    /**
     * Log a message with error level using params as parameters to
     * MessageFormat.
     * 
     * @param msg
     *            Object to log.
     * @param params
     *            Parameters passed to MessageFormat.format.
     */
    public static void error(String msg, Object... params) {
        _defaultLogger.error(MessageFormat.format(msg, params));
    }

    /**
     * Log an error level message against a specified Logger.
     * 
     * @param logger
     *            The logger name to log the message against.
     * @param msg
     *            The message to log
     */
    public static void errorEx(String logger, Object msg) {
        _factory.getInstance(logger).error(msg);
    }

    /**
     * Log an error level message against a specified Logger using a
     * parameterised message string.
     * 
     * @param logger
     *            The logger name to log the message against.
     * @param msg
     *            The message to log
     * @param params
     *            Params passed into MessageFormat.format call.
     */
    public static void errorEx(String logger, String msg, Object... params) {
        _factory.getInstance(logger).error(MessageFormat.format(msg, params));
    }

    /**
     * Log a message with warn level.
     * 
     * @param msg
     *            Object to log.
     */
    public static void warn(Throwable msg) {
        _defaultLogger.warn(getMessage(msg));
    }

    /**
     * @param msg
     * @param obj
     */
    public static void warn(String msg, Throwable obj) {
        _defaultLogger.warn(msg + CRTAB + getMessage(obj, true));
    }

    /**
     * Log a parameterised message with warn level.
     * 
     * @param msg
     *            Message to log.
     * @param params
     *            Parameters which will be processed via MessageFormat.format
     *            against the msg string.
     */
    public static void warn(String msg, Object... params) {
        _defaultLogger.warn(MessageFormat.format(msg, params));
    }

    /**
     * Log a warn level message against a specified Logger.
     * 
     * @param logger
     *            The logger name to log the message against.
     * @param msg
     *            The message to log
     */
    public static void warnEx(String logger, Object msg) {
        _factory.getInstance(logger).warn(msg);
    }

    /**
     * Log a warn level message against a specified Logger using a parameterised
     * message string.
     * 
     * @param logger
     *            The logger name to log the message against.
     * @param msg
     *            The message to log
     * @param params
     *            Params passed into MessageFormat.format call.
     */
    public static void warnEx(String logger, String msg, Object... params) {
        _factory.getInstance(logger).warn(MessageFormat.format(msg, params));
    }

    /**
     * Log a message with info level.
     * 
     * @param msg
     *            Object to log.
     */
    public static void info(Throwable msg) {
        if (_defaultLogger.isInfoEnabled()) {
            _defaultLogger.info(getMessage(msg));
        }
    }

    /**
     * @param msg
     * @param obj
     */
    public static void info(String msg, Throwable obj) {
        if (_defaultLogger.isInfoEnabled()) {
            _defaultLogger.info(msg + CRTAB + getMessage(obj, true));
        }
    }

    /**
     * Log a parameterised message with info level.
     * 
     * @param msg
     *            Message to log.
     * @param params
     *            Parameters which will be processed via MessageFormat.format
     *            against the msg string.
     */
    public static void info(String msg, Object... params) {
        if (_defaultLogger.isInfoEnabled()) {
            _defaultLogger.info(MessageFormat.format(msg, params));
        }
    }

    /**
     * Log an info level message against a specified Logger.
     * 
     * @param logger
     *            The logger name to log the message against.
     * @param msg
     *            The message to log
     */
    public static void infoEx(String logger, Object msg) {
        Log l = _factory.getInstance(logger);
        if (l.isInfoEnabled()) {
            l.info(msg);
        }
    }

    /**
     * Log an info level message against a specified Logger using a
     * parameterised message string.
     * 
     * @param logger
     *            The logger name to log the message against.
     * @param msg
     *            The message to log
     * @param params
     *            Params passed into MessageFormat.format call.
     */
    public static void infoEx(String logger, String msg, Object... params) {
        Log l = _factory.getInstance(logger);
        if (l.isInfoEnabled()) {
            l.info(MessageFormat.format(msg, params));
        }
    }

    /**
     * Log a message with debug level.
     * 
     * @param msg
     *            Object to log.
     */
    public static void debug(Throwable msg) {
        if (_defaultLogger.isDebugEnabled()) {
            _defaultLogger.debug(getMessage(msg));
        }
    }

    /**
     * @param msg
     * @param obj
     */
    public static void debug(String msg, Throwable obj) {
        if (_defaultLogger.isDebugEnabled()) {
            _defaultLogger.debug(msg + CRTAB + getMessage(obj, true));
        }
    }

    /**
     * Log a parameterised message with debug level.
     * 
     * @param msg
     *            Message to log.
     * @param params
     *            Parameters which will be processed via MessageFormat.format
     *            against the msg string.
     */
    public static void debug(String msg, Object... params) {
        if (_defaultLogger.isDebugEnabled()) {
            _defaultLogger.debug(MessageFormat.format(msg, params));
        }
    }

    /**
     * Log a debug level message against a specified Logger.
     * 
     * @param logger
     *            The logger name to log the message against.
     * @param msg
     *            The message to log
     */
    public static void debugEx(String logger, Object msg) {
        Log l = _factory.getInstance(logger);
        if (l.isDebugEnabled()) {
            l.debug(msg);
        }
    }

    /**
     * Log a debug level message against a specified Logger using a
     * parameterised message string.
     * 
     * @param logger
     *            The logger name to log the message against.
     * @param msg
     *            The message to log
     * @param params
     *            Params passed into MessageFormat.format call.
     */
    public static void debugEx(String logger, String msg, Object... params) {
        Log l = _factory.getInstance(logger);
        if (l.isDebugEnabled()) {
            l.debug(MessageFormat.format(msg, params));
        }
    }

    /**
     * Log a message with trace level.
     * 
     * @param msg
     *            Object to log.
     */
    public static void trace(Throwable msg) {
        if (isTraceEnabled(_defaultLogger) && _defaultLogger.isDebugEnabled()) {
            _defaultLogger.trace(getMessage(msg));
        }
    }

    /**
     * @param msg
     * @param obj
     */
    public static void trace(String msg, Throwable obj) {
        if (isTraceEnabled(_defaultLogger) && _defaultLogger.isDebugEnabled()) {
            _defaultLogger.trace(msg + CRTAB + getMessage(obj, true));
        }
    }

    /**
     * Log a parameterised message with trace level.
     * 
     * @param msg
     *            Message to log.
     * @param params
     *            Parameters which will be processed via MessageFormat.format
     *            against the msg string.
     */
    public static void trace(String msg, Object... params) {
        if (isTraceEnabled(_defaultLogger) && _defaultLogger.isDebugEnabled()) {
            _defaultLogger.trace(MessageFormat.format(msg, params));
        }
    }

    /**
     * Log a trace level message against a specified Logger.
     * 
     * @param logger
     *            The logger name to log the message against.
     * @param msg
     *            The message to log
     */
    public static void traceEx(String logger, Object msg) {
        Log l = _factory.getInstance(logger);
        if (isTraceEnabled(l) && l.isDebugEnabled()) {
            l.trace(msg);
        }
    }

    /**
     * Log a trace level message against a specified Logger using a
     * parameterised message string.
     * 
     * @param logger
     *            The logger name to log the message against.
     * @param msg
     *            The message to log
     * @param params
     *            Params passed into MessageFormat.format call.
     */
    public static void traceEx(String logger, String msg, Object... params) {
        Log l = _factory.getInstance(logger);
        if (isTraceEnabled(l) && l.isDebugEnabled()) {
            l.trace(MessageFormat.format(msg, params));
        }
    }

    /**
     * Output a message to the "output" Logger. Useful for temporary logging
     * during development. The level for this Logger can have its output level
     * set independently of the others.
     * 
     * @param msg
     *            Object to log.
     */
    public static void dump(Throwable msg) {
        Log l = _factory.getInstance(OUTPUT_LOGGER_NAME);
        if (l.isDebugEnabled()) {
            l.debug(getMessage(msg));
        }
    }

    /**
     * @param msg
     * @param obj
     */
    public static void dump(String msg, Throwable obj) {
        Log l = _factory.getInstance(OUTPUT_LOGGER_NAME);
        if (l.isDebugEnabled()) {
            l.debug(msg + CRTAB + getMessage(obj, true));
        }
    }

    /**
     * Log a parameterised message with trace level.
     * 
     * @param msg
     *            Message to log.
     * @param params
     *            Parameters which will be processed via MessageFormat.format
     *            against the msg string.
     */
    public static void dump(String msg, Object... params) {
        Log l = _factory.getInstance(OUTPUT_LOGGER_NAME);
        if (l.isDebugEnabled()) {
            l.debug(MessageFormat.format(msg, params));
        }
    }

    /**
     * @param logger
     * @param msg
     */
    public static void dumpEx(String logger, Object msg) {
        Log l = _factory.getInstance(OUTPUT_LOGGER_NAME + "." + logger);
        if (l.isDebugEnabled()) {
            l.debug(msg);
        }
    }

    /**
     * Log a trace level message against a specified Logger using a
     * parameterised message string.
     * 
     * @param logger
     *            The logger name to log the message against.
     * @param msg
     *            The message to log
     * @param params
     *            Params passed into MessageFormat.format call.
     */
    public static void dumpEx(String logger, String msg, Object... params) {
        Log l = _factory.getInstance(OUTPUT_LOGGER_NAME + "." + logger);
        if (l.isDebugEnabled()) {
            l.debug(MessageFormat.format(msg, params));
        }
    }

    /**
     * @return The default Logger.
     */
    public static Log getDefaultLogger() {
        return _defaultLogger;
    }

    /**
     * @param name
     * 
     * @return Log instance with the given name.
     */
    public static Log setDefaultLoggerName(String name) {
        return _factory.getInstance(name);
    }

    /**
     * Returns a string of the stack trace for an exception.
     * 
     * @param e
     *            Exception to get stackTrace for.
     * 
     * @return The stack trace.
     */
    public static String getStackTrace(Throwable e) {
        StringWriter s = new StringWriter(1024);
        PrintWriter w = new PrintWriter(s);
        e.printStackTrace(w);

        return s.toString();
    }

    private static boolean isTraceEnabled(Log logger) {
        return _localTrace && logger.isTraceEnabled();
    }

    private static String getMessage(Throwable msg) {
        return getMessage(msg, false);
    }

    private static String getMessage(Throwable msg, boolean stackTrace) {
        String result = getStackTrace(msg);
        if (stackTrace) {
            result = StringUtils.replace(result, SystemUtils.LINE_SEPARATOR, SystemUtils.LINE_SEPARATOR + "\t");
        }
        return result;
    }

    private DefaultLogger() {
        super();
    }

    private static final String DEFAULT_LOGGER_NAME = "log.std";
    private static final String OUTPUT_LOGGER_NAME = "log.output";
    private static final String CRTAB = SystemUtils.LINE_SEPARATOR + "\t";
    private static LogFactory _factory = LogFactory.getFactory();
    private static Log _defaultLogger = setDefaultLoggerName(DEFAULT_LOGGER_NAME);
    // To set this pass -Dlog.trace=true to the VM when running the app
    private static boolean _localTrace = Boolean.getBoolean("log.trace");

}