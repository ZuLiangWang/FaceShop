package com.zuliangwang.FaceShop.interactor;

import android.graphics.Bitmap;

import com.zuliangwang.FaceShop.bean.FacePositionModel;

/**
 * Created by zuliangwang on 15/11/5.
 */
public interface DetectFaceInteractor {

    public Bitmap getFace();

    public Bitmap getLeftEye();

    public Bitmap getRightEye();

    public Bitmap getLeftEyebrow();

    public Bitmap getRightEyebrow();

    public Bitmap getNose();

    public Bitmap getMouse();

    public Bitmap getFeatures();

    public Bitmap getSmallFeatures(Bitmap features);

}
