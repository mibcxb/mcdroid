package com.mibcxb.droid.demo;

import android.app.Application;

import com.mibcxb.droid.McDroid;
import com.mibcxb.droid.logger.McLogLevel;
import com.mibcxb.droid.logger.McLogManager;
import com.mibcxb.droid.slf4j.McDroidPrinter;

public class DemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        McDroid.init(this);
        McLogManager.instance().setLogLevel(McLogLevel.DEBUG);
        McLogManager.instance().setPrinter(new McDroidPrinter());
        McLogManager.defaultLogger().error("McDroid initialized: {}", McDroid.versionName());
    }
}
