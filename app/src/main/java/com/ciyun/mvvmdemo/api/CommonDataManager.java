package com.ciyun.mvvmdemo.api;

import android.content.Context;

/**
 * if(网络可用){
 * if(本地存在对应缓存&&缓存没有过期&&非刷新操作){
 * 返回本地；
 * }else{
 * 进行网络请求；
 * 存储网络数据到本地；//（可以使用文件或sqllite）
 * 返回网络数据；
 * }
 * }else{
 * if（刷新操作）{
 * 提示无网络状态下刷新操作不可用；
 * }else{
 * if(本地存在对应缓存){
 * 返回本地缓存
 * }else{
 * 返回空；
 * }
 * }
 * }
 */
public class CommonDataManager {

    /**
     *
     *
     * @param context .
     * @param url 请求api
     * @param refresh 是否强制刷新
     * @return 返回数据
     */
    public Object getData(Context context, String url, boolean refresh) {
//        if (NetWorkUtils.isNetworkAvailable(context)) {
//            if (本地存在对应缓存 && 缓存没有过期 && 非刷新操作) {
//                return getDataFromLocal(url);
//            } else {
//                进行网络请求；
//                存储网络数据到本地；//（可以使用文件或sqllite）
//                返回网络数据；
//            }
//        } else {
//            if(refresh){
////                提示无网络状态下刷新操作不可用；
//            }else{
//                if (本地存在对应缓存) {
//                    return getDataFromLocal(url);
//                } else {
//                    return null;
//                }
//            }
//        }
        return null;
    }

    public Object getDataFromNet(String url) {
        return null;
    }

    public Object getDataFromLocal(String url) {
        return null;
    }

    public void saveDataToLocal(Object o) {

    }
}
