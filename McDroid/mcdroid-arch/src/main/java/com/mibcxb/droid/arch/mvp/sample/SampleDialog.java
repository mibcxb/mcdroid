package com.mibcxb.droid.arch.mvp.sample;

import android.content.Context;
import android.os.Bundle;

import com.mibcxb.droid.arch.mvp.BaseDialog;

public class SampleDialog extends BaseDialog<SampleContract.Presenter> implements SampleContract.View {
    public SampleDialog(Context context) {
        super(context);
    }

    public SampleDialog(Context context, int theme) {
        super(context, theme);
    }

    public SampleDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new SamplePresenter(this);
    }
}
