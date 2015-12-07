package com.zuliangwang.FaceShop.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zuliangwang.FaceShop.R;

import butterknife.InjectView;


/**
 * Created by zuliangwang on 15/12/7.
 */
public class ShareActivity extends BaseActivity implements View.OnClickListener{

    Intent intent ;
    Bitmap result ;

    @InjectView(R.id.share_qq)
    ImageButton shareQq;;

    @InjectView(R.id.share_wechat)
    ImageButton shareWechat;

    @InjectView(R.id.share_title)
    ImageView shareTitle;

    @InjectView(R.id.share_show)
    ImageView shareShow;

    @InjectView(R.id.save_phone)
    ImageButton savePhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_activity);

        intent = getIntent();
        result = intent.getParcelableExtra("result");

        initImages();

        savePhone.setOnClickListener(this);
    }

    public void initImages(){
        Picasso.with(this).load(R.drawable.e1).into(shareTitle);
        Picasso.with(this).load(R.drawable.e3).into(savePhone);
        Picasso.with(this).load(R.drawable.e4).into(shareWechat);
        Picasso.with(this).load(R.drawable.e5).into(shareQq);
        shareShow.setImageBitmap(result);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save_phone:
                MediaStore.Images.Media.insertImage(getContentResolver(), result, "faceShop", "");
                Toast.makeText(this, "图片已保存到系统相册!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
