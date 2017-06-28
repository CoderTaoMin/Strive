package com.strive.android.base;


import android.app.Activity;

/**
 * Created by 清风徐来 on 2016/11/3
 * 类说明: 操作UI的基类View
 */
public interface BaseView {
    /**
     * 获取宿主Activity
     * @return activity
     */
    Activity getActivity();

    /**
     * 显示loading页面
     */
    void showLoading();

    /**
     * 显示空数据页面
     */
    void showEmpty();

    /**
     * 显示错误页面
     */
    void showDataError();

    /**
     * 显示网络错误页面
     */
    void showNetError();

    /**
     * 显示正常数据
     */
    void showContent();
}
