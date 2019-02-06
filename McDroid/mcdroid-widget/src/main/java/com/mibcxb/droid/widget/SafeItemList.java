package com.mibcxb.droid.widget;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class SafeItemList<Item> implements ItemList<Item> {
    private final List<Item> itemList = new LinkedList<>();

    @Override
    public boolean isEmpty() {
        synchronized (itemList) {
            return itemList.isEmpty();
        }
    }

    @Override
    public int size() {
        synchronized (itemList) {
            return itemList.size();
        }
    }

    @Override
    public int indexOf(Item item) {
        if (item == null) {
            return -1;
        }
        synchronized (itemList) {
            return itemList.indexOf(item);
        }
    }

    @Override
    public Item getItem(int index) {
        return getItem(index, null);
    }

    @Override
    public Item getItem(int index, Item fallback) {
        Item item = null;
        synchronized (itemList) {
            if (0 <= index && index < itemList.size()) {
                item = itemList.get(index);
            }
        }
        return item != null ? item : fallback;
    }

    @Override
    public List<Item> getItems() {
        return getItems(null);
    }

    @Override
    public List<Item> getItems(Filter<Item> filter) {
        List<Item> list = new ArrayList<>();
        synchronized (itemList) {
            if (filter == null) {
                list.addAll(itemList);
            } else {
                for (Item item : itemList) {
                    if (!filter.accept(item)) {
                        continue;
                    }
                    list.add(item);
                }
            }
        }
        return list;
    }

    @Override
    public void addItem(Item item) {
        if (item == null) {
            return;
        }
        synchronized (itemList) {
            if (itemList.contains(item)) {
                return;
            }
            itemList.add(item);
        }
    }

    @Override
    public void addItems(Collection<Item> collection) {
        if (collection == null) {
            return;
        }
        synchronized (itemList) {
            for (Item item : collection) {
                if (item == null || itemList.contains(item)) {
                    continue;
                }
                itemList.add(item);
            }
        }
    }

    @Override
    public Item removeItem(int index) {
        synchronized (itemList) {
            if (index < 0 || itemList.size() <= index) {
                return null;
            }
            return itemList.remove(index);
        }
    }

    @Override
    public void removeItem(Item item) {
        if (item == null) {
            return;
        }
        synchronized (itemList) {
            itemList.remove(item);
        }
    }

    @Override
    public void removeItems(Collection<Item> collection) {
        if (collection == null) {
            return;
        }
        synchronized (itemList) {
            for (Item item : collection) {
                if (item == null) {
                    continue;
                }
                itemList.remove(item);
            }
        }
    }

    @Override
    public void clearItems() {
        synchronized (itemList) {
            itemList.clear();
        }
    }
}
