package com.mibcxb.droid.widget;

import java.util.Collection;
import java.util.List;

public class SafeBaseItemList<Item extends BaseItem> implements BaseItemList<Item> {
    private final ItemList<Item> itemList = new SafeItemList<>();

    @Override
    public Item getItem(String uuid) {
        return getItem(uuid, null);
    }

    @Override
    public Item getItem(final String uuid, Item fallback) {
        if (uuid == null) {
            return fallback;
        }
        List<Item> itemList = getItems(new Filter<Item>() {
            @Override
            public boolean accept(Item item) {
                return uuid.equals(item.getUuid());
            }
        });
        Item item = itemList.size() > 0 ? itemList.get(0) : null;
        return item != null ? item : fallback;
    }

    @Override
    public Item removeItem(final String uuid) {
        Item item = getItem(uuid);
        removeItem(item);
        return item;
    }

    @Override
    public boolean isEmpty() {
        return itemList.isEmpty();
    }

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
