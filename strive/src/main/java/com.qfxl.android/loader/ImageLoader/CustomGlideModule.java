package com.qfxl.android.loader.ImageLoader;

import android.content.Context;
import android.os.Environment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.GlideModule;

/**
 * Created by 清风徐来 on 2016/12/12
 * 类说明:自定义Glide属性,必须在Manifest申明，Glide 会扫描 AndroidManifest.xml 为 GlideModule 的 meta 声明
 */
public class CustomGlideModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();//系统给应用分配的最大内存大小
        int memoryCacheSize = maxMemory / 8;//分配给Glide图片内存缓存大小为 系统给应用分配的最大内存大小/8

        //设置Glide内存缓存大小
        builder.setMemoryCache(new LruResourceCache(maxMemory));

        //设置磁盘缓存目录及缓存大小
        String diskCacheFolder = Environment.getExternalStorageDirectory().getAbsolutePath()+"/qfxlCache";
        int diskCacheSize = 100 * 1024 * 1024;//磁盘缓存大小，默认100MB

        builder.setDiskCache(new DiskLruCacheFactory(diskCacheFolder,diskCacheSize));

        //设置BitmapPool缓存内存大小
        builder.setBitmapPool(new LruBitmapPool(memoryCacheSize));

        //设置图片解码格式,Glide 默认使用低质量的 RGB565
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);//RGB_565内存使用为ARGB_8888的一半，但质量不高而且不能使用透明度

    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
