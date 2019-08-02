package com.mibcxb.droid.core.logger;

public interface McLogPrinter {
    void printLog(McLogLevel level, String tag, String msg);

    class SystemPrinter implements McLogPrinter {
        private static final String FORMAT = "%s/%s: %s";

        @Override
        public void printLog(McLogLevel level, String tag, String msg) {
            System.out.println(String.format(FORMAT, level.name(), tag, msg));
        }
    }
}
