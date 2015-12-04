package com.zuliangwang.FaceShop.ui.activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.squareup.picasso.Picasso;
import com.zuliangwang.FaceShop.R;
import com.zuliangwang.FaceShop.presenter.CameraPresenter;
import com.zuliangwang.FaceShop.presenter.impl.CameraPresenterImpl;
import com.zuliangwang.FaceShop.utils.cameraUtils.CameraFilePath;
import com.zuliangwang.FaceShop.view.CameraView;

import java.io.File;
import java.io.IOException;

/**
 * Created by zuliangwang on 15/11/15.
 */
public class CameraActivity extends BaseActivity implements View.OnClickListener,CameraView{


//    @InjectView(R.id.take_photo)
    ImageButton takePhoto;
//    @InjectView(R.id.select_from_photoalbum)
    ImageButton selectFromAlbumButton;
//    @InjectView(R.id.cameraac_back)
    ImageButton back;


    public String photoStringAbPath;

    public static final int START_TAKE_PHOTO =1;
    public static final int SELECT_FROM_ALBUM =2;

    CameraPresenter cameraPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_activity);
//        ButterKnife.inject(this);
        takePhoto = (ImageButton) findViewById(R.id.take_photo);
        selectFromAlbumButton = (ImageButton) findViewById(R.id.select_from_photoalbum);
        back = (ImageButton) findViewById(R.id.cameraac_back);

        Log.d("TAG", "ss" + takePhoto.getWidth() + "dd" + selectFromAlbumButton.getWidth() + "cc" + back.getWidth());


        cameraPresenter = new CameraPresenterImpl(this,this,this);

        takePhoto.setOnClickListener(this);
        selectFromAlbumButton.setOnClickListener(this);


//        Picasso.with(this).load(R.drawable.b1).into(takePhoto);
        Picasso.with(this).load(R.drawable.b2).into(back);
        Picasso.with(this).load(R.drawable.b3).into(selectFromAlbumButton);
        Picasso.with(this).load(R.drawable.b1).into(takePhoto);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.take_photo:
                startCamera();
                break;
            case R.id.select_from_photoalbum:
                selectFromAlbum();
                Log.d("TAG","album");
                break;
        }
    }




    public void selectFromAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECT_FROM_ALBUM);
    }



    public void startCamera() {
        Intent cameraIntent =new Intent();
        cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.addCategory(Intent.CATEGORY_DEFAULT);
        File photoFilePath = null;

        if (cameraIntent.resolveActivity(getPackageManager()) == null)  Log.d("TAG", "null");
        else Log.d("TAG","not null");
        try {
            photoFilePath = CameraFilePath.createImageFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("TAG", "" + photoFilePath.getAbsolutePath());
        if (photoFilePath != null) {
            photoStringAbPath = photoFilePath.getAbsolutePath();
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(photoFilePath));
            startActivityForResult(cameraIntent, START_TAKE_PHOTO);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        int openType =0 ;

        switch (requestCode){
            case START_TAKE_PHOTO:
                if (resultCode == RESULT_OK && data!=null){
                    //注意，这里拍摄的photo的路径已经在startCamera得到了
                    openType = 1;
                }
                break;
            case SELECT_FROM_ALBUM:
                //得到了用户选择的照片的路径
                if (resultCode == RESULT_OK && data!=null){
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    photoStringAbPath = cursor.getString(columnIndex);
                    cursor.close();
                    openType = 2;
                }
                break;
        }

        Intent intent = new Intent(this,SelectTemplateActivity.class);
        intent.putExtra("photoPath",photoStringAbPath);
        //openType为0代表选取照片失败，1代表拍摄的照片，2代表从相册选取的照片
        intent.putExtra("openType",openType);
        startActivity(intent);

    }


    @Override
    public void initialized() {

    }

}
