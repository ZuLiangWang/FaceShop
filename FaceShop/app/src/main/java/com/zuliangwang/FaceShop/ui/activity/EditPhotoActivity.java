package com.zuliangwang.FaceShop.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.zuliangwang.FaceShop.R;
import com.zuliangwang.FaceShop.YouTuApplication;
import com.zuliangwang.FaceShop.bean.FacePositionModel;
import com.zuliangwang.FaceShop.bean.FaceTemplateBean;
import com.zuliangwang.FaceShop.interactor.impl.DetectFaceInteractorImpl;
import com.zuliangwang.FaceShop.ui.Config;
import com.zuliangwang.FaceShop.utils.bitmapcontroller.BitmapClipMaster;
import com.zuliangwang.FaceShop.utils.bitmapcontroller.BitmapRich;
import com.zuliangwang.FaceShop.utils.bitmapcontroller.GetDiskBitmap;
import com.zuliangwang.FaceShop.utils.cameraUtils.CameraFilePath;
import com.zuliangwang.FaceShop.widget.DragText;

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
public class EditPhotoActivity extends BaseActivity implements View.OnClickListener{



    @InjectView(R.id.edit_photo_back)
    ImageButton backButton;

    @InjectView(R.id.edit_frame)
    FrameLayout editFrame;

    @InjectView(R.id.edit_big_size)
    ImageButton bigSize;

    @InjectView(R.id.edit_small_size)
    ImageButton smallSize;

    @InjectView(R.id.edit_text_size)
    TextView textSize;

    @InjectView(R.id.edit_edit_text)
    EditText editText;

    @InjectView(R.id.edit_template_confirm)
    ImageButton confirm;

    @InjectView(R.id.edit_title)
    ImageView editTitle;

    float curTextSize;
    float textSizeRat;
    int curRat;


    Intent lastActivityIntent;

    int templateId;
    int curTemplatePosition;
    int templateLeft;
    int templateRight;
    int templateTop;
    int templateBottom;

    int faceLeft;
    int faceRight;
    int faceTop;
    int faceBottom;
    String photoPath;


    Bitmap faceBitmap;
    ImageView template ;
    DragText dragText;

    DetectFaceInteractorImpl detectFaceInteractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.edit_photo_activity);
        ButterKnife.inject(this);
        initInfo();
        initTemplate();


        LoadFaceTask task = new LoadFaceTask();
        task.execute();

    }

    private void initInfo(){

        lastActivityIntent = getIntent();

        templateId = lastActivityIntent.getIntExtra("templateId", 0);
        templateLeft = lastActivityIntent.getIntExtra("templateLeft", 0);
        templateRight = lastActivityIntent.getIntExtra("templateRight", 0);
        templateTop = lastActivityIntent.getIntExtra("templateTop", 0);
        templateBottom = lastActivityIntent.getIntExtra("templateBottom", 0);
        curTemplatePosition = lastActivityIntent.getIntExtra("templatePosition", 0);

        photoPath = lastActivityIntent.getStringExtra("photoPath");
        faceBitmap = BitmapFactory.decodeFile(photoPath);

        Picasso.with(this).load(R.drawable.d3).into(bigSize);
        Picasso.with(this).load(R.drawable.d4).into(smallSize);
        Picasso.with(this).load(R.drawable.b2).into(backButton);
        Picasso.with(this).load(R.drawable.d1).into(editTitle);

        smallSize.setOnClickListener(this);
        bigSize.setOnClickListener(this);
        backButton.setOnClickListener(this);
        confirm.setOnClickListener(this);

    }

    public void initTemplate(){
        curRat =1;
        template = new ImageView(this);
        template.setBackgroundColor(Color.WHITE);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(templateRight-templateLeft,templateBottom-templateTop);
        params.setMargins(templateLeft, templateTop, templateRight, templateBottom);
        template.setLayoutParams(params);

        editFrame.addView(template);
        Picasso.with(this).load(templateId).resize(220,220).into(template);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.edit_big_size:
                if (curTextSize < (textSizeRat*3) ){
                    curTextSize += textSizeRat;
                    curRat++;
                }
                textSize.setText(""+curRat);
                break;
            case R.id.edit_small_size:
                if (curTextSize > textSizeRat){
                    curTextSize -= textSizeRat;
                    curRat--;
                }

                textSize.setText(""+curRat);
                break;
            case R.id.edit_photo_back:
                onBackPressed();
                break;
            case R.id.edit_template_confirm:
                Intent intent = new Intent(EditPhotoActivity.this,ShareActivity.class);
                Bitmap result = GetDiskBitmap.loadBitmapFromView(editFrame);
//                intent.putExtra("result",result);
//                startActivity(intent);

                MediaStore.Images.Media.insertImage(getContentResolver(), result, "faceShop", "");
                Toast.makeText(EditPhotoActivity.this,"图片已保存到系统相册!",Toast.LENGTH_SHORT).show();
                break;

        }

        dragText.setTextSize(curTextSize);

    }


    public class LoadFaceTask extends AsyncTask{
        Bitmap result;
        DetectFaceInteractorImpl detectFaceInteractor;

        @Override
        protected Object doInBackground(Object[] params) {
            Youtu youtu = ((YouTuApplication)getApplication()).getYoutu();;
            try {
                JSONObject respsonse = youtu.FaceShape(faceBitmap,0);
                Gson gson = new Gson();
                FacePositionModel model = gson.fromJson(respsonse.toString(),FacePositionModel.class);
                Log.d("TAG",model.toString());
               detectFaceInteractor = new DetectFaceInteractorImpl(model,BitmapRich.toGrayscale(faceBitmap));
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
            float temLeft = bean.getLeft()*template.getWidth();
            faceLeft = (int) (templateLeft+ temLeft);

            float temRight = bean.getRight()*template.getWidth();
            faceRight = (int) (templateLeft+temRight);

            float temTop = bean.getTop()*template.getHeight();
            faceTop = (int) (templateTop+temTop);

            float temBottom=bean.getBottom()*template.getHeight();
            faceBottom = (int) (templateTop+templateBottom);

            ImageView face = new ImageView(EditPhotoActivity.this);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(faceRight-faceLeft,faceBottom-faceTop);

            face.setScaleType(ImageView.ScaleType.FIT_START);

            params.setMargins(faceLeft, faceTop, 0, 0);
            face.setLayoutParams(params);
            editFrame.addView(face);





//            Bitmap finalBitmap = BitmapRich.toGrayscale(result);

//            Bitmap zzBitmap = detectFaceInteractor.removeBorder(finalBitmap);

            Bitmap bigFeatures = detectFaceInteractor.getFeatures();
            Bitmap zzBitmap = detectFaceInteractor.getSmallFeatures(bigFeatures);
//            Bitmap finalBitmap = BitmapRich.toGrayscale(zzBitmap);
            face.setImageBitmap(zzBitmap);
//            face.setBackgroundColor(Color.WHITE);



            dragText = new DragText(EditPhotoActivity.this);

            FrameLayout.LayoutParams p = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dragText.setLayoutParams(p);
            editFrame.addView(dragText);
            Log.d("ssssx", "panrent" + dragText.getParent().toString());

            editText.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    dragText.setText(editText.getText());
                    return false;
                }
            });
            curTextSize = dragText.getTextSize();
            textSizeRat = curTextSize;


        }
    }


}
