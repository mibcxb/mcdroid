package com.mibcxb.droid.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class PagingHelper<DataType> {
    public enum Mode {
        PAGE, CURSOR
    }

    public interface OnPageLoadCallback {
        void onPageLoad(int index, int count);
    }

    public interface OnDataChangeListener<DataType> {
        void onDataChange(List<DataType> dataList, boolean noMoreData);
    }

    public static final int DEFAULT_START_INDEX = 1;
    public static final int DEFAULT_PAGE_LENGTH = 10;

    public static final int STATE_IDLE = 0;
    public static final int STATE_LOAD = 1;
    public static final int STATE_NEXT = 2;
    public static final int STATE_STOP = 9;

    private final Mode mode;
    private final AtomicInteger state;
    private final List<DataType> dataList;
    private final List<DataType> noneList;
    private final AtomicBoolean complete;

    private int startIndex;
    private int pageLength;

    private OnPageLoadCallback onPageLoadCallback;
    private OnDataChangeListener<DataType> onDataChangeListener;

    public PagingHelper() {
        this(Mode.PAGE);
    }

    public PagingHelper(Mode mode) {
        this(mode, DEFAULT_START_INDEX, DEFAULT_PAGE_LENGTH);
    }

    public PagingHelper(Mode mode, int startIndex, int pageLength) {
        this.mode = mode;
        this.state = new AtomicInteger(STATE_IDLE);
        this.dataList = new ArrayList<>();
        this.noneList = new ArrayList<>(0);
        this.complete = new AtomicBoolean(false);

        this.startIndex = startIndex;
        this.pageLength = pageLength;
    }

    public List<DataType> getDataList() {
        synchronized (dataList) {
            return dataList;
        }
    }

    public void setOnPageLoadCallback(OnPageLoadCallback onPageLoadCallback) {
        this.onPageLoadCallback = onPageLoadCallback;
    }

    public void setOnDataChangeListener(OnDataChangeListener<DataType> onDataChangeListener) {
        this.onDataChangeListener = onDataChangeListener;
    }

    private synchronized void start(int index, int count) {
        if (onPageLoadCallback == null) {
            throw new RuntimeException("Please set OnPageLoadCallback for loading");
        }
        onPageLoadCallback.onPageLoad(index, count);
    }

    public void stop() {
        state.set(STATE_STOP);
    }

    public void load() {
        reset();
        start(startIndex, pageLength);
        state.set(STATE_LOAD);
    }

    public void next() {
        if (complete.get()) {
            notifyDataChange(noneList, true);
            return;
        }

        int index;
        if (mode == Mode.PAGE) {
            index = dataList.size() / pageLength + startIndex;
        } else {
            index = dataList.size() + startIndex;
        }
        start(index, pageLength);
        state.set(STATE_NEXT);
    }

    public void reset() {
        synchronized (dataList) {
            dataList.clear();
            complete.set(false);
        }
    }

    public void setResult(List<DataType> collection) {
        if (state.compareAndSet(STATE_STOP, STATE_IDLE)) {
            return;
        }

        if (collection == null) {
            setFailed();
            return;
        }

        synchronized (dataList) {
            dataList.addAll(collection);
            complete.set(collection.size() < pageLength);
        }
        notifyDataChange(collection, complete.get());
        state.set(STATE_IDLE);
    }

    public void setFailed() {
        notifyDataChange(null, false);
        state.set(STATE_IDLE);
    }

    private synchronized void notifyDataChange(List<DataType> dataList, boolean noMoreData) {
        if (onDataChangeListener != null) {
            onDataChangeListener.onDataChange(dataList, noMoreData);
        }
    }
}
