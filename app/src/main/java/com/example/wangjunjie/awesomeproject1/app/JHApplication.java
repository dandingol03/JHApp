package com.example.wangjunjie.awesomeproject1.app;


import android.app.Application;

import com.example.wangjunjie.awesomeproject1.util.NetworkUtils;


public class JHApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        NetworkUtils.init();
    }
}
