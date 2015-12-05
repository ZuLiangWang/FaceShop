package com.zuliangwang.FaceShop.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.zuliangwang.FaceShop.R;
import com.zuliangwang.FaceShop.YouTuApplication;
import com.zuliangwang.FaceShop.bean.FacePositionModel;
import com.zuliangwang.FaceShop.bean.FaceTemplateBean;
import com.zuliangwang.FaceShop.interactor.impl.DetectFaceInteractorImpl;
import com.zuliangwang.FaceShop.ui.Config;
import com.zuliangwang.FaceShop.utils.bitmapcontroller.GetDiskBitmap;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import youtu.Youtu;

/**
 * Created by zuliangwang on 15/11/15.
 */
public class EditPhotoActivity extends BaseActivity{



    @InjectView(R.id.edit_photo_back)
    ImageButton backButton;

    @InjectView(R.id.edit_template)
    ImageView template;

    @InjectView(R.id.edit_face)
    ImageView face;



    Intent lastActivityIntent;

    int templateId;
    int curTemplatePosition;
    int templateLeft;
    int templateRight;
    int templateTop;
    int templateBottom;
    String photoPath;

    Bitmap faceBitmap;

    DetectFaceInteractorImpl detectFaceInteractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_photo_activity);
        ButterKnife.inject(this);
        initCameraPhoto();

        LoadFaceTask task = new LoadFaceTask();
        task.execute();

    }

    private void initCameraPhoto(){
        lastActivityIntent = getIntent();

        templateId = lastActivityIntent.getIntExtra("templateId", 0);
        templateLeft = lastActivityIntent.getIntExtra("templateLeft", 0);
        templateRight = lastActivityIntent.getIntExtra("templateRight", 0);
        templateTop = lastActivityIntent.getIntExtra("templateTop", 0);
        templateBottom = lastActivityIntent.getIntExtra("templateBottom", 0);
        curTemplatePosition = lastActivityIntent.getIntExtra("templatePosition",0);

        photoPath = lastActivityIntent.getStringExtra("photoPath");
        faceBitmap = BitmapFactory.decodeFile(photoPath);


        Picasso.with(this).load(templateId).into(template);
        template.setLeft(templateLeft);
        template.setRight(templateRight);
        template.setTop(templateTop);
        template.setBottom(templateBottom);
    }


    public class LoadFaceTask extends AsyncTask{
        Bitmap result;

        @Override
        protected Object doInBackground(Object[] params) {
            Youtu youtu = ((YouTuApplication)getApplication()).getYoutu();;
            try {
                JSONObject respsonse = youtu.FaceShape(faceBitmap,0);
                Gson gson = new Gson();
                FacePositionModel model = gson.fromJson(respsonse.toString(),FacePositionModel.class);
                Log.d("TAG",model.toString());
                DetectFaceInteractorImpl detectFaceInteractor = new DetectFaceInteractorImpl(model,faceBitmap);
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
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            Config config = new Config();
            FaceTemplateBean bean = config.getTemplateConfig(curTemplatePosition);
            int faceLeft = (int) (templateLeft+templateLeft*bean.getLeft());
            int faceRight = (int) (templateRight+templateRight*bean.getRight());
            int faceTop = (int) (templateTop+templateTop*bean.getTop());
            int faceBottom = (int) (templateBottom+templateBottom*bean.getBottom());


            face.setImageBitmap(result);

            boolean is = result==null;
            Log.d("TAG3",""+is);
            face.layout(faceLeft, faceTop, faceRight, faceBottom);

            Log.d("TAG","facveBVottom"+faceBottom+"    templateBottom"+templateBottom);
        }
    }


}
