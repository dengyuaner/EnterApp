package com.dy.enter.api;

import com.dy.enter.entity.Repo;
import com.dy.enter.entity.User;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by dy on 2017/3/10 9:40.
 */

public interface ApiService {
    @GET("organization/information")
    Observable<Repo<List<User>>> getCompanyInfo();

    @POST("user/login")
    Observable<Repo<String>> login(@Query("uid") String username, @Query("pwd") String password);

    @Headers("Content-Type: text/xml")
    Observable<String> request();
}
