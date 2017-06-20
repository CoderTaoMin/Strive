package com.qfxl.android.utils;

import android.content.Context;
import android.util.TypedValue;


/**
 * Created by 清风徐来 on 2016/11/3
 * 类说明: 单位转换
 */
public class DestinyUtils {
    private DestinyUtils(){
        throw new UnsupportedOperationException("can't be instantiated");
    }

    /**
     * dp转换为px
     * @param context 上下文
     * @param inParam 需要转换的数值
     * @return 转换之后的值
     */
    public static int dp2px(Context context,float inParam){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,inParam,context.getResources().getDisplayMetrics());
    }

    /**
     * sp转换为px
     * @param context 上下文
     * @param inParam 需要转换的数值
     * @return 转换之后的值
     */
    public static int sp2px(Context context,float inParam){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,inParam,context.getResources().getDisplayMetrics());
    }

    /**
     * px转换为dp
     * @param context 上下文
     * @param inParam 需要转换的值
     * @return 转换之后的值
     */
    public static int px2dp(Context context,float inParam){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (inParam / scale);
    }

    /**
     * px转换为sp
     * @param context 上下文
     * @param inParam 需要转换的值
     * @return
     */
    public static int px2sp(Context context,float inParam){
        float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (inParam / scale);
    }

}
