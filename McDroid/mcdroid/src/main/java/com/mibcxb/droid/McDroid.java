package com.mibcxb.droid;

import android.content.Context;

import com.mibcxb.droid.core.content.PrefsCache;

/**
 * @since 1.0.0
 */
public final class McDroid {

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
}
