package com.ciyun.mvvmdemo;

import android.content.Context;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;

import com.ciyun.mvvmdemo.model.MainBean;
import com.ciyun.mvvmdemo.network.LoginManager;
import com.ciyun.mvvmdemo.utils.DialogUtils;
import com.ciyun.mvvmdemo.utils.PhoneUtils;
import com.ciyun.mvvmdemo.utils.SharedPreferencesUtils;
import com.ciyun.mvvmdemo.utils.ToastUtils;
import com.kelin.mvvmlight.base.ViewModel;
import com.kelin.mvvmlight.command.ReplyCommand;
import com.kelin.mvvmlight.command.ResponseCommand;

import rx.functions.Action0;
import rx.functions.Func1;

/**
 * Authority: ciyun
 * Date: 2018-04-17  9:26
 */

public class MainViewModel2 implements ViewModel {
    private static final String TAG = MainViewModel2.class.getSimpleName();
    private static final String USER_NAME = "user_name";
    private static final String URL_1 = "https://wx2.sinaimg.cn/mw690/6cf57525ly1fpslhy0cr5j20j60sqab4.jpg";
    private static final String URL_2 = "https://wx1.sinaimg.cn/mw690/6cf57525ly1fpslhy2s3bj20go0oz75j.jpg";

    //context
    private Context context;

    //model
    private MainBean mainBean;

    //field to presenter 展示的数据
    public final ObservableField<String> url = new ObservableField<>();
    public final ObservableField<String> userName = new ObservableField<>();
    public final ObservableField<String> password = new ObservableField<>();

    //command
    public final ResponseCommand onTouchCommond = new ResponseCommand(new Func1<MotionEvent,Boolean>() {
        @Override
        public Boolean call(MotionEvent motionEvent) {
            Log.e(TAG,"---action....---"+motionEvent.getAction());
            if (motionEvent.getAction() == MotionEvent.ACTION_MOVE){
                Log.e(TAG,"---move--------");
                return true;
            }
            if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                Log.e(TAG,"---up--------");
                return false;
            }
            return true;
        }
    });

    //command
    public final ReplyCommand onImgClickCommand = new ReplyCommand(new Action0() {
        @Override
        public void call() {
            Log.e(TAG,"-----call-----");
            //模拟更新数据
            url.set(URL_2);
        }
    });

    //command
    public final ReplyCommand onClickCommand = new ReplyCommand(new Action0() {
        @Override
        public void call() {
            String name = userName.get();
            String pass = password.get();
            Log.e(TAG, "--username--" + name + ", ---password--" + pass);
            if (TextUtils.isEmpty(pass) || TextUtils.isEmpty(pass)) {
                ToastUtils.showToast(context, context.getString(R.string.empty_name_and_password));
            } else if (!PhoneUtils.isPhoneNum(name)) {
                ToastUtils.showToast(context, context.getString(R.string.not_phone_num));
            } else {
                SharedPreferencesUtils.setParam(context, USER_NAME, name);
                String request = LoginManager.getLoginReques(name, pass);
                ToastUtils.showToast(context, request);
                DialogUtils.showProgressDialog(context, null);
            }
        }
    });

    MainViewModel2(Context context) {
        this.context = context;
        loadData(URL_1);
    }

    /**
     * 模拟数据
     */
    private void loadData(String link) {
        String username = SharedPreferencesUtils.getParam(context, USER_NAME);
        Log.e(TAG, "--username--" + username);

        MainBean bean = new MainBean();
        bean.setUrl(link);
        bean.setUserName(username);
        this.mainBean = bean;

        initViewModelField();
    }

    private void initViewModelField() {
        url.set(mainBean.getUrl());
        userName.set(mainBean.getUserName());
        password.set(mainBean.getPassword());
    }

}