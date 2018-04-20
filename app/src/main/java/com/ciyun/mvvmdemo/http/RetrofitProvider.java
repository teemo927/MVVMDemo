package com.ciyun.mvvmdemo.http;

import android.content.Context;
import android.util.Log;

import com.ciyun.mvvmdemo.model.NewsService;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitProvider {
    private static final String TAG = RetrofitProvider.class.getSimpleName();
    private static final long DEFAULT_TIMEOUT = 5000;
    private static final String BASE_URL = "http://news-at.zhihu.com/";

    private static Retrofit retrofit;

    public static Retrofit getInstance(Context context) {
        if (retrofit == null) {
            synchronized (RetrofitProvider.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(initHttpClient(context))
                            .build();
                }
            }
        }
        return retrofit;
    }

    /**
     * 初始化OkHttpClient
     *
     * @param context
     */
    private static OkHttpClient initHttpClient(Context context) {
        File httpCacheDirectory = new File(context.getCacheDir(), "tmp_cache");
        return new OkHttpClient().newBuilder()
                .addNetworkInterceptor(new NetInterceptor(null, context))
                .cache(new Cache(httpCacheDirectory, 10 * 1024 * 1024))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }



    public static <T> T sendDataToServer(final Class<T> service, Context context, String fieldName, String param) {
        T t = RetrofitProvider.getInstance(context).create(service);
        String string = service.getName();
        Log.e(TAG, "---------classname------------" + string);
        try {
            Object entity = service.newInstance();

            Method method = service.getDeclaredMethod(fieldName);
            Object o = method.invoke(entity);
            Log.e(TAG, "---------ooo------------" + o.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public static void test(Context context) {
        //统一
        SimpleDateFormat DAY_FORMAT = new SimpleDateFormat("yyyyMMdd");
        Date date = Calendar.getInstance().getTime();
        String formatDate = DAY_FORMAT.format(date);
        Log.e(TAG, "-----date-----" + formatDate);

        NewsService service = sendDataToServer(NewsService.class, context, "getNewsList", formatDate);
    }
}
