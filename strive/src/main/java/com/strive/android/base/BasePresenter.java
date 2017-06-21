package com.strive.android.base;

/**
 * Created by Frank on 2016/11/1
 * 类说明: 基类Presenter
 */
public interface BasePresenter {
    /**
     * 管理声明周期,在Activity或者Fragment调用
     */
    void onResume();

    /**
     * 管理声明周期,在Activity或者Fragment调用
     */
    void onPause();

    /**
     * 管理声明周期,在Activity或者Fragment调用
     */
    void onDestroy();
}
