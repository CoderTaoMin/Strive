package com.strive.android.network;

import com.strive.android.model.entity.Contributor;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by 清风徐来 on 2017/06/27
 * 类说明:apis
 */

public interface ApiService {
    String HOST = "https://api.github.com/";//hostName

    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: RetrofitBean-Sample-App",
            "name:ljd"
    })
    @GET("repos/{owner}/{repo}/contributors")
    Observable<List<Contributor>> listContributors(@Path("owner") String owner, @Path("repo") String repo);
}
