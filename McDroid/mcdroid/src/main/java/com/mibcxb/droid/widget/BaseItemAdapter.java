package com.mibcxb.droid.widget;

import android.support.annotation.NonNull;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BaseItemAdapter<Item extends BaseItem> extends BaseAdapter {
    private final List<Item> itemList = new ArrayList<>();

    public void addItem(@NonNull Item item) {
        synchronized (itemList) {
            if (!itemList.contains(item)) {
                itemList.add(item);
            }
        }
    }

    public void addItem(int position, @NonNull Item item) {
        synchronized (itemList) {
            if (!itemList.contains(item)) {
                itemList.add(position, item);
            }
        }
    }

    public void addItemList(@NonNull Collection<Item> collection) {
        if (collection.isEmpty()) {
            return;
        }
        for (Item item : collection) {
            addItem(item);
        }
    }

    public Item removeItem(int position) {
        Item item = null;
        synchronized (itemList) {
            if (0 <= position && position < itemList.size()) {
                item = itemList.remove(position);
            }
        }
        return item;
    }

    public boolean removeItem(@NonNull Item item) {
        synchronized (itemList) {
            return itemList.remove(item);
        }
    }

    public void clearItemList() {
        synchronized (itemList) {
            itemList.clear();
        }
    }

    @NonNull
    public List<Item> getItemList() {
        List<Item> list;
        synchronized (itemList) {
            list = new ArrayList<>(itemList);
        }
        return list;
    }

    @Override
    public int getCount() {
        synchronized (itemList) {
            return itemList.size();
        }
    }

    @Override
    public Item getItem(int position) {
        Item item = null;
        synchronized (itemList) {
            if (0 <= position && position < itemList.size()) {
                item = itemList.get(position);
            }
        }
        return item;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
