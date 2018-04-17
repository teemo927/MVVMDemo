package com.ciyun.mvvmdemo;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.facebook.drawee.backends.pipeline.Fresco;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        ViewDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setVariable(com.ciyun.mvvmdemo.BR.viewModel,new MainViewModel(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
