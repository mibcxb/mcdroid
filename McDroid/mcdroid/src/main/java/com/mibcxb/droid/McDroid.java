package com.mibcxb.droid;

import android.content.Context;

import com.mibcxb.droid.content.PrefsCache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @since 1.0.0
 */
public final class McDroid {
    private static final int VERSION_CODE = BuildConfig.VERSION_CODE;
    private static final String VERSION_NAME = BuildConfig.VERSION_NAME;

    private static Logger sLogger = LoggerFactory.getLogger("McDroid");

    private McDroid() {
    }

    public static void init(Context context) {
        PrefsCache.initialize(context);
    }

    public static int versionCode() {
        return VERSION_CODE;
    }

    public static String versionName() {
        return VERSION_NAME;
    }

    public static Logger logger() {
        return sLogger;
    }
}
