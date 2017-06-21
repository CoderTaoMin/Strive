package com.strive.android.utils;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by 清风徐来 on 2017/6/20
 * 类说明:
 */

public class ScreenUtil {

    /**
     * 获取屏幕的宽度
     *
     * @param activity 宿主Activity
     * @return 屏幕的宽度
     */
    public static int getScreenWidth(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    /**
     * 获取屏幕的高度
     * @param activity 宿主Activity
     * @return 屏幕的高度
     */
    public static int getScreenHeight(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }
}
