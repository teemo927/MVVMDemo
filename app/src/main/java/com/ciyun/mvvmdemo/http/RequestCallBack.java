package com.ciyun.mvvmdemo.http;

import android.util.Log;

import retrofit2.Response;

/**
 * 预处理返回数据
 *
 * @param <T>
 */
public abstract class RequestCallBack<T> implements retrofit2.Callback<T> {
    public static String OK_CODE = "0";

    @Override
    public void onResponse(retrofit2.Call call, Response response) {

        if (response.isSuccessful() && null != response.body()) {

            // TODO something the same ...
            // ...
            Log.e("TAG", "-----------onSuccess-----------" + response.code());

            onSuccess((T) response.body());


        } else {
            // TODO something the same ...
            // ...
            Log.e("TAG", "-----------onError------------" + response.code());

            onError(response.message());

        }
    }


    @Override
    public void onFailure(retrofit2.Call call, Throwable t) {

        // TODO something the same ...
        // ...
        Log.e("TAG", "-----------onFailure------------" + t.getMessage());

        onFail(t.getMessage());

    }

    public abstract void onSuccess(T result);

    public abstract void onError(String message);

    public abstract void onFail(String message);

    public abstract void goToLogin();

}