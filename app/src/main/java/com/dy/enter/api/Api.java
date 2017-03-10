package com.dy.enter.api;

import com.dy.enter.entity.Repo;
import com.dy.enter.entity.User;
import com.dy.enter.fastjson.FastJsonConverterFactory;
import com.dy.enter.util.AppConstans;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;

/**
 * Created by dy on 2017/3/10 9:28.
 */

public class Api {

    private ApiService service;

    private Api() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(20, TimeUnit.SECONDS);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstans.SERVER_BASE)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(FastJsonConverterFactory.create()) // 添加fastJson转换器
                .client(builder.build())
                .build();
        service = retrofit.create(ApiService.class);
    }

    public static Api getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;

        private Api instance;

        //JVM会保证此方法绝对只调用一次
        private Singleton() {
            instance = new Api();
        }

        public Api getInstance() {
            return instance;
        }
    }

    public Observable<Repo<List<User>>> getCompanyInfo() {
        return service.getCompanyInfo();
    }

    public Observable<Repo<String>> login(String username, String password) {
        return service.login(username, password);
    }

    public Observable<String> request() {
        return service.request();
    }
}
