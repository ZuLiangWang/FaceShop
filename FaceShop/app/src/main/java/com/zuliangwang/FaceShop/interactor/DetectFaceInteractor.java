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

    public Bitmap getLeftEyebrew();

    public Bitmap getRightEyebrew();

    public Bitmap getNose();

    public Bitmap getMouse();

}
