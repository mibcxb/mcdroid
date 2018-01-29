package com.mibcxb.droid.demo;

import android.app.Application;
import android.util.Log;

import com.mibcxb.droid.McDroid;

public class DemoApplication extends Application {
    public static final String TAG = DemoApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        McDroid.init(this);
        Log.e(TAG, "McDroid initialized: " + McDroid.versionName());
    }
}
