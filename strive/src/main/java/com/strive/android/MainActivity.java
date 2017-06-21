package com.strive.android;

import android.os.Handler;


import com.strive.android.base.BaseActivity;

import com.strive.android.presenter.MainPresenter;

/**
 * Created by 清风徐来 on 2017/6/20.
 * 所有图片的加载入口
 */
public class MainActivity extends BaseActivity<MainPresenter> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void findView() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showContent();
            }
        },3000);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

}
