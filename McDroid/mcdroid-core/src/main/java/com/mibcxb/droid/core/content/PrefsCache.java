package com.mibcxb.droid.core.content;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.mibcxb.droid.core.util.StringUtils;

import static com.mibcxb.droid.core.McDroidLog.logger;


public class PrefsCache {
    private static final String NAME = "com.mibcxb.droid.content.prefs.cahce";
    private static final PrefsCache ourInstance = new PrefsCache();

    private static final long NEVER_EXPIRED = -1;
    private static final long DO_NOT_CHANGE = 0;

    public static void initialize(Context context) {
        instance().doInit(context);
    }

    public static PrefsCache instance() {
        return ourInstance;
    }

    private SharedPreferences prefs;
    private Gson gson = new Gson();

    private PrefsCache() {
    }

    private synchronized void doInit(Context context) {
        if (prefs == null) {
            prefs = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        }
    }

    public <T> void put(String name, T value) {
        put(name, value, DO_NOT_CHANGE);
    }

    public <T> void put(String name, T value, long expireTime) {
        if (StringUtils.isBlank(name)) {
            return;
        }
        String cacheJson = prefs.getString(name, null);
        Cache cache = null;
        if (!StringUtils.isBlank(cacheJson)) {
            try {
                cache = gson.fromJson(cacheJson, Cache.class);
            } catch (Exception e) {
                logger().error("PrefsCache.EXP '" + e.getMessage() + "'", e);
            }
        }
        if (cache == null) {
            cache = new Cache();
            cache.createTime = System.currentTimeMillis();
            cache.targetTime = cache.createTime;
            cache.expireTime = expireTime > 0 ? expireTime : NEVER_EXPIRED;
        } else {
            if (expireTime != DO_NOT_CHANGE) {
                cache.expireTime = expireTime;
                cache.targetTime = System.currentTimeMillis();
            }
        }
        cache.updateTime = System.currentTimeMillis();
        cache.objectJson = value != null ? gson.toJson(value) : null;
        cacheJson = gson.toJson(cache);
        prefs.edit().putString(name, cacheJson).apply();
        logger().info("PrefsCache.PUT '{}' Success.", name);
    }

    public <T> T get(String name, Class<T> clazz) {
        if (StringUtils.isBlank(name) || clazz == null) {
            return null;
        }
        String cacheJson = prefs.getString(name, null);
        Cache cache = null;
        T object = null;
        try {
            cache = gson.fromJson(cacheJson, Cache.class);
            if (cache != null && !StringUtils.isBlank(cache.objectJson)) {
                object = gson.fromJson(cache.objectJson, clazz);
            }
        } catch (Exception e) {
            logger().error("PrefsCache.EXP '" + e.getMessage() + "'", e);
        }
        if (object != null &&
                (cache.expireTime < 0 || System.currentTimeMillis() < cache.targetTime + cache.expireTime)) {
            return clazz.cast(object);
        }
        return null;
    }

    class Cache {
        long createTime;
        long updateTime;
        long targetTime;
        long expireTime = -1;
        String objectJson;
    }
}
