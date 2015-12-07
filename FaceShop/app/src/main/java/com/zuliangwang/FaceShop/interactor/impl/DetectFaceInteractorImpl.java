package com.zuliangwang.FaceShop.interactor.impl;

import android.graphics.Bitmap;
import android.graphics.Path;
import android.graphics.PointF;

import com.zuliangwang.FaceShop.bean.FacePositionModel;
import com.zuliangwang.FaceShop.interactor.DetectFaceInteractor;
import com.zuliangwang.FaceShop.utils.bitmapcontroller.BitmapClipMaster;
import com.zuliangwang.FaceShop.utils.bitmapcontroller.BitmapRich;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zuliangwang on 15/11/26.
 */
public class DetectFaceInteractorImpl implements DetectFaceInteractor {

    private Bitmap mSrc;
    private FacePositionModel model;
    private List<PointF> pointList;
    float maxX;
    float maxY;
    float minX;
    float minY;

    private List<PointF> featuresList;

    public DetectFaceInteractorImpl(FacePositionModel model,Bitmap src) {
        this.model = model;
        this.mSrc = src;
        pointList = new ArrayList<PointF>();

        featuresList = new ArrayList<PointF>();
    }


    @Override
    public Bitmap getFace() {
        pointList.clear();

        for (int i=0;i<model.getFace_shape().get(0).getFace_profile().size();i++){
            PointF pointF = new PointF(model.getFace_shape().get(0).getFace_profile().get(i).getX(),model.getFace_shape().get(0).getFace_profile().get(i).getY());
            pointList.add(pointF);
        }



        PointF right0 = new PointF(model.getFace_shape().get(0).getRight_eyebrow().get(0).getX(),model.getFace_shape().get(0).getRight_eyebrow().get(0).getY());
        PointF right7 = new PointF(model.getFace_shape().get(0).getRight_eyebrow().get(7).getX(),model.getFace_shape().get(0).getRight_eyebrow().get(7).getY());
        PointF right6 = new PointF(model.getFace_shape().get(0).getRight_eyebrow().get(6).getX(),model.getFace_shape().get(0).getRight_eyebrow().get(6).getY());
        PointF right5 = new PointF(model.getFace_shape().get(0).getRight_eyebrow().get(5).getX(),model.getFace_shape().get(0).getRight_eyebrow().get(5).getY());
        PointF right4 = new PointF(model.getFace_shape().get(0).getRight_eyebrow().get(4).getX(),model.getFace_shape().get(0).getRight_eyebrow().get(4).getY());

        PointF left4 = new PointF(model.getFace_shape().get(0).getLeft_eyebrow().get(4).getX(),model.getFace_shape().get(0).getLeft_eyebrow().get(4).getY());
        PointF left5 = new PointF(model.getFace_shape().get(0).getLeft_eyebrow().get(5).getX(),model.getFace_shape().get(0).getLeft_eyebrow().get(5).getY());
        PointF left6 = new PointF(model.getFace_shape().get(0).getLeft_eyebrow().get(6).getX(),model.getFace_shape().get(0).getLeft_eyebrow().get(6).getY());
        PointF left7 = new PointF(model.getFace_shape().get(0).getLeft_eyebrow().get(7).getX(),model.getFace_shape().get(0).getLeft_eyebrow().get(7).getY());
        PointF left0 = new PointF(model.getFace_shape().get(0).getLeft_eyebrow().get(0).getX(),model.getFace_shape().get(0).getLeft_eyebrow().get(0).getY());


        pointList.add(right0);
        pointList.add(right7);
        pointList.add(right6);
        pointList.add(right5);
        pointList.add(right4);
        pointList.add(left4);
        pointList.add(left5);
        pointList.add(left6);
        pointList.add(left7);
        pointList.add(left0);

        maxX = 0;
        maxY = 0;
        for (int i =0;i<pointList.size();i++){
            if (pointList.get(i).x >= maxX){
                maxX = pointList.get(i).x;
            }
            if (pointList.get(i).y >= maxY){
                maxY = pointList.get(i).y;
            }
        }

        minX = 10000;
        minY = 10000;
        for (int i =0;i<pointList.size();i++){
            if (pointList.get(i).x <= minX){
                minX = pointList.get(i).x;
            }
            if (pointList.get(i).y <= minY){
                minY = pointList.get(i).y;
            }
        }



        Path path = BitmapClipMaster.getBezierPath(pointList);



        Bitmap clipBitmap =  BitmapClipMaster.clipBitmap(mSrc, path);

        int iMinX = (int) minX;
        int iMinY = (int) minY;
        int iMaxX = (int) maxX;
        int iMaxY = (int) maxY;
      return   Bitmap.createBitmap(clipBitmap,iMinX,iMinY,iMaxX-iMinX,iMaxY-iMinY);
    }

