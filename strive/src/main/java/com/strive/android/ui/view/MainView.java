package com.strive.android.ui.view;

import android.support.v7.widget.RecyclerView;

import com.strive.android.base.BaseView;

/**
 * Created by 清风徐来 on 2017/6/23.
 * 主View Demo
 */

public interface MainView extends BaseView {
    /**
     * 返回
     */
    void back();

    RecyclerView getContributorRecyclerView();
}
