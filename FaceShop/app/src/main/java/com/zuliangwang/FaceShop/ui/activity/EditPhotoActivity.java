package com.zuliangwang.FaceShop.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.zuliangwang.FaceShop.utils.bitmapcontroller.GetDiskBitmap;

import java.io.File;

/**
 * Created by zuliangwang on 15/11/15.
 */
public class EditPhotoActivity extends BaseActivity{

    Bitmap photo;
    Intent loginIntent;
    boolean isTakePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    private void initCameraPhoto(){
        String photoPath = loginIntent.getStringExtra("photoPath");
        photo = GetDiskBitmap.getDiskBitmap(photoPath);
    }


}
