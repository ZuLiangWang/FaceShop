package com.zuliangwang.FaceShop;

import android.app.Application;



import youtu.Youtu;

/**
 * Created by zuliangwang on 15/10/18.
 */
public class YouTuApplication extends Application {
    Youtu youtu;
    private static String appId;
    private static String secretId;
    private static String secretKey;

    @Override
    public void onCreate() {
        super.onCreate();
        appId = getResources().getString(R.string.app_id);
        secretId = getResources().getString(R.string.secret_id);
        secretKey = getResources().getString(R.string.secret_key);
        youtu = new Youtu(appId,secretId,secretKey,Youtu.API_YOUTU_END_POINT);
    }

    public Youtu getYoutu(){
        return youtu;
    }
}
