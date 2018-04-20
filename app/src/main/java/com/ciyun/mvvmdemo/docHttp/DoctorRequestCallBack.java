package com.ciyun.mvvmdemo.docHttp;

import retrofit2.Response;

public abstract class DoctorRequestCallBack<T> implements retrofit2.Callback<DocCallModel> {
    public static String OK_CODE = "0";

    @Override
    public void onResponse(retrofit2.Call<DocCallModel> call, Response<DocCallModel> response) {

        if (response.isSuccessful() && null != response.body() && response.body().getRetcode().equals(OK_CODE)) {
            // TODO something the same ...
            // ...
            DocCallModel body = response.body();
            if (null == body.getToken()) {//token失效
                goToLogin();
            }
            onSuccess((T) body.getData());


        } else {
            // TODO something the same ...
            // ...

            onError(response.message());

        }
    }


    @Override
    public void onFailure(retrofit2.Call<DocCallModel> call, Throwable t) {

        // TODO something the same ...
        // ...

        onFail(t.getMessage());

    }

    public abstract void onSuccess(T result);

    public abstract void onError(String message);

    public abstract void onFail(String message);

    public abstract void goToLogin();

}