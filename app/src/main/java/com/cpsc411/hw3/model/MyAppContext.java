package com.cpsc411.hw3.model;

import android.app.Application;
import android.content.Context;

public class MyAppContext extends Application {
    private static Context context;
    public void onCreate() {
        super.onCreate();
        MyAppContext.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyAppContext.context;
    }
}
