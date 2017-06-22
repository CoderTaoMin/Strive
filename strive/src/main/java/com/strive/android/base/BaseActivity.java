package com.strive.android.base;



import android.os.Bundle;

import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;


import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.strive.android.R;
import com.strive.android.ui.custom.MultipleStatusLayout;



/**
 * Created by 清风徐来 on 2016/11/2
 * 类说明:基类Activity
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {
    private T presenter;
    protected MultipleStatusLayout rootLayout;//视图的各种状态,如加载错误,网络错误,loading状态

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_base);
        rootLayout = (MultipleStatusLayout) findViewById(R.id.msl_base_root);
        rootLayout.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
        LinearLayout contentLayout = (LinearLayout) findViewById(R.id.content_view);
        View view = getLayoutInflater().inflate(getLayoutId(), null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT
                , LinearLayout.LayoutParams.MATCH_PARENT);
        contentLayout.addView(view, lp);
        presenter = initPresenter();
        initView();
        loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (presenter != null) {
            presenter.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDestroy();
        }
    }

    /**
     * 显示内容布局
     */
    protected void showContent() {
        if (rootLayout != null) {
            rootLayout.showContent();
        }
    }

    /**
     * 显示loading状态
     */
    protected void showLoading() {
        if (rootLayout != null) {
            rootLayout.showLoading();
        }
    }

    /**
     * 显示空数据状态
     */
    protected void showEmptyData() {
        if (rootLayout != null) {
            rootLayout.showEmpty();
        }
    }

    /**
     * 显示网络错误
     */
    protected void showNetWorkError() {
        if (rootLayout != null) {
            rootLayout.showNoNetwork();
        }
    }

    /**
     * 显示数据加载错误
     */
    protected void showLoadError() {
        if (rootLayout != null) {
            rootLayout.showError();
        }
    }


    /**
     * 主布局id
     *
     * @return
     */
    protected abstract int getLayoutId();

    protected abstract void initView();

    /**
     * 加载数据
     */
    protected void loadData() {
        showLoading();
    }

    /**
     * 初始化Presenter
     *
     * @return
     */
    protected abstract T initPresenter();

    /**
     * 添加Fragment到布局中
     *
     * @param containerViewId 布局id
     * @param fragment        fragment
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }


}
