package com.strive.android.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 清风徐来 on 2016/11/7
 * 类说明:实现懒加载的Fragment
 */
public abstract class BaseFragment extends Fragment {
    private boolean isViewCreated;//视图是否已经创建
    private boolean isLoadDataComplete;//用户是否可见

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        initViews(view);
        isViewCreated = true;
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint()) {
            loadData();
            isLoadDataComplete = true;
        }
    }

    protected abstract int getLayoutId();

    protected abstract void initViews(View view);

    protected abstract void loadData();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isViewCreated && !isLoadDataComplete) {//Fragment可见
            loadData();
        }
    }
}
