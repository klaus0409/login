package com.microwise.login_http.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by Zzz
 * <p>
 * on 2018/12/10
 */
public class TestApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getInstance() {
        return context;
    }

}
