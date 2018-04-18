package com.ciyun.mvvmdemo;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.ciyun.mvvmdemo.databinding.ActivityMainBinding;
import com.facebook.drawee.backends.pipeline.Fresco;

public class MainActivity extends Activity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setVariable(com.ciyun.mvvmdemo.BR.viewModel,new MainViewModel(this));
//        binding.setViewModel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.unbind();
    }
}
