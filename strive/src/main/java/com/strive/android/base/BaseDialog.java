package com.strive.android.base;

import android.app.Dialog;
import android.content.Context;

/**
 * Created by 清风徐来 on 2016/11/2
 * 类说明:Dialog的基类
 */

public class BaseDialog extends Dialog {
    public BaseDialog(Context context) {
        super(context);
    }

    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
    }
    //公用的属性设置
}