    @Override
    public Bitmap getSmallFeatures(Bitmap features){
        pointList.clear();

        for (int i=0;i<model.getFace_shape().get(0).getFace_profile().size();i++){
            PointF pointF = new PointF(model.getFace_shape().get(0).getFace_profile().get(i).getX(),model.getFace_shape().get(0).getFace_profile().get(i).getY());
            pointList.add(pointF);
        }



        PointF right0 = new PointF(model.getFace_shape().get(0).getRight_eyebrow().get(0).getX(),model.getFace_shape().get(0).getRight_eyebrow().get(0).getY());
        PointF right7 = new PointF(model.getFace_shape().get(0).getRight_eyebrow().get(7).getX(),model.getFace_shape().get(0).getRight_eyebrow().get(7).getY());
        PointF right6 = new PointF(model.getFace_shape().get(0).getRight_eyebrow().get(6).getX(),model.getFace_shape().get(0).getRight_eyebrow().get(6).getY());
        PointF right5 = new PointF(model.getFace_shape().get(0).getRight_eyebrow().get(5).getX(),model.getFace_shape().get(0).getRight_eyebrow().get(5).getY());
        PointF right4 = new PointF(model.getFace_shape().get(0).getRight_eyebrow().get(4).getX(),model.getFace_shape().get(0).getRight_eyebrow().get(4).getY());

        PointF left4 = new PointF(model.getFace_shape().get(0).getLeft_eyebrow().get(4).getX(),model.getFace_shape().get(0).getLeft_eyebrow().get(4).getY());
        PointF left5 = new PointF(model.getFace_shape().get(0).getLeft_eyebrow().get(5).getX(),model.getFace_shape().get(0).getLeft_eyebrow().get(5).getY());
        PointF left6 = new PointF(model.getFace_shape().get(0).getLeft_eyebrow().get(6).getX(),model.getFace_shape().get(0).getLeft_eyebrow().get(6).getY());
        PointF left7 = new PointF(model.getFace_shape().get(0).getLeft_eyebrow().get(7).getX(),model.getFace_shape().get(0).getLeft_eyebrow().get(7).getY());
        PointF left0 = new PointF(model.getFace_shape().get(0).getLeft_eyebrow().get(0).getX(),model.getFace_shape().get(0).getLeft_eyebrow().get(0).getY());


        pointList.add(right0);
        pointList.add(right7);
        pointList.add(right6);
        pointList.add(right5);
        pointList.add(right4);
        pointList.add(left4);
        pointList.add(left5);
        pointList.add(left6);
        pointList.add(left7);
        pointList.add(left0);


        maxX = 0;
        maxY = 0;
        for (int i =0;i<pointList.size();i++){
            if (pointList.get(i).x >= maxX){
                maxX = pointList.get(i).x;
            }
            if (pointList.get(i).y >= maxY){
                maxY = pointList.get(i).y;
            }
        }

        minX = 10000;
        minY = 10000;
        for (int i =0;i<pointList.size();i++){
            if (pointList.get(i).x <= minX){
                minX = pointList.get(i).x;
            }
            if (pointList.get(i).y <= minY){
                minY = pointList.get(i).y;
            }
        }


        int iMinX = (int) minX;
        int iMinY = (int) minY;
        int iMaxX = (int) maxX;
        int iMaxY = (int) maxY;

        return   Bitmap.createBitmap(features,iMinX,iMinY,iMaxX-iMinX,iMaxY-iMinY);
    }

    public Bitmap removeBorder(Bitmap face){
        for (int i=0;i<pointList.size();i++){
           pointList.get(i).x = pointList.get(i).x-minX;
            pointList.get(i).y = pointList.get(i).y-minY;
        }

        Path path = BitmapClipMaster.getBezierPath(pointList);
        Bitmap clipBitmap = BitmapClipMaster.clipBitmap(face,path);

        return clipBitmap;
    }


