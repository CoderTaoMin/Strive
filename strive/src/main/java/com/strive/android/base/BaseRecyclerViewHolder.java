package com.strive.android.base;


import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;

    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
        mConvertView = itemView;
    }
    public static BaseRecyclerViewHolder get(ViewGroup parent, int layoutId) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new BaseRecyclerViewHolder(convertView);
    }

    public <T extends View> T getView(int id) {
        View v = mViews.get(id);
        if (v == null) {
            v = mConvertView.findViewById(id);
            mViews.put(id, v);
        }
        return (T) v;
    }
}
