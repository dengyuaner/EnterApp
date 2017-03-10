package com.dy.enter.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.dy.enter.api.Api;
import com.dy.enter.entity.Repo;
import com.dy.enter.interfaces.ILoginPresenter;
import com.dy.enter.interfaces.ILoginView;
import com.dy.enter.viewmodel.UserModel;
import com.trello.rxlifecycle.ActivityEvent;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dy on 2017/3/10 14:30.
 */

public class LoginPresenter extends BasePresenter implements ILoginPresenter {
    private UserModel mUser;
    private ILoginView loginView;

    public LoginPresenter(Context context, ILoginView loginView) {
        super(context);
        this.loginView = loginView;
        initUser();
    }

    private void initUser() {
        mUser = new UserModel(loginView.getUsername(), loginView.getPassword());
    }

    @Override
    public void login(String username, String password) {
        if (TextUtils.isEmpty(username)) {
            loginView.setUserNameError("用户名错误");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            loginView.setPasswordError("密码错误");
            return;
        }
        loginView.showProgress();
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
                        loginView.hideProgress();
                    }

                    @Override
                    public void onNext(Repo<String> stringRepo) {
                        loginView.hideProgress();
                        if (stringRepo.getCode() == 200) {
                            loginView.loginSuccess();
                        } else {
                            loginView.loginFailure();
                        }
                    }
                });
    }


}
