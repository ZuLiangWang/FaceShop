package com.zuliangwang.FaceShop.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.zuliangwang.FaceShop.R;
import com.zuliangwang.FaceShop.YouTuApplication;
import com.zuliangwang.FaceShop.bean.FacePositionModel;
import com.zuliangwang.FaceShop.interactor.DetectFaceInteractor;
import com.zuliangwang.FaceShop.interactor.ReplaceInteractor;
import com.zuliangwang.FaceShop.interactor.impl.DetectFaceInteractorImpl;
import com.zuliangwang.FaceShop.interactor.impl.ReplaceInteractorImpl;
import com.zuliangwang.FaceShop.utils.bitmapcontroller.BitmapClipMaster;
import com.zuliangwang.FaceShop.utils.bitmapcontroller.BitmapRich;
import com.zuliangwang.FaceShop.utils.cameraUtils.CameraFilePath;
import com.zuliangwang.FaceShop.view.LoginView;
import com.zuliangwang.FaceShop.widget.DragEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import youtu.Youtu;


public class LoginActivity extends AppCompatActivity implements LoginView,View.OnClickListener{

    @InjectView(R.id.take_photo_button)
    Button takePhotoButton;

    @InjectView(R.id.select_photo_button)
    Button selectPhotoButton;

    @InjectView(R.id.set_up_button)
    Button setUpButton;

    @InjectView(R.id.faceImage1)
    ImageView origin;

    @InjectView(R.id.faceImage2)
    ImageView newface;


    private String mPhotoPath;
    private Bitmap srcFace;
    private Bitmap result;

    DetectFaceInteractor detectFaceInteractor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.inject(this);
        initialized();




        srcFace = BitmapFactory.decodeResource(getResources(),R.drawable.icon_face_06);
        origin.setImageBitmap(srcFace);
        MyAyncTask task = new MyAyncTask();
        task.execute();

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
            mPhotoPath = photoFilePath.getAbsolutePath();
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

//            editIntent.putExtra("ca")
            editIntent.putExtra("photoPath",mPhotoPath);
            startActivity(editIntent);
        }

    }


    public class MyAyncTask extends AsyncTask<Void,Void,Void>{


        @Override
        protected Void doInBackground(Void... params) {

            Youtu youtu = ((YouTuApplication)getApplication()).getYoutu();;
            try {
                JSONObject respsonse = youtu.FaceShape(srcFace,0);
                Gson gson = new Gson();
                FacePositionModel model = gson.fromJson(respsonse.toString(),FacePositionModel.class);
                detectFaceInteractor = new DetectFaceInteractorImpl(model,srcFace);
                result = detectFaceInteractor.getFace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            newface.setImageBitmap(result);
            FrameLayout frameLayout = (FrameLayout) findViewById(R.id.test_frame);
            ViewGroup.LayoutParams frame = frameLayout.getLayoutParams();

            DragEditText dragEditText = new DragEditText(LoginActivity.this);
            dragEditText.setBg(newface);
//            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dragEditText.setLayoutParams(frame);
            dragEditText.setHint("23333");
            Bitmap test  = BitmapFactory.decodeResource(getResources(),R.drawable.icon_face_10);
//            origin.setImageBitmap(test);
            Bitmap ts = BitmapClipMaster.compose(test, result, 0, 0);
            Bitmap s=BitmapRich.toGrayscale(ts);
            origin.setImageBitmap(s);
        }
    }

}
