package com.ciyun.mvvmdemo.ui;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.ciyun.mvvmdemo.R;
import com.ciyun.mvvmdemo.databinding.ActivitySecondSameBinding;
import com.ciyun.mvvmdemo.viewModel.SecondViewModel;

public class SecondSameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivitySecondSameBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_second_same);
        binding.setVariable(com.ciyun.mvvmdemo.BR.viewModel, new SecondViewModel(this));
    }

}
