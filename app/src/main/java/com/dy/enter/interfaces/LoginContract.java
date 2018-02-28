package com.dy.enter.interfaces;

/**
 * Created by dy on 2018-02-28.
 */

public interface LoginContract {
    interface View extends BaseView {
        void showProgress();

        void hideProgress();

        void setPasswordError(String error);

        void setUserNameError(String error);

        void loginSuccess();

        void loginFailure();
    }

    interface Presenter extends BasePresenter<View> {
        void login(String username, String password);
    }
}
