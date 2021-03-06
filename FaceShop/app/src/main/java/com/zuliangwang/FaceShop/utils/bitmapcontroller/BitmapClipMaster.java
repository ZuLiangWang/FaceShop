package com.zuliangwang.FaceShop.utils.bitmapcontroller;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Region;

import java.util.List;

/**
 * Created by zuliangwang on 15/10/22.
 */
//1.canvas的参数resultBitmap 对canvas的任何操作最后都将保存到resultBitmap中
//2.canvas按照path裁剪了一些不需要显示的部分
public class BitmapClipMaster {

    public static float CTRL_POINT_RATIO = 0.125f;


    public static Bitmap clipBitmap(Bitmap srcBitmap,Path path){
//        Bitmap pathBitmap = Bitmap.createBitmap(srcBitmap.getWidth(),srcBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Bitmap resultBitmap = Bitmap.createBitmap(srcBitmap.getWidth(),srcBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(resultBitmap);
        canvas.clipPath(path, Region.Op.REPLACE);
        canvas.drawBitmap(srcBitmap,0,0,new Paint());
        return resultBitmap;
    }


    //返回的是奖两张图合并之后的新图
    public static Bitmap compose(Bitmap bgImage,Bitmap frontImage,float top,float left){
        Bitmap result = bgImage.copy(Bitmap.Config.ARGB_8888, true);
//        Bitmap result = Bitmap.createBitmap(bgImage.getWidth(),bgImage.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(result);


        canvas.drawBitmap(bgImage,0,0,new Paint());
        canvas.drawBitmap(frontImage, left, top, new Paint());
        canvas.save();

        return result;
    }



    public static Path getBezierPath(List<PointF> points) {
        Path path = new Path();

        if (points.size() < 2) {
            return path;
        }

        path.moveTo(points.get(0).x, points.get(0).y);

        for (int i = 0; i < points.size() - 1; i++) {
            PointF ctrlPointFirst =
                    getFirstCtrlPoint(points, i, CTRL_POINT_RATIO);
            PointF ctrlPointSecond =
                    getSecondCtrlPoint(points, i, CTRL_POINT_RATIO);
            path.cubicTo(
                    ctrlPointFirst.x, ctrlPointFirst.y,
                    ctrlPointSecond.x, ctrlPointSecond.y,
                    points.get(i + 1).x, points.get(i + 1).y);
        }

        return path;
    }

    public static PointF getFirstCtrlPoint(List<PointF> points, int index, float ratio) {
        if (index < 0 || index >= points.size() - 1) {
            throw new IllegalArgumentException();
        }

        float X;
        float Y;

        if (index == 0) {
            X = points.get(index).x + (points.get(index + 1).x - points.get(index).x) / 4;
            Y = points.get(index).y + (points.get(index + 1).y - points.get(index).y) / 4;
        } else {
            X = points.get(index).x + (points.get(index + 1).x - points.get(index - 1).x) * ratio;
            Y = points.get(index).y + (points.get(index + 1).y - points.get(index - 1).y) * ratio;
        }
        return new PointF(X, Y);
    }

    public static PointF getSecondCtrlPoint(List<PointF> points, int index, float ratio) {
        if (index < 0 || index >= points.size() - 1) {
            throw new IllegalArgumentException();
        }

        float X;
        float Y;

        if (index == points.size() - 2) {
            X = points.get(index + 1).x - (points.get(index + 1).x - points.get(index).x) / 4;
            Y = points.get(index + 1).y - (points.get(index + 1).y - points.get(index).y) / 4;
        } else {
            X = points.get(index + 1).x - (points.get(index + 2).x - points.get(index).x) * ratio;
            Y = points.get(index + 1).y - (points.get(index + 2).y - points.get(index).y) * ratio;
        }
        return new PointF(X, Y);
    }


    /**
     * @param res Resource
     * @param resId 资源id
     * @param targetWidth 目标图片的宽，单位px
     * @param targetHeight 目标图片的高，单位px
     * @return 返回压缩后的图片的Bitmap
     */
    public static Bitmap compressBitmap(Resources res, int resId, int targetWidth, int targetHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//设为true，节约内存
        BitmapFactory.decodeResource(res, resId, options);//返回null
        int height = options.outHeight;//得到源图片height，单位px
        int width = options.outWidth;//得到源图片的width，单位px
        //计算inSampleSize
        options.inSampleSize = calculateInSampleSize(width,height,targetWidth,targetHeight);
        options.inJustDecodeBounds = false;//设为false，可以返回Bitmap
        return BitmapFactory.decodeResource(res,resId,options);
    }

    /**
     * 计算压缩比例
     * @param width  源图片的宽
     * @param height 源图片的高
     * @param targetWidth  目标图片的宽
     * @param targetHeight 目标图片的高
     * @return inSampleSize 压缩比例
     */
    public static int calculateInSampleSize(int width,int height, int targetWidth, int targetHeight) {
        int inSampleSize = 1;
        if (height > targetHeight || width > targetWidth) {
            //计算图片实际的宽高和目标图片宽高的比率
            final int heightRate = Math.round((float) height / (float) targetHeight);
            final int widthRate = Math.round((float) width / (float) targetWidth);
            //选取最小的比率作为inSampleSize
            inSampleSize = heightRate < widthRate ? heightRate : widthRate;
        }
        return inSampleSize;
    }

    public static Bitmap realCrop(Bitmap bitmap,int minx,int miny,int maxx,int maxy){
        return Bitmap.createBitmap(bitmap,minx,miny,maxx-minx,maxy-miny);
    }
}
