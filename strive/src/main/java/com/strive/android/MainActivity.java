package com.strive.android;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;


import com.strive.android.base.BaseActivity;

import com.strive.android.permission.PermissionChecker;
import com.strive.android.presenter.MainPresenter;
import com.strive.android.utils.AppUtil;
import com.strive.android.utils.ToastUtil;

/**
 * Created by 清风徐来 on 2017/6/20.
 * 主Activity Demo
 */
public class MainActivity extends BaseActivity<MainPresenter> {

    private final String[] PERMISSIONS = {Manifest.permission.CALL_PHONE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private PermissionChecker permissionChecker;
    private final int PERMISSION_REQUEST_CODE = 0;
    private boolean isRequireCheck;//是否需要请求权限
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_drawer_home);//设置导航栏图标
        toolbar.setLogo(R.mipmap.ic_launcher);//设置app logo
        toolbar.setTitle("Title");//设置主标题
        toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white));//设置主标题颜色
        toolbar.setTitleTextAppearance(this, R.style.Theme_ToolBar_Base_Title);//修改主标题的外观，包括文字颜色，文字大小等

        toolbar.setSubtitle("Subtitle");//设置子标题
        toolbar.setSubtitleTextColor(ContextCompat.getColor(this, android.R.color.darker_gray));//设置子标题颜色
        toolbar.setSubtitleTextAppearance(this, R.style.Theme_ToolBar_Base_Subtitle);//设置子标题的外观，包括文字颜色，文字大小等

        toolbar.inflateMenu(R.menu.base_toolbar_menu);//设置右上角的填充菜单
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
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
        isRequireCheck = true;
    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void loadData() {
        super.loadData();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showNetWorkError();
            }
        }, 3000);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        permissionChecker = new PermissionChecker(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isRequireCheck) {
            if (permissionChecker.lackPermissions(PERMISSIONS)) {
                //申请权限
                ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_REQUEST_CODE);
            } else {
                ToastUtil.showToast(this,"所有的权限获取完毕");
            }
        } else {
            isRequireCheck = true;
        }
    }

    /**
     * 所有的权限是否都已经获取到
     * @param grantResults
     * @return
     */
    private boolean hasAllPermissionGranted(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE && hasAllPermissionGranted(grantResults)) {
            isRequireCheck = true;
            ToastUtil.showToast(this,"权限获取完毕");
        } else {
            isRequireCheck = false;
            showMissingPermissionDialog();
        }
    }
    // 显示缺失权限提示
    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("帮助");
        builder.setMessage("主,请授予我权限");

        // 拒绝, 退出应用
        builder.setNegativeButton("退出", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                AppUtil.openAppSetting(MainActivity.this);
            }
        });

        builder.setCancelable(false);

        builder.show();
    }

}
