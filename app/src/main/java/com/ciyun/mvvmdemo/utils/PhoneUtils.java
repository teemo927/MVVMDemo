package com.ciyun.mvvmdemo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Authority: ciyun
 * Date: 2018-04-17  16:38
 */

public class PhoneUtils {
    public static boolean isPhoneNum(String name) {

        Pattern pattern = Pattern.compile("^[1][34578][0-9]{9}$");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

}
