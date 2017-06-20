package com.qfxl.android.base;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by 清风徐来 on 2016/11/2
 * 类说明:基类Activity
 */
public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {

    protected T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(getLayoutId());
        findView();
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attach((V) this);
    }

    @Override
    protected void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }

    protected abstract int getLayoutId();//获取主布局layoutId

    protected abstract T initPresenter();//获取presenter

    protected abstract void findView();

    protected abstract void initView();


    /**
     * Activity跳转
     *
     * @param clazz
     */
    protected void transformActivity(Class clazz) {
        transformActivity(clazz, null);
    }

    /**
     * Activity跳转
     *
     * @param clazz
     */
    protected void transformActivity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);//切换动画
    }

    /**
     * 返回键
     */
    protected void back() {
        finish();
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);//切换动画
    }

    /**
     * 显示等待框
     */
    protected void showLoading() {

    }

    /**
     * 隐藏等待框
     */
    protected void hideLoading() {

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

    /**
     * 状态栏是否设置为透明,5.0之后有效
     */
    protected void setTitleBarTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

}
