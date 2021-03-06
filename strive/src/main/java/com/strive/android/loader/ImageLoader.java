package com.strive.android.loader;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by 清风徐来 on 2017/6/20.
 * 所有图片的加载入口
 */

public class ImageLoader {

    public static void displayImage(ImageView imageView, Context context, String url) {
        Glide.with(context).load(url).into(imageView);
    }
}
