package com.mibcxb.droid.glide;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;

import java.security.MessageDigest;

public class CircleImageTransformation extends BitmapTransformation {
    private final int strokeColor;
    private final int strokeWidth;

    public CircleImageTransformation() {
        this(0, 0);
    }

    public CircleImageTransformation(int strokeColor, int strokeWidth) {
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform,
                               int outWidth, int outHeight) {
        if (strokeWidth > 0) {
            int imageSize = (outWidth < outHeight ? outWidth : outHeight) - (strokeWidth << 1);
            if (imageSize > 0) {
                Bitmap imgBmp = TransformationUtils.circleCrop(pool, toTransform, imageSize, imageSize);
                Bitmap result = pool.get(outWidth, outHeight, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(result);
                Paint paint = new Paint();
                paint.setColor(strokeColor);
                paint.setAntiAlias(true);
                int centerX = outWidth >> 1;
                int centerY = outHeight >> 1;
                int radius = outWidth < outHeight ? centerX : centerY;
                canvas.drawCircle(centerX, centerY, radius, paint);
                canvas.drawBitmap(imgBmp, centerX - (radius - strokeWidth),
                        centerY - (radius - strokeWidth), paint);
                return result;
            }
        }
        return TransformationUtils.circleCrop(pool, toTransform, outWidth, outHeight);
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
    }
}
