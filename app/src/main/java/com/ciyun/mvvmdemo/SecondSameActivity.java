package com.ciyun.mvvmdemo;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.ciyun.mvvmdemo.databinding.ActivityMainBinding;

public class SecondSameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setVariable(com.ciyun.mvvmdemo.BR.viewModel,new MainViewModel(this));
//        MainViewModel2 model2 = new MainViewModel2(this);
//        binding.setViewModel((MainViewModel)(model2))_;
    }

}
