package com.mibcxb.droid.widget;

public abstract class ImageItem<Data> extends StateItem<Data> {
    public ImageItem(Data item) {
        super(item);
    }

    public ImageItem(Data data, int state) {
        super(data, state);
    }

    public abstract String getImageUri();
}
