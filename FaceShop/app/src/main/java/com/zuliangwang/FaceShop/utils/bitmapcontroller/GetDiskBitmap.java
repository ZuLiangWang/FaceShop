package com.zuliangwang.FaceShop.utils.bitmapcontroller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created by zuliangwang on 15/11/18.
 */
public class GetDiskBitmap {

    public static Bitmap getDiskBitmap(String path){
        Bitmap bitmap = null;
        try {
            File file = new File(path);
            if (file.exists()){
                bitmap = BitmapFactory.decodeFile(path);
            }
        }
        catch (Exception e){

        }
        return bitmap;
    }
}
