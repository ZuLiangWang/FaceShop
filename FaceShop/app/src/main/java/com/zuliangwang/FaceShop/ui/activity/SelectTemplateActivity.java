package com.zuliangwang.FaceShop.ui.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zuliangwang.FaceShop.R;

import butterknife.ButterKnife;

/**
 * Created by zuliangwang on 15/11/28.
 */
public class SelectTemplateActivity extends BaseActivity {


    String photoPath;
    int openType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_template_activity);
        ButterKnife.inject(this);

        Intent cameraIntent = getIntent();
        Bundle bundle= cameraIntent.getExtras();
        photoPath = bundle.getString("photoPath");
        openType = bundle.getInt("openType");
    }

    private void startEditAcitivty(){
        Intent intent  = new Intent(this,EditPhotoActivity.class);
        intent.putExtra("PhotoPath",photoPath);
        intent.putExtra("openType",openType);
        startActivity(intent);
    }






}
