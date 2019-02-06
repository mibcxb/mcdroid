package com.mibcxb.droid.widget;

import android.widget.BaseAdapter;

import java.util.Collection;
import java.util.List;

public abstract class BaseListAdapter<Item extends BaseItem> extends BaseAdapter implements BaseItemList<Item> {
    private final BaseItemList<Item> itemList = new SafeBaseItemList<>();

    // from BaseAdapter
    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // from BaseItemList
    @Override
    public Item getItem(String uuid) {
        return itemList.getItem(uuid);
    }

    @Override
    public Item getItem(String uuid, Item fallback) {
        return itemList.getItem(uuid, fallback);
    }

    @Override
    public Item removeItem(String uuid) {
        return itemList.removeItem(uuid);
    }

    // from ItemList
    @Override
    public int size() {
        return itemList.size();
    }

    @Override
    public int indexOf(Item item) {
        return itemList.indexOf(item);
    }

    @Override
    public Item getItem(int index) {
        return itemList.getItem(index);
    }

    @Override
    public Item getItem(int index, Item fallback) {
        return itemList.getItem(index, fallback);
    }

    @Override
    public List<Item> getItems() {
        return itemList.getItems();
    }

    @Override
    public List<Item> getItems(Filter<Item> filter) {
        return itemList.getItems(filter);
    }

    @Override
    public void addItem(Item item) {
        itemList.addItem(item);
    }

    @Override
    public void addItems(Collection<Item> collection) {
        itemList.addItems(collection);
    }

    @Override
    public Item removeItem(int index) {
        return itemList.removeItem(index);
    }

    @Override
    public void removeItem(Item item) {
        itemList.removeItem(item);
    }

    @Override
    public void removeItems(Collection<Item> collection) {
        itemList.removeItems(collection);
    }

    @Override
    public void clearItems() {
        itemList.clearItems();
    }
}
