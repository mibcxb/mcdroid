package com.mibcxb.droid.arch.mvp.sample;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mibcxb.droid.arch.mvp.BaseFragment;

public class SampleFragment extends BaseFragment<SampleContract.Presenter> implements SampleContract.View {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        new SamplePresenter(this);
    }
}
