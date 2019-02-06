package com.mibcxb.droid.camera;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class CameraFragment extends Fragment {
    public static final String ARG_ROTATE_DISPLAY = "arg-rotate-display";
    public static final String ARG_AUTO_FOCUS = "arg-auto-focus";

    private CameraPreview mPreview;
    private boolean rotateDisplayEnabled;
    private boolean autoFocusEnabled;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        if (arguments != null) {
            rotateDisplayEnabled = arguments.getBoolean(ARG_ROTATE_DISPLAY, true);
            autoFocusEnabled = arguments.getBoolean(ARG_AUTO_FOCUS, false);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mPreview = new CameraPreview(getContext());
        return mPreview;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPreview.prepareCamera();
        mPreview.resumePreview();
        setupWithArgs(mPreview);

        mPreview.scanQrCode(new CameraPreview.ScanCallback() {
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        mPreview.releaseCamera();
    }

    private void setupWithArgs(final CameraPreview preview) {
        if (rotateDisplayEnabled) {
            FragmentActivity activity = getActivity();
            int rotation = CameraHelper.getDisplayRotation(activity);
            preview.setDisplayOrientation(rotation);
        }
        preview.getController().setAutoFocusEnabled(autoFocusEnabled);
    }
}
