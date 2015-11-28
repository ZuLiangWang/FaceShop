package com.zuliangwang.FaceShop.utils.bitmapcontroller;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

/**
 * Created by zuliangwang on 15/11/28.
 */
public class BitmapRich {

    public static Bitmap toGrayscale(Bitmap srcBitmap){
        int width,height;
        height = srcBitmap.getHeight();
        width = srcBitmap.getWidth();

        Bitmap result = Bitmap.createBitmap(width,height,Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(result);

        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();

        colorMatrix.setSaturation(0);
        ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(colorMatrix);
        paint.setColorFilter(colorMatrixColorFilter);
        canvas.drawBitmap(srcBitmap,0,0,paint);

        return result;
    }

    public static Bitmap changeContrast(Bitmap srcBitmap){
        int width,height;
        height = srcBitmap.getHeight();
        width = srcBitmap.getWidth();

        Bitmap result = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);

        Paint paint = new Paint();
        //通过调整系数来改变对比度
        int PROGRESS = 200;
        float contrast = (float) (( PROGRESS+ 64) / 128.0);

        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(new float[] {contrast,0,0,0,0,0
        ,contrast,0,0,0,
        0,0,contrast,0,0,0,0,0,1,0});

        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(srcBitmap,0,0,paint);

        return result;
    }
}
