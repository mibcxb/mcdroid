package com.mibcxb.droid.widget;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collection;
import java.util.List;

public abstract class BaseItemAdapter<Item extends BaseItem, VH extends BaseViewHolder<Item>>
        extends RecyclerView.Adapter<VH> implements BaseItemList<Item> {
    private final BaseItemList<Item> itemList = new SafeBaseItemList<>();

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

    @Override
    public int getItemCount() {
        return size();
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Item item = getItem(position);
        if (item == null) {
            return;
        }
        holder.bindView(item);
    }

}
