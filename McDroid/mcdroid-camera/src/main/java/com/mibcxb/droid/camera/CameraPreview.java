package com.mibcxb.droid.camera;

import android.content.Context;
import android.os.Build;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.mibcxb.droid.camera.impl.CameraControllerImpl;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * A basic Camera preview class
 */
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
    public interface ScanCallback {

    }

    private SurfaceHolder mHolder;
    private final CameraController mController;
    private Disposable scanDisposable;

    public CameraPreview(Context context) {
        super(context);
        mController = Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP ?
                new CameraControllerImpl(context) : new CameraControllerImpl(context);

        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        mHolder = getHolder();
        mHolder.addCallback(this);
        // deprecated setting, but required on Android versions prior to 3.0
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        // The Surface has been created, now tell the camera where to draw the preview.
        mController.startPreview(holder);
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // empty. Take care of releasing the Camera preview in your activity.
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        // If your preview can change or rotate, take care of those events here.
        // Make sure to stop the preview before resizing or reformatting it.

        if (mHolder.getSurface() == null) {
            // preview surface does not exist
            return;
        }

        // stop preview before making changes
        mController.stopPreview();

        // set preview size and make any resize, rotate or
        // reformatting changes here

        // start preview with new settings
        mController.startPreview(holder);


    }

    public CameraController getController() {
        return mController;
    }

    public void prepareCamera() {
        mController.prepareCamera();
    }

    public void resumePreview() {
        mController.startPreview(mHolder);
    }

    public void releaseCamera() {
        mController.releaseCamera();
    }

    public void setDisplayOrientation(int orientation) {
        mController.setDisplayOrientation(orientation);
    }

    public void scanQrCode(ScanCallback callback) {
        if (scanDisposable == null || scanDisposable.isDisposed()) {
            scanDisposable = Observable.timer(1, TimeUnit.SECONDS)
                    .flatMap(new Function<Long, ObservableSource<CameraController.PictureData>>() {
                        @Override
                        public ObservableSource<CameraController.PictureData> apply(Long aLong)
                                throws Exception {
                            return mController.takePicture();
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            new Consumer<CameraController.PictureData>() {
                                @Override
                                public void accept(CameraController.PictureData pictureData) throws Exception {
                                    resumePreview();
                                }
                            },
                            new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    throwable.printStackTrace();
                                }
                            }
                    );
        }
    }

    public void stopScan() {
        if (scanDisposable != null && !scanDisposable.isDisposed()) {
            scanDisposable.dispose();
            scanDisposable = null;
        }
    }
}
