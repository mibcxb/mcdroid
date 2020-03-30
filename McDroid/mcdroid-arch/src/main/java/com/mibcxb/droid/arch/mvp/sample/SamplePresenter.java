package com.mibcxb.droid.arch.mvp.sample;

public class SamplePresenter implements SampleContract.Presenter {
    protected final SampleContract.View view;

    SamplePresenter(SampleContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void prepare() {

    }

    @Override
    public void release() {

    }
}
