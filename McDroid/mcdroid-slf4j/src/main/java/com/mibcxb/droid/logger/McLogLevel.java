package com.mibcxb.droid.logger;

public enum McLogLevel {
    NONE(Integer.MAX_VALUE),
    TRACE(1),
    DEBUG(2),
    INFO(3),
    WARN(4),
    ERROR(5);

    private final int value;

    McLogLevel(int value) {
        this.value = value;
    }

    public int intValue() {
        return value;
    }

    public static McLogLevel valueToLevel(int value) {
        switch (value) {
            case 1:
                return TRACE;
            case 2:
                return DEBUG;
            case 3:
                return INFO;
            case 4:
                return WARN;
            case 5:
                return ERROR;
            default:
                return NONE;
        }
    }
}
