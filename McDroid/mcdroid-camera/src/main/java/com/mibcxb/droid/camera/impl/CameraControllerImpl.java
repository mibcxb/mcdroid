package com.mibcxb.droid.camera.impl;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

import static com.mibcxb.droid.core.McDroidLog.logger;
import static com.mibcxb.droid.core.McDroidLog.printE;

/**
 * Use this controller when api code < 21
 */
public class CameraControllerImpl extends CameraControllerBase {

    private Camera mCamera;
    private boolean mAutoFocusEnabled;
    private AtomicBoolean isPreviewStarted = new AtomicBoolean();

    public CameraControllerImpl(Context context) {
        super(context);
    }

    @Override
    public int getCameraCount() {
        return Camera.getNumberOfCameras();
    }

    @Override
    public void prepareCamera() {
        if (mCamera == null) {
            try {
                mCamera = Camera.open();
            } catch (Exception e) {
                printE(e);
            }
        }
    }

    @Override
    public void startPreview(SurfaceHolder surfaceHolder) {
        if (mCamera == null || surfaceHolder == null) {
            return;
        }
        try {
            mCamera.setPreviewDisplay(surfaceHolder);
            mCamera.startPreview();
            isPreviewStarted.set(true);
            enableAutoFocus();
        } catch (IOException e) {
            logger().info("Error setting camera preview: " + e.getMessage());
        }
    }

    @Override
    public void stopPreview() {
        if (mCamera == null) {
            return;
        }
        try {
            cancelAutoFocus();
            mCamera.stopPreview();
            isPreviewStarted.set(false);
        } catch (Exception e) {
            logger().trace(e.getMessage(), e);
        }
    }

    @Override
    public void releaseCamera() {
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
    }

    @Override
    public void setDisplayOrientation(int orientation) {
        if (mCamera != null) {
            mCamera.setDisplayOrientation(orientation);
        }
    }

    @Override
    public Observable<PictureData> takePicture() {
        return Observable.create(new ObservableOnSubscribe<PictureData>() {
            @Override
            public void subscribe(ObservableEmitter<PictureData> emitter) throws Exception {
                if (mCamera == null) {
                    throw new RuntimeException("Camera is not ready for taking a picture.");
                }
                mCamera.takePicture(
                        new Camera.ShutterCallback() {
                            @Override
                            public void onShutter() {
                                logger().info("onShutter: {}", mCamera);
                            }
                        },
                        new Camera.PictureCallback() {
                            @Override
                            public void onPictureTaken(byte[] data, Camera camera) {
                                int length = data == null ? -1 : data.length;
                                logger().info("onPictureTaken: RAW={}, {}", length, mCamera);
                            }
                        },
                        new Camera.PictureCallback() {
                            @Override
                            public void onPictureTaken(byte[] data, Camera camera) {
                                int length = data == null ? -1 : data.length;
                                logger().info("onPictureTaken: PostView={}, {}", length, mCamera);
                            }
                        },
                        new Camera.PictureCallback() {
                            @Override
                            public void onPictureTaken(byte[] data, Camera camera) {
                                int length = data == null ? -1 : data.length;
                                logger().info("onPictureTaken: JPEG={}, {}", length, mCamera);
                            }
                        });
            }
        });
    }

    @Override
    public boolean isAutoFocusSupported() {
        if (mCamera != null) {
            Camera.Parameters params = mCamera.getParameters();
            List<String> focusModes = params.getSupportedFocusModes();
            return focusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        }
        return false;
    }

    @Override
    public void setAutoFocusEnabled(boolean enabled) {
        if (mCamera == null) {
            return;
        }
        mAutoFocusEnabled = enabled;
        enableAutoFocus();
    }

    private void enableAutoFocus() {
        if (mAutoFocusEnabled && isAutoFocusSupported() && isPreviewStarted.get()) {
            Camera.Parameters params = mCamera.getParameters();
            params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
            mCamera.setParameters(params);
            mCamera.autoFocus(autoFocusCallback);
        }
    }

    private void cancelAutoFocus() {
        mCamera.cancelAutoFocus();
    }

    private Camera.AutoFocusCallback autoFocusCallback = new Camera.AutoFocusCallback() {
        @Override
        public void onAutoFocus(boolean success, Camera camera) {
            cancelAutoFocus();
        }
    };
}
