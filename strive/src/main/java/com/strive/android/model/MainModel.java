package com.strive.android.model;

import com.strive.android.base.BaseModel;

import com.strive.android.model.entity.Contributor;
import com.strive.android.network.ApiService;
import com.strive.android.network.RetrofitClient;



import java.util.List;


import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 清风徐来 on 2017/6/24.
 * MainActivity's Model
 */
public class MainModel implements BaseModel {

    public Observable<List<Contributor>> listContributors(String name, String repo) {
        return RetrofitClient.getInstance()
                .create(ApiService.class)
                .listContributors(name, repo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
