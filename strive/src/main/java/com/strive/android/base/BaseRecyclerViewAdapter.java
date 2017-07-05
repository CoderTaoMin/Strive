package com.strive.android.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;


/**
 * Created by 清风徐来 on 2017/2/17.
 * class说明:通用适配器
 */

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {

    private List<T> mDataList;

    public BaseRecyclerViewAdapter(List<T> mDataList) {
        this.mDataList = mDataList;
    }

    protected abstract int getLayoutId(int viewType);

    protected abstract void convert(BaseRecyclerViewHolder holder, T data, int position);

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseRecyclerViewHolder.get(parent, getLayoutId(viewType));
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        convert(holder, mDataList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}

