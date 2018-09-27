package com.mibcxb.droid.util;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxJavaUtils {
    public static <T> Observable<T> onMainThread(@NonNull Observable<T> observable) {
        return onMainThread(observable, null);
    }

    public static <T> Observable<T> onMainThread(@NonNull Observable<T> observable, Scheduler scheduler) {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }
        return observable.subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread());
    }
}
