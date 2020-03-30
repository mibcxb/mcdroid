package com.mibcxb.droid.logger;

import com.mibcxb.droid.slf4j.McLogPrinter;
import com.mibcxb.droid.slf4j.McLogger;

import org.slf4j.Logger;

/**
 * 日志帮助类
 */
final class McLogHelper {
    /**
     * 设置Logger的日志输出级别
     *
     * @param logger 日志对象，{@link McLogger}的实现类
     * @param level  输出级别
     */
    static void setLevel(Logger logger, McLogLevel level) {
        if (logger instanceof McLogger) {
            ((McLogger) logger).setLevel(level);
        }
    }

    /**
     * 设置Logger的日志打印器
     *
     * @param logger  日志对象，{@link McLogger}的实现类
     * @param printer 日志打印器
     */
    static void setPrinter(Logger logger, McLogPrinter printer) {
        if (logger instanceof McLogger) {
            ((McLogger) logger).setPrinter(printer);
        }
    }
}
