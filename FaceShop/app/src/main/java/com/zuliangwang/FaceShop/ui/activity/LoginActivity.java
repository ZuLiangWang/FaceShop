package com.zuliangwang.FaceShop.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.zuliangwang.FaceShop.R;
import com.zuliangwang.FaceShop.utils.cameraUtils.CameraFilePath;
import com.zuliangwang.FaceShop.view.LoginView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class LoginActivity extends AppCompatActivity implements LoginView,View.OnClickListener{

    @InjectView(R.id.take_photo_button)
    Button takePhotoButton;

    @InjectView(R.id.select_photo_button)
    Button selectPhotoButton;

    @InjectView(R.id.set_up_button)
    Button setUpButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.inject(this);
        initialized();







    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    @Override
    public void initialized() {
        selectPhotoButton.setOnClickListener(this);
        setUpButton.setOnClickListener(this);
        takePhotoButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.take_photo_button:
                startCamera();
                Log.d("TAG","Start Cameara");
                break;
        }
    }


    @Override
    public void startCamera() {
        Intent cameraIntent =new Intent();
        cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.addCategory(Intent.CATEGORY_DEFAULT);
        File photoFilePath = null;

        if (cameraIntent.resolveActivity(getPackageManager()) == null)  Log.d("TAG","null");
        else Log.d("TAG","not null");
        try {
            photoFilePath = CameraFilePath.createImageFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("TAG",""+photoFilePath.getAbsolutePath());
        if (photoFilePath != null) {
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(photoFilePath));
            startActivityForResult(cameraIntent, 1);
        }
    }

    //当照片拍摄完毕，在这里调用图片进入下一个Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK){
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get(MediaStore.EXTRA_OUTPUT);
            Intent editIntent = new Intent(LoginActivity.this,EditPhotoActivity.class);

            startActivity(editIntent);
        }

    }


}
