package com.mibcxb.droid.camera;

import android.view.SurfaceHolder;

import io.reactivex.Observable;

public interface CameraController {
    boolean checkCameraHardware();

    int getCameraCount();

    void prepareCamera();

    void startPreview(SurfaceHolder surfaceHolder);

    void stopPreview();

    void releaseCamera();

    void setDisplayOrientation(int orientation);

    boolean isAutoFocusSupported();

    void setAutoFocusEnabled(boolean enabled);

    Observable<PictureData> takePicture();

    class PictureData {
        public static final int TYPE_RAW = 0;
        public static final int TYPE_POSTVIEW = 1;
        public static final int TYPE_JPEG = 2;

        public final int type;
        public final byte[] data;

        public PictureData(int type, byte[] data) {
            this.type = type;
            this.data = data;
        }

        @Override
        public String toString() {
            return "PictureData{" +
                    "type=" + type +
                    ", data=" + data.length +
                    '}';
        }
    }
}
