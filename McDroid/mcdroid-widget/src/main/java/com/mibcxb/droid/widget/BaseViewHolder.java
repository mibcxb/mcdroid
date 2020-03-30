package com.mibcxb.droid.widget;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
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
