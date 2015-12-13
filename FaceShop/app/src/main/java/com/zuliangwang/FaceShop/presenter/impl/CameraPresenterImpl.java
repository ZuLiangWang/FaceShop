package com.zuliangwang.FaceShop.presenter.impl;

import android.app.Activity;
import android.content.Context;

import com.zuliangwang.FaceShop.presenter.CameraPresenter;


/**
 * Created by zuliangwang on 15/11/29.
 */
public class CameraPresenterImpl implements CameraPresenter {

    private Context mContext;
    private Activity activity;
    private String photoPath;



    private static final int START_TAKE_PHOTO = 1;
    private static final int SELECT_FROM_ALBUM = 2;

    public CameraPresenterImpl(Context mContext, Activity activity) {
        this.mContext = mContext;
        this.activity = activity;
        initialized();
    }

    @Override
    public void initialized() {
    }

    @Override
    public void startTakePhoto() {

    }

    @Override
    public void startSelectFromAlbum() {

    }


}
