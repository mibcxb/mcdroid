package com.mibcxb.droid.widget;

import android.support.annotation.IntRange;

public abstract class StateItemAdapter<Item extends StateItem> extends BaseItemAdapter<Item> {
    private boolean multiCheckEnabled;
    private int multiCheckLimit = -1;

    public boolean isMultiCheckEnabled() {
        return multiCheckEnabled;
    }

    public void setMultiCheckEnabled(boolean enabled) {
        this.multiCheckEnabled = enabled;
    }

    public int getMultiCheckLimit() {
        return multiCheckLimit;
    }

    public void setMultiCheckLimit(int limit) {
        this.multiCheckLimit = limit;
    }

    public void checkItem(@IntRange(from = 0) int position) {
        if (position >= getCount()) {
            return;
        }
        if (multiCheckEnabled) {
            int checkedCount = getCheckedCount();
            Item item = getItem(position);
            if (!item.isChecked()) {
                if (multiCheckLimit < 0 || checkedCount < multiCheckLimit) {
                    item.setState(StateItem.ST_CHECKED);
                }
            } else {
                item.setState(StateItem.ST_DEFAULT);
            }
        } else {
            uncheckAll();
            getItem(position).setState(StateItem.ST_CHECKED);
        }
    }

    public void checkAll() {
        if (!multiCheckEnabled) {
            return;
        }
        if (multiCheckLimit >= 0) {
            return;
        }
        for (int i = 0; i < getCount(); i++) {
            getItem(i).setState(StateItem.ST_CHECKED);
        }
    }

    public void uncheckAll() {
        for (int i = 0; i < getCount(); i++) {
            getItem(i).setState(StateItem.ST_DEFAULT);
        }
    }

    public int getCheckedCount() {
        int count = 0;
        for (Item item : getItemList()) {
            if (item.isChecked()) {
                count++;
            }
        }
        return count;
    }
}
