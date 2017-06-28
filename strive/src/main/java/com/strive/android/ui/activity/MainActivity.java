package com.strive.android.ui.activity;


import android.os.Bundle;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.strive.android.R;
import com.strive.android.base.BaseActivity;

import com.strive.android.presenter.MainPresenter;

import com.strive.android.ui.view.MainView;


/**
 * Created by 清风徐来 on 2017/6/20.
 * 主Activity Demo
 */
public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    private RecyclerView contributorRecyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mToolBar.setNavigationIcon(R.drawable.ic_drawer_home);//设置导航栏图标
        mToolBar.setTitle("首页");//设置主标题
        mToolBar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white));//设置主标题颜色
        mToolBar.setTitleTextAppearance(this, R.style.Theme_ToolBar_Base_Title);//修改主标题的外观，包括文字颜色，文字大小等

        mToolBar.inflateMenu(R.menu.base_toolbar_menu);//设置右上角的填充菜单
        mToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItemId = item.getItemId();
                if (menuItemId == R.id.action_search) {
                    Toast.makeText(MainActivity.this, R.string.menu_search, Toast.LENGTH_SHORT).show();
                } else if (menuItemId == R.id.action_notification) {
                    Toast.makeText(MainActivity.this, R.string.menu_notifications, Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        contributorRecyclerView = (RecyclerView) findViewById(R.id.rv_main_contributor_list);
        contributorRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        contributorRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mPresenter.listContributes();
    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mPresenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    public void back() {
        onBackPressed();
    }

    @Override
    public RecyclerView getContributorRecyclerView() {
        return contributorRecyclerView;
    }
}
