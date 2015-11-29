package com.zuliangwang.FaceShop.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.zuliangwang.FaceShop.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by zuliangwang on 15/11/28.
 */
public class SelectExpressionFaceActivity extends BaseActivity implements View.OnClickListener{


    @InjectView(R.id.change_face)
    ImageButton changeFace;
    @InjectView(R.id.make_expression)
    ImageButton makeExpression;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_changeface_expression);
        ButterKnife.inject(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.change_face:
//                Intent intent = new Intent(this,CameraActivity.class);
                break;
            case R.id.make_expression:
                Intent intent1 = new Intent(this,CameraActivity.class);
                break;
        }
    }
}
