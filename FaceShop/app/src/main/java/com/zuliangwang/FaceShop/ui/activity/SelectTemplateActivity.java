package com.zuliangwang.FaceShop.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zuliangwang.FaceShop.R;

import butterknife.ButterKnife;

/**
 * Created by zuliangwang on 15/11/28.
 */
public class SelectTemplateActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_template_activity);
        ButterKnife.inject(this);
    }



}
