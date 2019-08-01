package com.mibcxb.droid.camera;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.mibcxb.droid.core.McDroidLog.logger;


public class QrScanView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private Disposable disposable;
    private int framePerSecond = 30;

    private long oneFrameTime = 1000L / framePerSecond;
    private boolean running = false;

    private SurfaceHolder mHolder;
    private Paint mPaint;

    public QrScanView(Context context) {
        super(context);

        mHolder = getHolder();
        mHolder.addCallback(this);
        // deprecated setting, but required on Android versions prior to 3.0
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        running = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            logger().error("AAAAAA {}", System.currentTimeMillis());
            synchronized (mHolder) {
                Canvas canvas = mHolder.lockCanvas();
                canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

                mPaint.setColor(0xFF000000);
                canvas.drawCircle(100, 100, 50, mPaint);

                mHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    private Disposable createDrawScanObservable() {
        return Observable.timer(oneFrameTime, TimeUnit.MILLISECONDS, Schedulers.newThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        logger().error("AAAAAA {}", aLong);
                        synchronized (mHolder) {
                            Canvas canvas = mHolder.lockCanvas();
                            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

                            mPaint.setColor(0xFF000000);
                            canvas.drawCircle(100, 100, 50, mPaint);

                            mHolder.unlockCanvasAndPost(canvas);
                        }
                    }
                });
    }
}
