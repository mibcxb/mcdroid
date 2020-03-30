package com.mibcxb.droid.arch.mvp;

import android.content.Context;

import androidx.appcompat.app.AppCompatDialog;

public class BaseDialog<Presenter extends BasePresenter> extends AppCompatDialog {
    protected Presenter presenter;

    public BaseDialog(Context context) {
        super(context);
    }

    public BaseDialog(Context context, int theme) {
        super(context, theme);
    }

    public BaseDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (presenter != null) {
            presenter.prepare();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.release();
        }
    }
}
