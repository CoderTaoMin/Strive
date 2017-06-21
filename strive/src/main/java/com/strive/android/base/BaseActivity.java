package com.strive.android.base;

import android.app.Activity;

import android.content.pm.PackageManager;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.strive.android.R;
import com.strive.android.ui.custom.MultipleStatusLayout;
import com.strive.android.utils.ToastUtil;


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
        setContentView(R.layout.activity_base);
        rootLayout = (MultipleStatusLayout) findViewById(R.id.msl_base_root);
        showLoading();
        rootLayout.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast(getActivity(), "重新加载");
            }
        });
        LinearLayout contentLayout = (LinearLayout) findViewById(R.id.content_view);
        View view = getLayoutInflater().inflate(getLayoutId(), null);
        contentLayout.addView(view);
        findView();
        initView();
        presenter = initPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
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

    protected abstract void findView();

    protected abstract void initView();

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

    /**
     * 检测权限适用于6.0及以上系统
     *
     * @param permissions 权限
     * @return
     */
    protected boolean hasPermission(String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(this, permission)
                        != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 申请权限
     *
     * @param code
     * @param permissions
     */
    protected void requestPermission(int code, String... permissions) {
        ActivityCompat.requestPermissions(this, permissions, code);
    }

    /**
     * 权限申请回调
     *
     * @param requestCode  请求授权码
     * @param permissions  权限
     * @param grantResults 授权结果
     */
    protected void requestPermissionCallBack(int requestCode, String[] permissions, int[] grantResults) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        requestPermissionCallBack(requestCode, permissions, grantResults);
    }

    /**
     * 获取Activity实例
     *
     * @return
     */
    public Activity getActivity() {
        return this;
    }

}
