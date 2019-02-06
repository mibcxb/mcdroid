package com.mibcxb.droid.camera.impl;

import android.content.Context;

import com.mibcxb.droid.camera.CameraController;
import com.mibcxb.droid.camera.CameraHelper;

public abstract class CameraControllerBase implements CameraController {

    protected final Context mContext;

    CameraControllerBase(Context context) {
        this.mContext = context;
    }

    @Override
    public boolean checkCameraHardware() {
        return CameraHelper.checkCameraHardware(mContext);
    }
}
