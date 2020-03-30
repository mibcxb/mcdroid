package com.mibcxb.droid.arch.mvp.sample;

import com.mibcxb.droid.arch.mvp.BasePresenter;
import com.mibcxb.droid.arch.mvp.BaseView;

public interface SampleContract {
    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter {
    }
}
