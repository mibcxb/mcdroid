package com.mibcxb.droid.arch.mvp.sample;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mibcxb.droid.arch.mvp.BaseActivity;

public class SampleActivity extends BaseActivity<SampleContract.Presenter> implements SampleContract.View {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new SamplePresenter(this);
    }
}
