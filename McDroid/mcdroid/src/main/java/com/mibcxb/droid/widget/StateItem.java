package com.mibcxb.droid.widget;

import android.support.annotation.IntDef;

import java.util.concurrent.atomic.AtomicInteger;

public class StateItem<Data> extends BaseItem<Data> {
    public static final int ST_DEFAULT = 0;
    public static final int ST_CHECKED = 1;

    private final AtomicInteger stateRef;

    public StateItem(Data item) {
        this(item, ST_DEFAULT);
    }

    public StateItem(Data data, @State int state) {
        super(data);
        stateRef = new AtomicInteger(state);
    }

    public boolean isChecked() {
        return getState() == ST_CHECKED;
    }

    public int getState() {
        return stateRef.get();
    }

    public void setState(@State int state) {
        stateRef.set(state);
    }

    @IntDef({ST_DEFAULT, ST_CHECKED})
    public @interface State {
    }
}
