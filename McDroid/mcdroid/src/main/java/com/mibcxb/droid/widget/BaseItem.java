package com.mibcxb.droid.widget;

import java.util.UUID;

public class BaseItem<Data> {
    private final String uuid = UUID.randomUUID().toString();
    private Data data;

    public BaseItem(Data data) {
        this.data = data;
    }

    public String getUuid() {
        return uuid;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseItem<?> baseItem = (BaseItem<?>) o;
        return uuid.equals(baseItem.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }
}
