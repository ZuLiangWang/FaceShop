package com.zuliangwang.FaceShop.listener;

/**
 * Created by zuliangwang on 15/11/6.
 */
public interface BaseGsonListener<T> {

    public void onSuccess(T data);

    public void onFailed();

}
