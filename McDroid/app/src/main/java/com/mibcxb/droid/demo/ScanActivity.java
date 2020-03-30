package com.mibcxb.droid.demo;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.mibcxb.droid.camera.CameraFragment;
import com.mibcxb.droid.camera.QrScanView;

public class ScanActivity extends AppCompatActivity {
    private CameraFragment mCameraFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.fl_container, obtainCameraFragment())
//                .commit();

        QrScanView qrScanView = new QrScanView(this);
        qrScanView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        FrameLayout qrLayout = findViewById(R.id.fl_qr_scan);
        qrLayout.addView(qrScanView,300,300);
        qrScanView.setVisibility(View.VISIBLE);
    }

    private CameraFragment obtainCameraFragment() {
        if (mCameraFragment == null) {
            mCameraFragment = new CameraFragment();
            Bundle arguments = new Bundle();
            arguments.putBoolean(CameraFragment.ARG_ROTATE_DISPLAY, true);
            arguments.putBoolean(CameraFragment.ARG_AUTO_FOCUS, true);
            mCameraFragment.setArguments(arguments);
        }
        return mCameraFragment;
    }
}
