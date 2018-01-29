package com.mibcxb.droid;

import android.content.Context;

/**
 * @since 1.0.0
 */
public final class McDroid {
    private static final int VERSION_CODE = 10000;
    private static final String VERSION_NAME = "1.0.0";

    private McDroid() {
    }

    public static void init(Context context) {
    }

    public static int versionCode() {
        return VERSION_CODE;
    }

    public static String versionName() {
        return VERSION_NAME;
    }
}
