package com.mibcxb.droid.slf4j;

import android.util.Log;

import com.mibcxb.droid.core.logger.McLogLevel;
import com.mibcxb.droid.core.logger.McLogPrinter;

public class McDroidPrinter implements McLogPrinter {
    @Override
    public void printLog(McLogLevel level, String tag, String msg) {
        switch (level) {
            case TRACE:
                Log.v(tag, msg);
                break;
            case DEBUG:
                Log.d(tag, msg);
                break;
            case INFO:
                Log.i(tag, msg);
                break;
            case WARN:
                Log.w(tag, msg);
                break;
            case ERROR:
                Log.e(tag, msg);
                break;
            default:
                break;
        }
    }
}
