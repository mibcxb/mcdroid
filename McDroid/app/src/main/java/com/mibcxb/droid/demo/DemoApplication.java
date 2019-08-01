package com.mibcxb.droid.demo;

import android.app.Application;

import com.mibcxb.droid.McDroid;

import static com.mibcxb.droid.core.McDroidLog.logger;


public class DemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        McDroid.init(this);
        logger().error("McDroid initialized: {}", McDroid.versionName());
    }
}
