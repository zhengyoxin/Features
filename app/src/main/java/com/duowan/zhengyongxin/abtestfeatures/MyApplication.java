package com.duowan.zhengyongxin.abtestfeatures;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

/**
 * Created by zhengyongxin on 2018/3/24.
 */

public class MyApplication extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        FeatureManager.INSTANCE.init();
    }

    public static Context getContext() {
        return context;
    }
}
