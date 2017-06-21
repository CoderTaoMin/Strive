package com.strive.android.loader.glide;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

/**
 * Created by 清风徐来 on 2016/12/12
 * 类说明: Glide加载图片的监听
 */
public class CustomGlideRequestListener implements RequestListener<String,GlideDrawable> {
    @Override
    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
        //TODO log exception
        return false;//一定要return false，这样Glide才能调用error的占位图
    }

    @Override
    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
        return false;
    }
}
