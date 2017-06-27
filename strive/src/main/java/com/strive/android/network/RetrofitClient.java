package com.strive.android.network;

import com.strive.android.utils.LogUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 清风徐来 on 2017/06/27
 * 类说明:Retrofit
 */
public class RetrofitClient {
    private static RetrofitClient mRetrofitClient;
    private static Retrofit mRetrofit;

    private RetrofitClient() {
        //日志打印拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(8, TimeUnit.SECONDS)
                .readTimeout(8, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new TokenInterceptor())
                .build();
        mRetrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .baseUrl(ApiService.HOST)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * 获取当前实例
     *
     * @return
     */
    public static synchronized RetrofitClient getInstance() {
        if (mRetrofitClient == null) {
            mRetrofitClient = new RetrofitClient();
        }
        return mRetrofitClient;
    }


    public <T> T create(Class<T> service) {
        return mRetrofit.create(service);
    }


    /**
     * 为请求设置token
     */
    private class TokenInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request mRequest = chain.request();
            LogUtil.i(mRequest.toString());
            mRequest = mRequest.newBuilder()
                    .addHeader("Token", "")
                    .build();
            return chain.proceed(mRequest);
        }
    }

}
