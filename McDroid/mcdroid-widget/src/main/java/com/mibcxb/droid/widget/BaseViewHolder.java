package com.mibcxb.droid.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class BaseViewHolder<Item extends BaseItem> extends RecyclerView.ViewHolder {

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        initView(itemView);
    }

    public void initView(@NonNull View view) {
    }

    public void bindView(@NonNull Item item) {
    }

    protected Context getContext() {
        return itemView.getContext();
    }
}
