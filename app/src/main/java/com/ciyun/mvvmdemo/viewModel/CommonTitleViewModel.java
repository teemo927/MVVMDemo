package com.ciyun.mvvmdemo.viewModel;

import android.util.Log;

import com.kelin.mvvmlight.base.ViewModel;
import com.kelin.mvvmlight.command.ReplyCommand;

import rx.functions.Action0;

/**
 * Authority: ciyun
 * Date: 2018-04-18  15:47
 */

public class CommonTitleViewModel implements ViewModel {

    private static final String TAG = CommonTitleViewModel.class.getSimpleName();

    public final ReplyCommand closeCommand = new ReplyCommand(new Action0() {
        @Override
        public void call() {
            Log.e(TAG, "---------close---------------");
        }
    });
}
