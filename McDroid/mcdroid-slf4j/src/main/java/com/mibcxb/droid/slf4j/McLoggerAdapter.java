package com.mibcxb.droid.slf4j;

import com.mibcxb.droid.core.logger.McLogLevel;
import com.mibcxb.droid.core.logger.McLogPrinter;
import com.mibcxb.droid.core.logger.McLoggerSetter;

import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;

public class McLoggerAdapter extends MarkerIgnoringBase implements McLoggerSetter, McLogPrinter {
    private final String tag;
    private McLogLevel level;
    private McLogPrinter printer;

    McLoggerAdapter(String tag) {
        this.tag = tag;
    }

    private synchronized McLogLevel getLevel() {
        return level;
    }

    @Override
    public synchronized void setLevel(McLogLevel level) {
        this.level = level;
    }

    @Override
    public synchronized void setPrinter(McLogPrinter printer) {
        this.printer = printer;
    }

    @Override
    public synchronized void printLog(McLogLevel level, String tag, String msg) {
        if (printer != null) {
            printer.printLog(level, tag, msg);
        }
    }

    /**
     * Is this logger instance enabled for the VERBOSE level?
     *
     * @return True if this Logger is enabled for level VERBOSE, false otherwise.
     */
    @Override
    public boolean isTraceEnabled() {
        return isLoggable(McLogLevel.TRACE);
    }

    /**
     * Log a message object at level VERBOSE.
     *
     * @param msg - the message object to be logged
     */
    @Override
    public void trace(String msg) {
        log(McLogLevel.TRACE, msg, null);
    }

    /**
     * Log a message at level VERBOSE according to the specified format and
     * argument.
     *
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for level VERBOSE.
     * </p>
     *
     * @param format the format string
     * @param arg    the argument
     */
    @Override
    public void trace(String format, Object arg) {
        formatAndLog(McLogLevel.TRACE, format, arg);
    }

    /**
     * Log a message at level VERBOSE according to the specified format and
     * arguments.
     *
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the VERBOSE level.
     * </p>
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    @Override
    public void trace(String format, Object arg1, Object arg2) {
        formatAndLog(McLogLevel.TRACE, format, arg1, arg2);
    }

    /**
     * Log a message at level VERBOSE according to the specified format and
     * arguments.
     *
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the VERBOSE level.
     * </p>
     *
     * @param format   the format string
     * @param argArray an array of arguments
     */
    @Override
    public void trace(String format, Object... argArray) {
        formatAndLog(McLogLevel.TRACE, format, argArray);
    }

    /**
     * Log an exception (throwable) at level VERBOSE with an accompanying message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to log
     */
    @Override
    public void trace(String msg, Throwable t) {
        log(McLogLevel.TRACE, msg, t);
    }

    /**
     * Is this logger instance enabled for the DEBUG level?
     *
     * @return True if this Logger is enabled for level DEBUG, false otherwise.
     */
    @Override
    public boolean isDebugEnabled() {
        return isLoggable(McLogLevel.DEBUG);
    }

    /**
     * Log a message object at level DEBUG.
     *
     * @param msg - the message object to be logged
     */
    @Override
    public void debug(String msg) {
        log(McLogLevel.DEBUG, msg, null);
    }

    /**
     * Log a message at level DEBUG according to the specified format and argument.
     *
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for level DEBUG.
     * </p>
     *
     * @param format the format string
     * @param arg    the argument
     */
    @Override
    public void debug(String format, Object arg) {
        formatAndLog(McLogLevel.DEBUG, format, arg);
    }

    /**
     * Log a message at level DEBUG according to the specified format and
     * arguments.
     *
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the DEBUG level.
     * </p>
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    @Override
    public void debug(String format, Object arg1, Object arg2) {
        formatAndLog(McLogLevel.DEBUG, format, arg1, arg2);
    }

    /**
     * Log a message at level DEBUG according to the specified format and
     * arguments.
     *
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the DEBUG level.
     * </p>
     *
     * @param format   the format string
     * @param argArray an array of arguments
     */
    @Override
    public void debug(String format, Object... argArray) {
        formatAndLog(McLogLevel.DEBUG, format, argArray);
    }

    /**
     * Log an exception (throwable) at level DEBUG with an accompanying message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to log
     */
    @Override
    public void debug(String msg, Throwable t) {
        log(McLogLevel.DEBUG, msg, t);
    }

    /**
     * Is this logger instance enabled for the INFO level?
     *
     * @return True if this Logger is enabled for the INFO level, false otherwise.
     */
    @Override
    public boolean isInfoEnabled() {
        return isLoggable(McLogLevel.INFO);
    }

    /**
     * Log a message object at the INFO level.
     *
     * @param msg - the message object to be logged
     */
    @Override
    public void info(String msg) {
        log(McLogLevel.INFO, msg, null);
    }

    /**
     * Log a message at level INFO according to the specified format and argument.
     *
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the INFO level.
     * </p>
     *
     * @param format the format string
     * @param arg    the argument
     */
    @Override
    public void info(String format, Object arg) {
        formatAndLog(McLogLevel.INFO, format, arg);
    }

