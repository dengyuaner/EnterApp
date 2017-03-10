package com.dy.enter.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.dy.enter.R;
import com.dy.enter.api.Api;
import com.dy.enter.entity.Repo;
import com.dy.enter.entity.User;
import com.trello.rxlifecycle.ActivityEvent;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void loadData() {
        Api.getInstance().getCompanyInfo()
                .flatMap(new Func1<Repo<List<User>>, Observable<Repo<String>>>() {
                    //获取公司信息后登陆
                    @Override
                    public Observable<Repo<String>> call(Repo<List<User>> listRepo) {
                        System.out.println(listRepo.getData().get(0).getOrgId());
                        return Api.getInstance().login("GS0044", "888888");
                    }
                })

                .subscribeOn(Schedulers.io())//请求在io线程中
                .observeOn(Schedulers.io())//请求完成后在io中执行
                .doOnNext(new Action1<Repo<String>>() {
                    @Override
                    public void call(Repo<String> stringRepo) {
                        //保存账号密码
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .compose(this.<Repo<String>>bindUntilEvent(ActivityEvent.DESTROY))
                //在主线程中执行
                .subscribe(new Subscriber<Repo<String>>() {
                    @Override
                    public void onCompleted() {
                        dismissDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismissDialog();
                    }

                    @Override
                    public void onNext(Repo<String> stringRepo) {

                    }

                    @Override
                    public void onStart() {
                        super.onStart();
                        showDialog();
                    }
                });
    }

}
