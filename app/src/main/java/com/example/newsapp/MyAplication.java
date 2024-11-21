package com.example.newsapp;

import android.app.Application;

import com.facebook.ads.AudienceNetworkAds;

public class MyAplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        AudienceNetworkAds.initialize(MyAplication.this);

    }
}
