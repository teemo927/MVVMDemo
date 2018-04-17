package com.ciyun.mvvmdemo;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

public abstract class BaseActivity extends Activity {

    private ViewDataBinding mViewDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_base);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mViewDataBinding){
            mViewDataBinding.unbind();
        }
    }
}
