package com.zuliangwang.FaceShop.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.zuliangwang.FaceShop.utils.bitmapcontroller.BitmapDecoder;

/**
 * Created by zuliangwang on 15/11/29.
 */
public class BaseActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        requestWindowFeature(WINDOW.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
    }

    public void simpleStartActivity(Class<Activity> activityClass){
        Intent intent = new Intent(this,activityClass);
        startActivity(intent);
    }
}
