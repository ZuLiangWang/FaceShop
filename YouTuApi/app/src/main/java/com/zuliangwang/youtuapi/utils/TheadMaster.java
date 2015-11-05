package com.zuliangwang.youtuapi.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zuliangwang on 15/10/18.
 */
public class TheadMaster {
    private final int TASK_END = 0;
    ExecutorService mSingleThreadExecutor;
    ExecutorService mCachedThreadPool;

    private ExecutorService getSingleThreadExecutor(){
        if (mSingleThreadExecutor == null){
            mSingleThreadExecutor = Executors.newSingleThreadExecutor();
        }
        return mSingleThreadExecutor;
    }

    private ExecutorService getmCachedThreadPool(){
        if (mCachedThreadPool == null){
            mCachedThreadPool = Executors.newCachedThreadPool();
        }
        return mCachedThreadPool;
    }

}
