package com.dy.enter.interfaces;

/**
 * Created by dy on 2017/3/10 14:07.
 */

public interface ILoginView {
    void showProgress();

    void hideProgress();

    void setPasswordError(String error);

    void setUserNameError(String error);

    String getUsername();

    String getPassword();

    void loginSuccess();

    void loginFailure();
}
