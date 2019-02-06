package com.mibcxb.droid.camera;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.hardware.SensorManager;
import android.view.OrientationEventListener;

public class CameraOrientationDetector extends OrientationEventListener {
    public interface Listener {
        void onOrientationChanged(int orientation);
    }

    private Listener listener;
    private Resources resources;
    private int previous;

    public CameraOrientationDetector(Context context) {
        this(context, null);
    }

    public CameraOrientationDetector(Context context, Listener listener) {
        super(context, SensorManager.SENSOR_DELAY_NORMAL);
        this.listener = listener;
        this.resources = context.getResources();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void onOrientationChanged(int orientation) {
        int current = normalizeOrientation(orientation);
        if (isOrientationPortrait()) {
            if (current == 90 || current == 180) {
                notifyViewOrientationChanged(current);
            }
        } else {
            if (current == 0 || current == 270) {
                notifyViewOrientationChanged(current);
            }
        }
    }

    private void notifyViewOrientationChanged(int orientation) {
        if (orientation != previous) {
            previous = orientation;
            if (listener != null) {
                listener.onOrientationChanged(orientation);
            }
        }
    }

    private boolean isOrientationPortrait() {
        return getScreenOrientation() == Configuration.ORIENTATION_PORTRAIT;
    }

    private int getScreenOrientation() {
        return resources.getConfiguration().orientation;
    }

    public static int normalizeOrientation(int orientation) {
        int normalized = ((orientation / 45 + 1) >> 1) % 4;
        switch (normalized) {
            case 1:
                return 180;
            case 2:
                return 270;
            case 3:
                return 0;
            default:
                return 90;
        }
    }
}
