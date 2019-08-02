package com.mibcxb.droid.demo;

import android.app.Application;

import com.mibcxb.droid.McDroid;
import com.mibcxb.droid.core.logger.McLogLevel;
import com.mibcxb.droid.core.logger.McLogManager;
import com.mibcxb.droid.slf4j.McDroidPrinter;

import static com.mibcxb.droid.core.McDroidLog.logger;

public class DemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        McDroid.init(this);
        McLogManager.instance().setLogLevel(McLogLevel.DEBUG);
        McLogManager.instance().setPrinter(new McDroidPrinter());
        logger().error("McDroid initialized: {}", McDroid.versionName());
    }
}
