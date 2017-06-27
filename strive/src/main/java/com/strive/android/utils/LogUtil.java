package com.strive.android.utils;

import android.util.Log;

/**
 * Created by 清风徐来 on 2017/06/27
 * 类说明:log
 */

public class LogUtil {

    private static final String TAG = "qfxl";

    public static void i(String tag, String msg) {
        Log.i(tag, msg);
    }

    public static void i(String msg) {
        Log.i(TAG, msg);
    }

    public static void e(String tag, String msg) {
        Log.e(tag, msg);
    }

    public static void e(String msg) {
        Log.e(TAG, msg);
    }

}
