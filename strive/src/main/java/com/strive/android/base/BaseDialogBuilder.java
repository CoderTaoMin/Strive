package com.strive.android.base;


import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

/**
 * Created by 清风徐来 on 2016/11/2
 * 类说明:构建Dialog的基类
 */
public abstract class BaseDialogBuilder {
    protected Context context;

    public BaseDialogBuilder(Context context) {
        this.context = context;
    }

    /**
     * Dialog的主题
     *
     * @return
     */
    abstract int getThemeId();

    /**
     * Dialog的主布局Id
     *
     * @return
     */
    abstract int getLayoutId();

    /**
     * 初始化View
     *
     * @param view
     */
    abstract void initView(View view);

    /**
     * 获取dialog的宽高
     *
     * @return
     */
    abstract Pair<Integer, Integer> getLayoutPair();

    /**
     * 获取dialog的gravity
     * @return
     */
    abstract int getDialogGravity();

    public BaseDialog create() {
        BaseDialog mBaseDialog = new BaseDialog(context, getThemeId());
        View contentView = LayoutInflater.from(context).inflate(getLayoutId(), null);
        initView(contentView);
        mBaseDialog.setContentView(contentView);
        Window mWindow = mBaseDialog.getWindow();
        if (mWindow != null) {
            mWindow.setLayout(getLayoutPair().first, getLayoutPair().second);
            mWindow.setGravity(getDialogGravity());
        }
        return mBaseDialog;
    }


}
