package com.strive.android;

import android.app.Application;

/**
 * Created by 清风徐来 on 2016/11/2
 * 类说明:
 */
public class App extends Application {
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getApplication() {
        return instance;
    }

    /**
     * 清除cookie
     */
    public void clearCookie() {

    }

    /**
     * 清除缓存
     */
    public void clearCache() {

    }
}
