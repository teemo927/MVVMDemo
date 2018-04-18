package com.ciyun.mvvmdemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences的一个工具类，调用setParam就能保存String, Integer, Boolean, Float, Long类型的参数
 * 同样调用getParam就能获取到保存在手机里面的数据
 */
public class SharedPreferencesUtils {
    /**
     * 保存在手机里面的文件名
     */
    private static final String FILE_NAME = "sp_data";


    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param context .
     * @param key     .
     * @param object  .
     */
    public static void setParam(Context context, String key, String object) {

        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString(key,object);

        editor.commit();
    }


    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param context       .
     * @param key           .
     * @return .
     */
    public static String getParam(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

        return sp.getString(key,"");
    }
} 