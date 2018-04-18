package com.ciyun.mvvmdemo.network;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Authority: ciyun
 * Date: 2018-04-17  15:29
 */

public class LoginManager {


    /**
     * 登录请求Json数据组装
     *
     * @param loginname
     * @param password
     * @return
     */
    public static String getLoginReques(String loginname, String password) {
        String title;
        try {
            // 另外一个Json对象需要新建
            JSONObject mQrInfo = new JSONObject();
            mQrInfo.put("loginname", loginname);
            mQrInfo.put("password", password);
            // 将用户和码值添加到整个Json中
            title = mQrInfo.toString();

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return title;
    }

}
