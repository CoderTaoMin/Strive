package com.strive.android;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.strive.android.base.BaseActivity;
import com.strive.android.base.BasePresenter;
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
