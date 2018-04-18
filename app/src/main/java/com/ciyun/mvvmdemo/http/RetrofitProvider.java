package com.ciyun.mvvmdemo.http;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by dingzhihu on 15/5/7.
 */
public class RetrofitProvider {

    private static Retrofit retrofit;

    private RetrofitProvider() {
    }

    public static Retrofit getInstance() {
        if (retrofit == null) {
            synchronized (RetrofitProvider.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl("http://news-at.zhihu.com/")
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }
        return retrofit;

    }

    public static  void sendDataToServer(String className){
        try {
            Class<?> clazz = Class.forName(className);
            Object object = retrofit.create(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传单个文件
     *
     * @param file        文件
     * @param mediaType   MediaType
     * @param pictureName 文件名
     * @return 返回"RequestBody"和"MultipartBody_Part"
     */
    private static HashMap<String, Object> updateSingelFile(File file, String mediaType, String pictureName) {

        HashMap<String, Object> map = new HashMap<>();
        RequestBody pictureNameBody = RequestBody.create(MediaType.parse(mediaType), pictureName);

        RequestBody requestFile = RequestBody.create(MediaType.parse(mediaType), file);
        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part picturePart = MultipartBody.Part.createFormData(pictureName, file.getName(), requestFile);
        map.put("RequestBody", pictureNameBody);
        map.put("MultipartBody_Part", picturePart);
        return map;
    }

    public static void testUpdateFile(File file, String mediaType, String pictureName) {
        HashMap<String, Object> map = updateSingelFile(file, mediaType, pictureName);
        //调接口
        Service service = getInstance().create(Service.class);
        Call<ResponseBody> call = service.create((RequestBody) map.get("RequestBody"), (MultipartBody.Part) map.get("MultipartBody_Part"));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

        sendDataToServer( RetrofitProvider.class.getName());
    }

    /**
     * @param mediaType 在表单中的content-type  如：multipart/form-data
     * @param content   后面的参数用于描述表单
     * @return 返回配置好的 RequestBody
     */
    public static RequestBody createRequestBody(String mediaType, String content) {
        RequestBody body = RequestBody.create(MediaType.parse(mediaType), content);
        return body;
    }

    /**
     * @param mediaType 在表单中的content-type  如：图片"image/png"
     * @param file 文件
     * @param name 描述
     * @return 返回配置好的 RequestBody
     */
    public static MultipartBody.Part createMultipartBodyPart(String mediaType, File file, String name) {
        RequestBody requestFile = RequestBody.create(MediaType.parse(mediaType), file);
        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part part = MultipartBody.Part.createFormData(name, file.getName(), requestFile);
        return part;
    }

    interface Service {
        //传单个文件
        @Multipart
        @POST("v1/create")
        Call<ResponseBody> create(@Part("pictureName") RequestBody pictureName,
                                  @Part MultipartBody.Part file);

        //传多个文件
        @Multipart
        @POST("v1/create")
        Call<ResponseBody> create(@Part("pictureName") RequestBody pictureName, @PartMap Map<String, RequestBody> file1s);

        //上传图片
        @Multipart
        @POST()
        Observable<ResponseBody> uploadPic(
                @Url String url,
                @Part() MultipartBody.Part file);

        //上传图片数量确定
        @POST("upload/")
        Call<ResponseBody> uploadFiles(@Part("filename") String description,
                                       @Part("pic\"; filename=\"image1.png") RequestBody imgs1,
                                       @Part("pic\"; filename=\"image2.png") RequestBody imgs2,
                                       @Part("pic\"; filename=\"image3.png") RequestBody imgs3,
                                       @Part("pic\"; filename=\"image4.png") RequestBody imgs4);

        //上传图片数量不确定
        @Multipart
        @POST()
        Observable<ResponseBody> uploadFiles(
                @Url String url,
                @PartMap() Map<String, RequestBody> maps);
    }
}
