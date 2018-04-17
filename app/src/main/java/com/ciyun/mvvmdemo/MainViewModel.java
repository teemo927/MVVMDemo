package com.ciyun.mvvmdemo;

import android.content.Context;
import android.databinding.ObservableField;
import android.util.Log;

import com.ciyun.mvvmdemo.model.MainBean;
import com.kelin.mvvmlight.base.ViewModel;
import com.kelin.mvvmlight.command.ReplyCommand;

import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Authority: ciyun
 * Date: 2018-04-17  9:26
 */

public class MainViewModel implements ViewModel {
    private static final String TAG = MainViewModel.class.getSimpleName();

    private static final String URL_1 = "https://wx2.sinaimg.cn/mw690/00658JkGgy1fqekwzp4rfj30ku0kuatg.jpg";
    private static final String URL_2 = "https://wx1.sinaimg.cn/mw690/00658JkGgy1fqetvwsu9uj30ku0ku1ci.jpg";
    //context
    private Context context;

    //model
    public MainBean mainBean;

    //field to presenter   data
    public final ObservableField<String> msg = new ObservableField<>();
    public final ObservableField<String> url = new ObservableField<>();
    public final ObservableField<String> userName = new ObservableField<>();
    public final ObservableField<String> password = new ObservableField<>();


    //command
    public final ReplyCommand onClickCommand  = new ReplyCommand(new Action0() {
        @Override
        public void call() {
            msg.set("Hello");
            url.set(URL_2);

            Log.e(TAG,"--username--"+userName.get());
            Log.e(TAG,"--password--"+password.get());
        }
    });
    public final ReplyCommand afterUserNameTextChangedCommand  = new ReplyCommand(new Action1<String>() {
        @Override
        public void call(String s) {
            Log.e(TAG,"--call s:--"+s);
        }
    });

    public MainViewModel(Context context) {
        this.context = context;
        loadData("Good Day!",URL_1);
    }

    private void loadData(String s, String link) {
        MainBean bean = new MainBean();
        bean.setMsg(s);
        bean.setUrl(link);
        this.mainBean = bean;

        initViewModelField();
    }

    private void initViewModelField() {
        msg.set(mainBean.getMsg());
        url.set(mainBean.getUrl());
        userName.set(mainBean.getUserName());
        password.set(mainBean.getUserName());
    }
}
