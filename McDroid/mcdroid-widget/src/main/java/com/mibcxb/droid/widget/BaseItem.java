package com.mibcxb.droid.widget;

import java.util.UUID;

public class BaseItem<Data> {
    private final String uuid = UUID.randomUUID().toString();
    private final int type;
    private Data data;

    public BaseItem(int type, Data data) {
        this.type = type;
        this.data = data;
    }

    public String getUuid() {
        return uuid;
    }

    public int getType() {
        return type;
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
