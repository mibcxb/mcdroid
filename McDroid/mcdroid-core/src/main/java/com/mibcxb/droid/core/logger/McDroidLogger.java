package com.mibcxb.droid.core.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class McDroidLogger {
    private static final Logger LOGGER = LoggerFactory.getLogger("McDroid →");

    public static Logger logger() {
        return LOGGER;
    }

    public static Logger printE(Throwable throwable) {
        LOGGER.error(throwable.getMessage(), throwable);
        return LOGGER;
    }
}
