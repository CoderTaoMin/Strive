package com.strive.android.base;

/**
 * Created by Frank on 2016/11/1
 * 类说明: 基类Presenter
 */
public abstract class BasePresenter<T> {
    public T mView;

    public void attach(T mView) {
        this.mView = mView;
    }

    public void detach() {
        mView = null;
    }
}
