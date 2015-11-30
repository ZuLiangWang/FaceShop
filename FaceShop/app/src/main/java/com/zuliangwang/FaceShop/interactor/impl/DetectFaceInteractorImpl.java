package com.zuliangwang.FaceShop.interactor.impl;

import android.graphics.Bitmap;
import android.graphics.Path;
import android.graphics.PointF;

import com.zuliangwang.FaceShop.bean.FacePositionModel;
import com.zuliangwang.FaceShop.interactor.DetectFaceInteractor;
import com.zuliangwang.FaceShop.utils.bitmapcontroller.BitmapClipMaster;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zuliangwang on 15/11/26.
 */
public class DetectFaceInteractorImpl implements DetectFaceInteractor {

    private Bitmap mSrc;
    private FacePositionModel model;
    private List<PointF> pointList;

    public DetectFaceInteractorImpl(FacePositionModel model,Bitmap src) {
        this.model = model;
        this.mSrc = src;
        pointList = new ArrayList<>();
    }


    @Override
    public Bitmap getFace() {
        pointList.clear();
        for (int i=0;i<model.getFace_shape().get(0).getFace_profile().size();i++){
            PointF pointF = new PointF(model.getFace_shape().get(0).getFace_profile().get(i).getX(),model.getFace_shape().get(0).getFace_profile().get(i).getY());
            pointList.add(pointF);
        }
        Path path = BitmapClipMaster.getBezierPath(pointList);


        return BitmapClipMaster.clipBitmap(mSrc,path);
    }

    @Override
    public Bitmap getLeftEye() {
        return null;
    }

    @Override
    public Bitmap getRightEye() {
        return null;
    }

    @Override
    public Bitmap getLeftEyebrew() {
        return null;
    }

    @Override
    public Bitmap getRightEyebrew() {
        return null;
    }

    @Override
    public Bitmap getNose() {
        return null;
    }

    @Override
    public Bitmap getMouse() {
        return null;
    }
}
