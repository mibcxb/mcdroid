package com.mibcxb.droid.core.util;

public class ObjectUtils {
    public static boolean instanceOf(Object obj, Class cls) {
        return cls != null && cls.isInstance(obj);
    }

    public static <T> T cast(Object obj, Class<T> cls) {
        return cast(obj, cls, null);
    }

    public static <T> T cast(Object obj, Class<T> cls, T fallback) {
        T tgt = null;
        if (instanceOf(obj, cls)) {
            tgt = cls.cast(obj);
        }
        return tgt != null ? tgt : fallback;
    }
}
