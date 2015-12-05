package com.zuliangwang.FaceShop.utils.bitmapcontroller;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
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
        int PROGRESS = 100;
        float contrast = (float) (( PROGRESS+ 64) / 128.0);

        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(new float[] {contrast,0,0,0,0,0
        ,contrast,0,0,0,
        0,0,contrast,0,0,0,0,0,1,0});

        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(srcBitmap,0,0,paint);

        return result;
    }

    public static Bitmap toHeibai(Bitmap mBitmap)
    {
        int mBitmapWidth = 0;
        int mBitmapHeight = 0;

        mBitmapWidth = mBitmap.getWidth();
        mBitmapHeight = mBitmap.getHeight();
        Bitmap bmpReturn = Bitmap.createBitmap(mBitmapWidth, mBitmapHeight,
                Bitmap.Config.ARGB_8888);
        int iPixel = 0;
        for (int i = 0; i < mBitmapWidth; i++)
        {
            for (int j = 0; j < mBitmapHeight; j++)
            {
                int curr_color = mBitmap.getPixel(i, j);

                int avg = (Color.red(curr_color) + Color.green(curr_color) + Color
                        .blue(curr_color)) / 3;
                if (avg >= 100)
                {
                    iPixel = 255;
                }
                else
                {
                    iPixel = 0;
                }
                int modif_color = Color.argb(255, iPixel, iPixel, iPixel);

                bmpReturn.setPixel(i, j, modif_color);
            }
        }
        return bmpReturn;
    }


    public static Bitmap switchColor(Bitmap switchBitmap){
        int width=switchBitmap.getWidth();
        int height=switchBitmap.getHeight();

        // Turn the picture black and white
//      Bitmap newBitmap=Bitmap.createBitmap(width,height,Bitmap.Config.RGB_565);
        Bitmap newBitmap=Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(newBitmap);
        canvas.drawBitmap(switchBitmap, new Matrix(), new Paint());

        int current_color,red,green,blue,alpha,avg=0;
        for (int i=0;i<width;i++){
            for (int j=0;j<height;j++){
                //获得每一个位点的颜色
                current_color=switchBitmap.getPixel(i, j);
                //获得三原色
                red= Color.red(current_color);
                green=Color.green(current_color);
                blue=Color.blue(current_color);
                alpha=Color.alpha(current_color);
                avg=(red+green+blue)/3;// RGB average
                if (avg>=210){  //亮度：avg>=126
                    //设置颜色
                    newBitmap.setPixel(i, j, Color.argb(alpha, 255, 255, 255));// white
                } else if (avg<210 && avg>=80){  //avg<126 && avg>=115
                    newBitmap.setPixel(i, j, Color.argb(alpha, 108,108,108));//grey
                }else{
                    newBitmap.setPixel(i, j, Color.argb(alpha, 0, 0, 0));// black
                }
            }
        }
        return newBitmap;
    }
}
