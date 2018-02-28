package com.dy.enter.presenter;

import android.app.Activity;
import android.text.TextUtils;

import com.dy.enter.api.Api;
import com.dy.enter.entity.Repo;
import com.dy.enter.interfaces.LoginContract;
import com.trello.rxlifecycle.ActivityEvent;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dy on 2017/3/10 14:30.
 */

public class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter {


    public LoginPresenter(Activity activity) {
        super(activity);
        attachView((LoginContract.View) activity);

    }

    @Override
    public void login(String username, String password) {
        if (TextUtils.isEmpty(username)) {
            mView.setUserNameError("用户名错误");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            mView.setPasswordError("密码错误");
            return;
        }
        mView.showProgress();
        Api.getInstance().login(username, password)
                .compose(getActivityLifecycleProvider().<Repo<String>>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribeOn(Schedulers.io())//请求在io线程中
                .observeOn(AndroidSchedulers.mainThread())//请求完成后在main中执行
                .subscribe(new Subscriber<Repo<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideProgress();
                    }

                    @Override
                    public void onNext(Repo<String> stringRepo) {
                        mView.hideProgress();
                        if (stringRepo.getCode() == 200) {
                            mView.loginSuccess();
                        } else {
                            mView.loginFailure();
                        }
                    }
                });
    }


}
