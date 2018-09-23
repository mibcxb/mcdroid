package com.mibcxb.droid.util;

public class ObjectUtils {
    public static boolean isInstance(Class cls, Object obj) {
        return cls != null && cls.isInstance(obj);
    }
}
