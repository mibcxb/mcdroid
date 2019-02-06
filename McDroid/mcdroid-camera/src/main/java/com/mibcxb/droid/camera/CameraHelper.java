package com.mibcxb.droid.camera;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.view.Surface;

public final class CameraHelper {
    /**
     * Check if this device has a camera
     */
    public static boolean checkCameraHardware(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    public static int getDisplayRotation(Activity activity) {
        if (activity != null) {
            int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
            switch (rotation) {
                case Surface.ROTATION_0:
                    return 90;
                case Surface.ROTATION_90:
                    return 180;
                case Surface.ROTATION_180:
                    return 270;
                case Surface.ROTATION_270:
                    return 0;
            }
        }
        return 0;
    }
}
