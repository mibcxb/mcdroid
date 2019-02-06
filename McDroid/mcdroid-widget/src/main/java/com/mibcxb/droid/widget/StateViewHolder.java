package com.mibcxb.droid.widget;

import android.support.annotation.NonNull;
import android.view.View;

public class StateViewHolder<Item extends StateItem> extends BaseViewHolder<Item> {

    public StateViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public final void bindView(@NonNull Item item) {
        bindView(item, item.getState());
    }


    public void bindView(@NonNull Item item, int state) {
    }
}