    /**
     * Log a message at the INFO level according to the specified format and
     * arguments.
     *
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the INFO level.
     * </p>
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    @Override
    public void info(String format, Object arg1, Object arg2) {
        formatAndLog(McLogLevel.INFO, format, arg1, arg2);
    }

    /**
     * Log a message at level INFO according to the specified format and
     * arguments.
     *
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the INFO level.
     * </p>
     *
     * @param format   the format string
     * @param argArray an array of arguments
     */
    @Override
    public void info(String format, Object... argArray) {
        formatAndLog(McLogLevel.INFO, format, argArray);
    }

    /**
     * Log an exception (throwable) at the INFO level with an accompanying
     * message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to log
     */
    @Override
    public void info(String msg, Throwable t) {
        log(McLogLevel.INFO, msg, t);
    }

    /**
     * Is this logger instance enabled for the WARN level?
     *
     * @return True if this Logger is enabled for the WARN level, false
     * otherwise.
     */
    @Override
    public boolean isWarnEnabled() {
        return isLoggable(McLogLevel.WARN);
    }

    /**
     * Log a message object at the WARN level.
     *
     * @param msg - the message object to be logged
     */
    @Override
    public void warn(String msg) {
        log(McLogLevel.WARN, msg, null);
    }

    /**
     * Log a message at the WARN level according to the specified format and
     * argument.
     *
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the WARN level.
     * </p>
     *
     * @param format the format string
     * @param arg    the argument
     */
    @Override
    public void warn(String format, Object arg) {
        formatAndLog(McLogLevel.WARN, format, arg);
    }

    /**
     * Log a message at the WARN level according to the specified format and
     * arguments.
     *
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the WARN level.
     * </p>
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    @Override
    public void warn(String format, Object arg1, Object arg2) {
        formatAndLog(McLogLevel.WARN, format, arg1, arg2);
    }

    /**
     * Log a message at level WARN according to the specified format and
     * arguments.
     *
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the WARN level.
     * </p>
     *
     * @param format   the format string
     * @param argArray an array of arguments
     */
    @Override
    public void warn(String format, Object... argArray) {
        formatAndLog(McLogLevel.WARN, format, argArray);
    }

    /**
     * Log an exception (throwable) at the WARN level with an accompanying
     * message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to log
     */
    @Override
    public void warn(String msg, Throwable t) {
        log(McLogLevel.WARN, msg, t);
    }

    /**
     * Is this logger instance enabled for level ERROR?
     *
     * @return True if this Logger is enabled for level ERROR, false otherwise.
     */
    @Override
    public boolean isErrorEnabled() {
        return isLoggable(McLogLevel.ERROR);
    }

    /**
     * Log a message object at the ERROR level.
     *
     * @param msg - the message object to be logged
     */
    @Override
    public void error(String msg) {
        log(McLogLevel.ERROR, msg, null);
    }

    /**
     * Log a message at the ERROR level according to the specified format and
     * argument.
     *
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the ERROR level.
     * </p>
     *
     * @param format the format string
     * @param arg    the argument
     */
    @Override
    public void error(String format, Object arg) {
        formatAndLog(McLogLevel.ERROR, format, arg);
    }

    /**
     * Log a message at the ERROR level according to the specified format and
     * arguments.
     *
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the ERROR level.
     * </p>
     *
     * @param format the format string
     * @param arg1   the first argument
     * @param arg2   the second argument
     */
    @Override
    public void error(String format, Object arg1, Object arg2) {
        formatAndLog(McLogLevel.ERROR, format, arg1, arg2);
    }

    /**
     * Log a message at level ERROR according to the specified format and
     * arguments.
     *
     * <p>
     * This form avoids superfluous object creation when the logger is disabled
     * for the ERROR level.
     * </p>
     *
     * @param format   the format string
     * @param argArray an array of arguments
     */
    @Override
    public void error(String format, Object... argArray) {
        formatAndLog(McLogLevel.ERROR, format, argArray);
    }

    /**
     * Log an exception (throwable) at the ERROR level with an accompanying
     * message.
     *
     * @param msg the message accompanying the exception
     * @param t   the exception (throwable) to log
     */
    @Override
    public void error(String msg, Throwable t) {
        log(McLogLevel.ERROR, msg, t);
    }

    private void formatAndLog(McLogLevel level, String format, Object... argArray) {
        if (isLoggable(level)) {
            FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
            logInternal(level, ft.getMessage(), ft.getThrowable());
        }
    }

    private void log(McLogLevel level, String message, Throwable throwable) {
        if (isLoggable(level)) {
            logInternal(level, message, throwable);
        }
    }

    private boolean isLoggable(McLogLevel level) {
        return level.intValue() >= getLevel().intValue();
    }

    private void logInternal(McLogLevel level, String message, Throwable throwable) {
        if (throwable != null) {
            message += '\n' + getStackTraceString(throwable);
        }
        printLog(level, tag, message);
    }

    /**
     * Handy function to get a loggable stack trace from a Throwable
     *
     * @param tr An exception to log
     */
    public static String getStackTraceString(Throwable tr) {
        if (tr == null) {
            return "";
        }

        // This is to reduce the amount of log spew that apps do in the non-error
        // condition of the network being unavailable.
        Throwable t = tr;
        while (t != null) {
            if (t instanceof UnknownHostException) {
                return "";
            }
            t = t.getCause();
        }

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        tr.printStackTrace(pw);
        pw.flush();
        return sw.toString();
    }
}
