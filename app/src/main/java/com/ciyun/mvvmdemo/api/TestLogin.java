package com.ciyun.mvvmdemo.api;

import android.content.Context;
import android.util.Log;

/**
 * Authority: ciyun
 * Date: 2018-04-19  14:45
 */

public class TestLogin {

    public static void test(Context context) {

        new LoginApi(context).login("tsy", "as", new BaseApi.ApiCallback() {

            @Override
            public void onSuccess(Object ret) {
                Log.i("tsy", "onSuccess:");
            }

            @Override
            public void onError(int err_code, String err_msg) {
                Log.i("tsy", "onError:");
            }

            @Override
            public void onFailure() {
                Log.i("tsy", "onFailure:");
            }
        });
    }

}
