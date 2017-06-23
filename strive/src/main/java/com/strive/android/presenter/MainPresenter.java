package com.strive.android.presenter;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;

import com.strive.android.base.BasePresenter;
import com.strive.android.permission.PermissionChecker;
import com.strive.android.ui.view.MainView;
import com.strive.android.utils.AppUtil;
import com.strive.android.utils.ToastUtil;

/**
 * Created by 清风徐来 on 2017/6/20.
 * MainActivity's Presenter
 */
public class MainPresenter implements BasePresenter {
    private MainView mMainView;
    /**
     * 需要申请的权限列表
     */
    private final String[] PERMISSIONS = {Manifest.permission.CALL_PHONE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private PermissionChecker permissionChecker;
    private final int PERMISSION_REQUEST_CODE = 0;
    private boolean isRequireCheck;//是否需要请求权限

    public MainPresenter(MainView mMainView) {
        this.mMainView = mMainView;
        permissionChecker = new PermissionChecker(mMainView.getActivity());
        isRequireCheck = true;
    }

    public void callPhone(Context context) {
        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:10086"));
        context.startActivity(phoneIntent);
    }

    /**
     * 显示权限缺失Dialog
     *
     * @param context
     */
    public void showMissingPermissionDialog(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("温馨提示");
        builder.setMessage("亲,请授予我权限才能更好的为你服务");

        // 拒绝, 退出应用
        builder.setNegativeButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mMainView.back();
            }
        });

        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AppUtil.openAppSetting(context);
            }
        });

        builder.setCancelable(false);

        builder.show();
    }

    @Override
    public void onResume() {
        if (isRequireCheck) {
            if (permissionChecker.lackPermissions(PERMISSIONS)) {
                //申请权限
                ActivityCompat.requestPermissions(mMainView.getActivity(), PERMISSIONS, PERMISSION_REQUEST_CODE);
            } else {
                ToastUtil.showToast(mMainView.getActivity(), "所有的权限获取完毕");
            }
        } else {
            isRequireCheck = true;
        }
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        mMainView = null;
    }

    /**
     * 处理权限申请结果
     * @param requestCode 请求code
     * @param permissions 申请的权限
     * @param grantResults 授予状态
     */
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE && hasAllPermissionGranted(grantResults)) {
            isRequireCheck = true;
            ToastUtil.showToast(mMainView.getActivity(), "权限获取完毕");
        } else {
            isRequireCheck = false;
            showMissingPermissionDialog(mMainView.getActivity());
        }
    }

    /**
     * 所有的权限是否都已经获取到
     *
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
}
