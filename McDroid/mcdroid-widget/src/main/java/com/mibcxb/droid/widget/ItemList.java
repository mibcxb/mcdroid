package com.mibcxb.droid.widget;

import java.util.Collection;
import java.util.List;

public interface ItemList<Item> {
    interface Filter<Item> {
        boolean accept(Item item);
    }

    boolean isEmpty();

    int size();

    int indexOf(Item item);

    Item getItem(int index);

    Item getItem(int index, Item fallback);

    List<Item> getItems();

    List<Item> getItems(Filter<Item> filter);

    void addItem(Item item);

    void addItems(Collection<Item> collection);

    Item removeItem(int index);

    void removeItem(Item item);

    void removeItems(Collection<Item> collection);

    void clearItems();
}
