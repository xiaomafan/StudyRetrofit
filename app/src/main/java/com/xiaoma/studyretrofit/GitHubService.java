package com.xiaoma.studyretrofit;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GitHubService {

    @FormUrlEncoded
    @POST("ytnc/admin/auth/login")
    Call<NetResponse> serviceApi(@Field("userName") String userName, @Field("password") String password);


    @GET("users/{user}/repos")
    Observable<List<Repo>> listRepos(@Path("user") String user);

    @GET("users/{user}/repos")
    Single<List<Repo>> singleRepos(@Path("user") String user);
}
