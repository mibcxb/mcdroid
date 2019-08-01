package com.mibcxb.droid.core.logger;

public interface McLogPrinter {
    void printLog(McLogLevel level, String tag, String msg);

    class SystemPrinter implements McLogPrinter {
        @Override
        public void printLog(McLogLevel level, String tag, String msg) {
            System.out.println();
        }
    }
}
