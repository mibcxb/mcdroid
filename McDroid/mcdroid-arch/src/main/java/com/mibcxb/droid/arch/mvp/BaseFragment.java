package com.mibcxb.droid.arch.mvp;

import androidx.fragment.app.Fragment;

public abstract class BaseFragment<Presenter extends BasePresenter> extends Fragment {
    protected Presenter presenter;

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (presenter != null) {
            presenter.prepare();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.release();
        }
    }
}
