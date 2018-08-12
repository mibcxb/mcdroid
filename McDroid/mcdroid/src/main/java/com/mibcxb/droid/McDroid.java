package com.mibcxb.droid;

import android.content.Context;

import com.mibcxb.droid.content.PrefsCache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @since 1.0.0
 */
public final class McDroid {
    private static Logger sLogger = LoggerFactory.getLogger("McDroid");

    private McDroid() {
    }

    public static void init(Context context) {
        PrefsCache.initialize(context);
    }

    public static int versionCode() {
        return BuildConfig.VERSION_CODE;
    }

    public static String versionName() {
        return BuildConfig.VERSION_NAME;
    }

    public static Logger logger() {
        return sLogger;
    }

    public static void printError(Throwable throwable) {
        sLogger.error(throwable.getMessage(), throwable);
    }
}
