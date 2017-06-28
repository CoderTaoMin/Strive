package com.strive.android.ui.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.strive.android.R;
import com.strive.android.base.BaseRecyclerViewAdapter;
import com.strive.android.base.BaseRecyclerViewHolder;
import com.strive.android.loader.ImageLoader;
import com.strive.android.model.entity.Contributor;

import java.util.List;

/**
 * Created by 清风徐来 on 2017/6/28
 * 主Activity 列表适配器
 */

public class ContributorAdapter extends BaseRecyclerViewAdapter<Contributor> {

    public ContributorAdapter(List<Contributor> mDataList) {
        super(mDataList);
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_main_contributor;
    }

    @Override
    protected void convert(BaseRecyclerViewHolder holder, Contributor data, int position) {
        ImageView avatar = holder.getView(R.id.iv_contributor_avatar);
        TextView name = holder.getView(R.id.tv_contributor_name);
        ImageLoader.displayImage(avatar, avatar.getContext(), data.getAvatar());
        name.setText(data.getLogin());
    }
}
