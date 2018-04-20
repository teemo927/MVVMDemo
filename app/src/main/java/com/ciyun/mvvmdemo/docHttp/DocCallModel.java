package com.ciyun.mvvmdemo.docHttp;

//返回的具体的字段名称由与接口研发人员约定

/**
 *  慈云医生 应答格式
 * {
 * "retcode":"retCode",
 * "message":"message",
 * "restime":"resTime",
 * "token":" token",
 * "sign":"sign",
 * "data":{}
 *  }
 *
 * @param <T>
 */
public class DocCallModel<T> {

    private String retcode;
    private String message;
    private String restime;
    private String token;
    private String sign;
    private T data;

    public String getRetcode() {
        return retcode;
    }

    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRestime() {
        return restime;
    }

    public void setRestime(String restime) {
        this.restime = restime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}