    public Bitmap getLeftEyebrow(){
        featuresList.clear();

        for (int i=0;i<model.getFace_shape().get(0).getLeft_eyebrow().size();i++){
            PointF pointF = new PointF(model.getFace_shape().get(0).getLeft_eyebrow().get(i).getX(),model.getFace_shape().get(0).getLeft_eyebrow().get(i).getY());
            featuresList.add(pointF);
        }

        Path path = BitmapClipMaster.getBezierPath(featuresList);
        Bitmap clipBitmap = BitmapClipMaster.clipBitmap(mSrc,path);
        return clipBitmap;
    }

    public Bitmap getRightEyebrow(){
        featuresList.clear();

        for (int i=0;i<model.getFace_shape().get(0).getRight_eyebrow().size();i++){
            PointF pointF = new PointF(model.getFace_shape().get(0).getRight_eyebrow().get(i).getX(),model.getFace_shape().get(0).getRight_eyebrow().get(i).getY());
            featuresList.add(pointF);
        }

        Path path = BitmapClipMaster.getBezierPath(featuresList);
        Bitmap clipBitmap = BitmapClipMaster.clipBitmap(mSrc,path);
        return clipBitmap;
    }

    public Bitmap getLeftEye(){
        featuresList.clear();

        for (int i=0;i<model.getFace_shape().get(0).getLeft_eye().size();i++){
            PointF pointF = new PointF(model.getFace_shape().get(0).getLeft_eye().get(i).getX(),model.getFace_shape().get(0).getLeft_eye().get(i).getY());
            featuresList.add(pointF);
        }

        Path path = BitmapClipMaster.getBezierPath(featuresList);
        Bitmap clipBitmap = BitmapClipMaster.clipBitmap(mSrc,path);
        return clipBitmap;
    }



    @Override
    public Bitmap getRightEye() {
        featuresList.clear();

        for (int i=0;i<model.getFace_shape().get(0).getRight_eye().size();i++){
            PointF pointF = new PointF(model.getFace_shape().get(0).getRight_eye().get(i).getX(),model.getFace_shape().get(0).getRight_eye().get(i).getY());
            featuresList.add(pointF);
        }

        Path path = BitmapClipMaster.getBezierPath(featuresList);
        Bitmap clipBitmap = BitmapClipMaster.clipBitmap(mSrc,path);
        return clipBitmap;
    }

    @Override
    public Bitmap getNose() {
        featuresList.clear();

        for (int i=0;i<model.getFace_shape().get(0).getNose().size();i++){
            PointF pointF = new PointF(model.getFace_shape().get(0).getNose().get(i).getX(),model.getFace_shape().get(0).getNose().get(i).getY());
            featuresList.add(pointF);
        }

        Path path = BitmapClipMaster.getBezierPath(featuresList);
        Bitmap clipBitmap = BitmapClipMaster.clipBitmap(mSrc,path);
        return clipBitmap;
    }

    @Override
    public Bitmap getMouse() {
        featuresList.clear();

        for (int i=0;i<model.getFace_shape().get(0).getMouth().size();i++){
            PointF pointF = new PointF(model.getFace_shape().get(0).getMouth().get(i).getX(),model.getFace_shape().get(0).getMouth().get(i).getY());
            featuresList.add(pointF);
        }

        Path path = BitmapClipMaster.getBezierPath(featuresList);
        Bitmap clipBitmap = BitmapClipMaster.clipBitmap(mSrc, path);
        return clipBitmap;
    }


    public Bitmap getFeatures(){
        Bitmap brow = BitmapClipMaster.compose(getLeftEyebrow(),getRightEyebrow(),0,0);
        Bitmap eye = BitmapClipMaster.compose(getLeftEye(),getRightEye(),0,0);
        Bitmap eyeAndBrow = BitmapClipMaster.compose(brow,eye,0,0);
        brow = null; eye = null;
        Bitmap noseAndMouse = BitmapClipMaster.compose(getMouse(),getNose(),0,0);
        Bitmap features = BitmapClipMaster.compose(eyeAndBrow,noseAndMouse,0,0);
        noseAndMouse = null;
        return features;
    }


}
