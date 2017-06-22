package com.strive.android.permission;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

/**
 * Created by 清风徐来 on 2017/06/22
 * 类说明:权限检测
 */

public class PermissionChecker {

    private Context mContext;

    public PermissionChecker(Context context) {
        mContext = context.getApplicationContext();
    }

    /**
     * 是否缺失所需要的权限
     * @param permissions 权限列表
     * @return if true all granted , false otherwise
     */
    public boolean lackPermissions(String... permissions) {
        for (String permission : permissions) {
            if (lackPermission(permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否缺失具体权限
     * @param permission 权限名
     * @return if true granted , false otherwise
     */
    public boolean lackPermission(String permission) {
        return ContextCompat.checkSelfPermission(mContext, permission)
                == PackageManager.PERMISSION_DENIED;
    }
}
