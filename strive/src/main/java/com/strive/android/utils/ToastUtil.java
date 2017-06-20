package com.strive.android.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * Created by 清风徐来 on 2016/11/2
 * 类说明: Toast工具类
 */
public class ToastUtil {
    private static Toast toast;

    /**
     * 显示一个短toast
     *
     * @param context
     * @param msg 具体消息
     */
    public static void showToast(Context context, String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

    /**
     * 显示一个短toast
     *
     * @param context
     * @param stringId 资源id
     */
    public static void showToast(Context context, @StringRes int stringId) {
        if (toast == null) {
            toast = Toast.makeText(context, stringId, Toast.LENGTH_SHORT);
        } else {
            toast.setText(stringId);
        }
        toast.show();
    }
}
