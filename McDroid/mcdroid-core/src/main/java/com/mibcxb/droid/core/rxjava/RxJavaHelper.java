package com.mibcxb.droid.core.rxjava;

import androidx.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxJavaHelper {
    public static <T> Observable<T> onMainThread(@NonNull Observable<T> observable) {
        return onMainThread(observable, null);
    }

    public static <T> Observable<T> onMainThread(@NonNull Observable<T> observable, Scheduler subscribeOn) {
        if (subscribeOn == null) {
            subscribeOn = Schedulers.io();
        }
        return observable.subscribeOn(subscribeOn).observeOn(AndroidSchedulers.mainThread());
    }

    public static Disposable observeOnMain(Consumer<Long> consumer) {
        return observeOnMain(Observable.just(System.currentTimeMillis()), consumer);
    }

    public static <T> Disposable observeOnMain(@NonNull Observable<T> observable, Consumer<T> consumer) {
        return observeOnMain(observable, null, consumer);
    }

    public static <T> Disposable observeOnMain(@NonNull Observable<T> observable, Scheduler subscribeOn,
                                               Consumer<T> consumer) {
        return observeOnMain(observable, subscribeOn, consumer, null);
    }

    public static <T> Disposable observeOnMain(@NonNull Observable<T> observable,
                                               Consumer<T> consumer, Consumer<Throwable> throwableConsumer) {
        return observeOnMain(observable, null, consumer, throwableConsumer, null);
    }

    public static <T> Disposable observeOnMain(@NonNull Observable<T> observable, Scheduler subscribeOn,
                                               Consumer<T> consumer, Consumer<Throwable> throwableConsumer) {
        return observeOnMain(observable, subscribeOn, consumer, throwableConsumer, null);
    }

    public static <T> Disposable observeOnMain(@NonNull Observable<T> observable, Scheduler subscribeOn,
                                               Consumer<T> consumer, Consumer<Throwable> throwableConsumer,
                                               Action completeAction) {
        if (consumer == null) {
            consumer = defaultConsumer();
        }
        if (throwableConsumer == null) {
            throwableConsumer = throwableConsumer();
        }
        if (completeAction == null) {
            completeAction = completeAction();
        }
        return onMainThread(observable, subscribeOn).subscribe(consumer, throwableConsumer, completeAction);
    }

    public static <T> Consumer<T> defaultConsumer() {
        return new Consumer<T>() {
            @Override
            public void accept(T target) throws Exception {
            }
        };
    }

    public static Consumer<Throwable> throwableConsumer() {
        return new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        };
    }

    public static Action completeAction() {
        return new Action() {
            @Override
            public void run() throws Exception {
            }
        };
    }
}
