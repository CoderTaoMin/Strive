package com.strive.android.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

/**
 * Created by 清风徐来 on 2017/6/23
 * app相关工具类
 */
public class AppUtil {

    /**
     * 打开设置页面
     * @param context
     */
    public static void openAppSetting(Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        context.startActivity(intent);
    }
}
