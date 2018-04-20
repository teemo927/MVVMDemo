package com.ciyun.mvvmdemo.http;

import android.content.Context;
import android.util.Log;

import com.ciyun.mvvmdemo.docHttp.DocCallModel;
import com.ciyun.mvvmdemo.docHttp.DoctorRequestCallBack;
import com.ciyun.mvvmdemo.model.NewsService;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Authority: ciyun
 * Date: 2018-04-19  16:47
 */

public class TestUpdate<T> {


    private static final String TAG = TestUpdate.class.getSimpleName();



    public static <T> T sendDataToServer(final Class<T> service, Context context, String fieldName, String param) {
        T t = RetrofitProvider.getInstance(context).create(service);
        String string = service.getName();
        Log.e(TAG, "---------classname------------" + string);
        try {
            Method method = service.getDeclaredMethod(fieldName);
            Object o = method.invoke(param);
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

    public static void testUpdateFile(Context context, File file, String mediaType, String pictureName) {

        //调接口
        HashMap<String, Object> map = updateSingelFile(file, mediaType, pictureName);
        Service service = RetrofitProvider.getInstance(context).create(Service.class);
        Call<DocCallModel> call = service.create((RequestBody) map.get("RequestBody"), (MultipartBody.Part) map.get("MultipartBody_Part"));
        call.enqueue(new DoctorRequestCallBack<DocCallModel>() {
            @Override
            public void onSuccess(DocCallModel result) {

            }

            @Override
            public void onError(String message) {

            }

            @Override
            public void onFail(String message) {

            }

            @Override
            public void goToLogin() {

            }
        });
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
     * @param file      文件
     * @param name      描述
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
        Call<DocCallModel> create(@Part("pictureName") RequestBody pictureName,
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
