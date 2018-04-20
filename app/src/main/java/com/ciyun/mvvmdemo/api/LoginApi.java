package com.ciyun.mvvmdemo.api;

import android.content.Context;

import com.ciyun.mvvmdemo.model.LoginRetData;

import retrofit2.Call;
import retrofit2.http.GET;

public class LoginApi extends BaseApi {

    private static final String mBaseUrl = "http://192.168.3.1/";

    private ApiStore mApiStore;

    public LoginApi(Context context) {
        super(mBaseUrl, context);//修改baseUrl
        mApiStore = mRetrofit.create(ApiStore.class);
    }

    public void login(String username, String password, ApiCallback callback) {
        Call<LoginRetData> call = mApiStore.login();
        call.enqueue(new RetrofitCallback<LoginRetData>(callback));
    }

    public interface ApiStore {
        @GET("test_retrofit.php")
        Call<LoginRetData> login();
    }
}