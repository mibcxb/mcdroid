package com.mibcxb.droid.slf4j;

import com.mibcxb.droid.logger.McLogLevel;

public interface McLogPrinter {
    void printLog(McLogLevel level, String tag, String msg);
}
