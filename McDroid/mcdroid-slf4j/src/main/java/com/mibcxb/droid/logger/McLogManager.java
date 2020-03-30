package com.mibcxb.droid.logger;

import com.mibcxb.droid.slf4j.McLogPrinter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class McLogManager {
    private static final String VERSION = "1.0.0";
    private static final String DEFAULT_LOGGER_NAME = "McLog(" + VERSION + ")#";
    private static final Logger DEFAULT_LOGGER = LoggerFactory.getLogger(DEFAULT_LOGGER_NAME);

    private static class Singleton {
        private static final McLogManager INSTANCE = new McLogManager();
    }

    public static McLogManager instance() {
        return Singleton.INSTANCE;
    }

    public static Logger defaultLogger() {
        return DEFAULT_LOGGER;
    }

    private final Map<String, Logger> loggerMap = new HashMap<>();
    private final AtomicReference<McLogLevel> defaultLevel = new AtomicReference<>();
    private final AtomicReference<McLogPrinter> defaultPrinter = new AtomicReference<>();

    private McLogManager() {
        defaultLevel.set(McLogLevel.ERROR);

        Logger defaultLogger = defaultLogger();
        McLogHelper.setLevel(defaultLogger, defaultLevel.get());
        McLogHelper.setPrinter(defaultLogger, defaultPrinter.get());
    }

    public Logger createLogger(String loggerName) {
        if (isEmptyString(loggerName)) {
            throw new IllegalArgumentException("ERROR! Illegal logger name: " + loggerName);
        }
        synchronized (loggerMap) {
            Logger logger = loggerMap.get(loggerName);
            if (logger == null) {
                logger = LoggerFactory.getLogger(loggerName);
                McLogHelper.setLevel(logger, defaultLevel.get());
                McLogHelper.setPrinter(logger, defaultPrinter.get());
                loggerMap.put(loggerName, logger);
            }
            return logger;
        }
    }

    public void deleteLogger(String loggerName) {
        if (isEmptyString(loggerName)) {
            return;
        }
        synchronized (loggerMap) {
            loggerMap.remove(loggerName);
        }
    }

    public void setLogLevel(McLogLevel level) {
        if (level == null) {
            defaultLogger().warn("Logger level cannot be NULL. Abort.");
            return;
        }
        defaultLevel.set(level);

        Logger defaultLogger = defaultLogger();
        McLogHelper.setLevel(defaultLogger, defaultLevel.get());
        synchronized (loggerMap) {
            for (Logger logger : loggerMap.values()) {
                McLogHelper.setLevel(logger, level);
            }
        }
    }

    public void setPrinter(McLogPrinter printer) {
        if (printer == null) {
            defaultLogger().warn("Logger printer cannot be NULL. Abort.");
            return;
        }
        defaultPrinter.set(printer);

        Logger defaultLogger = defaultLogger();
        McLogHelper.setPrinter(defaultLogger, defaultPrinter.get());
        synchronized (loggerMap) {
            for (Logger logger : loggerMap.values()) {
                McLogHelper.setPrinter(logger, printer);
            }
        }
    }

    private boolean isEmptyString(String s) {
        return s == null || s.isEmpty();
    }
}
