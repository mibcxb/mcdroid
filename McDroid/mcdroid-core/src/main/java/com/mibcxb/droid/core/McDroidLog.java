package com.mibcxb.droid.core;

import com.mibcxb.droid.core.logger.McLogManager;

import org.slf4j.Logger;

public class McDroidLog {
    private static final String NAME = "McDroid →";
    private static Logger sLogger;

    public static Logger logger() {
        synchronized (NAME) {
            if (sLogger == null) {
                sLogger = McLogManager.instance().createLogger(NAME);
            }
            return sLogger;
        }
    }

    public static void printE(Throwable throwable) {
        logger().error(throwable.getMessage(), throwable);
    }
}
