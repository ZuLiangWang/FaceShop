package com.zuliangwang.youtuapi.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zuliangwang.youtuapi.R;
import com.zuliangwang.youtuapi.utils.BitmapClipMaster;
import com.zuliangwang.youtuapi.bean.DetectFaceModel;
import com.zuliangwang.youtuapi.bean.Face;
import com.zuliangwang.youtuapi.bean.FacePositionModel;
import com.zuliangwang.youtuapi.YouTuApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import youtu.Youtu;


public class MainActivity extends AppCompatActivity {
    private ImageView face_image1;
    private ImageView face_image2;
    private Button scroll_Button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        


        Youtu faceYoutu = ((YouTuApplication)getApplication()).getYoutu();
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon_face_06);
        Bitmap bitmap2 = BitmapFactory.decodeResource(this.getResources(),R.drawable.icon_face_10);

        face_image1.setImageBitmap(bitmap);
        face_image2.setImageBitmap(bitmap2);
        scroll_Button = (Button) findViewById(R.id.test_scroll);

        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute(faceYoutu, bitmap, bitmap2);



        scroll_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "scroll", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initViews(){
        face_image1 = (ImageView) findViewById(R.id.face_image);
        face_image2 = (ImageView) findViewById(R.id.face_image2);
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

    public class MyAsyncTask extends AsyncTask<Object,Void,String>{
        Gson gson;
        Bitmap smallBitmap;
        Bitmap testBitmap;
        Bitmap newBitmap;
        Bitmap clipBitmap;
        @Override
        protected String doInBackground(Object... params) {
            try {
                Gson gson  = new Gson();
                Youtu faceYoutu = (Youtu) params[0];
                Bitmap bitmap = (Bitmap) params[1];
                Bitmap bitmap2 = (Bitmap) params[2];

                JSONObject response = faceYoutu.DetectFace(bitmap, 0);
                DetectFaceModel model = gson.fromJson(response.toString(), DetectFaceModel.class);
                Face face = model.getFace().get(0);


                JSONObject facePosition = faceYoutu.FaceShape(bitmap, 0);
                Log.d("TAG",""+facePosition.toString());
                FacePositionModel positionModel = gson.fromJson(facePosition.toString(), FacePositionModel.class);




                Path path = new Path();
//                path.moveTo(positionModel.getFace_shape().get(0).getFace_profile().get(0).getX(),positionModel.getFace_shape().get(0).getFace_profile().get(0).getX());
                List<FacePositionModel.FaceShapeEntity.FaceProfileEntity> profileList =  positionModel.getFace_shape().get(0).getFace_profile();
                for (int i=0;i<profileList.size()-1;i++){
                    int temX = profileList.get(i).getX();
                    int temY = profileList.get(i).getY();
                    int nextX = profileList.get(i+1).getX();
                    int nextY = profileList.get(i+1).getY();
                    Log.d("cur"+i,"x="+temX+"     y="+temY);
                    Log.d("next" + i, "x=" + nextX + "      y=" + nextY);
                    path.quadTo(temX, temY, nextX, nextY);
                    if (i==19)
                        path.quadTo(nextX,nextY,positionModel.getImage_width(),0);
                }


                clipBitmap = BitmapClipMaster.clipBitmap(bitmap, path);


                smallBitmap = Bitmap.createBitmap(clipBitmap,face.getX(),face.getY(),face.getWidth(),face.getHeight());





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
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (smallBitmap != null) Log.d("heheda","not null");
            face_image2.setImageBitmap(smallBitmap);
//            face_image2.setImageBitmap(clipBitmap);
        }
    }
}
