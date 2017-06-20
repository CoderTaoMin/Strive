package com.qfxl.android.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 清风徐来 on 2016/11/7
 * 类说明:基类Fragment,在用户可见才加载数据,懒加载,执行顺序如下
 * setUserVisibleHint: isVisibleToUser = false
 * onAttach
 * onCreate
 * setUserVisibleHint: isVisibleToUser = true
 * onCreateView
 * onActivityCreated
 * onStart
 * onResume
 * onPause
 * onPause
 * onStop
 * onDestroyView
 * onDestroy
 * onDetach
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
        if(getUserVisibleHint()){
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
