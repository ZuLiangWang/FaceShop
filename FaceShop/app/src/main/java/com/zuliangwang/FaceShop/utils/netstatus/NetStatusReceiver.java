package com.zuliangwang.FaceShop.utils.netstatus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

/**
 * Created by zuliangwang on 15/11/6.
 */
public class NetStatusReceiver extends BroadcastReceiver {

    private static ArrayList<NetChangeObserver> observers ;
    private static BroadcastReceiver mBroadcastReceiver ;

    public static void registerObserver(NetChangeObserver observer){
        if (observers == null) {
            observers = new ArrayList<NetChangeObserver>();
        }
        observers.add(observer);
    }

    public static void removeRegisteredObserver(NetChangeObserver observer){
        if (observers != null){
            if (observers.contains(observer)){
                observers.remove(observer);
            }
        }
    }

    private static void notifyObservers(){
        if (observers.isEmpty() == false){
            for (int i=0;i<observers.size();i++){
//                if (observers.size(i))
            }
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        mBroadcastReceiver = NetStatusReceiver.this;

    }
}
