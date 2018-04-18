package com.ciyun.mvvmdemo.model;

/**
 * Authority: ciyun
 * Date: 2018-04-17  9:16
 */

public class MainBean {
    String url;
    String userName;
    String password;

    public MainBean() {
    }

    public MainBean(String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
