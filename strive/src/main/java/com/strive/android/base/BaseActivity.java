package com.strive.android.base;


import android.app.Activity;
import android.os.Bundle;

import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;


import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.strive.android.R;
import com.strive.android.R2;
import com.strive.android.ui.custom.MultipleStatusLayout;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by 清风徐来 on 2016/11/2
 * 类说明:基类Activity
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {
    protected T mPresenter;
    @BindView(R2.id.msl_base_root) protected MultipleStatusLayout mRootLayout;//视图的各种状态,如加载错误,网络错误,loading状态
    @BindView(R2.id.toolbar) protected Toolbar mToolBar;
    @BindView(R2.id.ll_content) protected LinearLayout mContentLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_base);
        ButterKnife.setDebug(true);
        ButterKnife.bind(this);
        mRootLayout.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRetryClick();
            }
        });
        View contentView = getLayoutInflater().inflate(getLayoutId(), null);
        LinearLayout.LayoutParams contentViewLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT
                , LinearLayout.LayoutParams.MATCH_PARENT);
        mContentLayout.addView(contentView, contentViewLp);
        mPresenter = initPresenter();
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPresenter != null) {
            mPresenter.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
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
     * 重试点击事件
     */
    protected void onRetryClick() {
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

    @Override
    public Activity getActivity() {
        return this;
    }

    /**
     * loading状态
     */
    @Override
    public void showLoading() {
        if (mRootLayout != null) {
            mRootLayout.showLoading();
        }
    }

    /**
     * 内容为空
     */
    @Override
    public void showEmpty() {
        if (mRootLayout != null) {
            mRootLayout.showEmpty();
        }
    }

    /**
     * 网络出错
     */
    @Override
    public void showNetError() {
        if (mRootLayout != null) {
            mRootLayout.showNoNetwork();
        }
    }

    /**
     * 数据错误
     */
    @Override
    public void showDataError() {
        if (mRootLayout != null) {
            mRootLayout.showError();
        }
    }

    /**
     * 数据加载正常
     */
    @Override
    public void showContent() {
        if (mRootLayout != null) {
            mRootLayout.showContent();
        }
    }
}
