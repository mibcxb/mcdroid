package com.mibcxb.droid.widget;

public interface BaseItemList<Item extends BaseItem> extends ItemList<Item> {

    Item getItem(String uuid);

    Item getItem(String uuid, Item fallback);

    Item removeItem(String uuid);
}
