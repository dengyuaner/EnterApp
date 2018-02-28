package com.dy.enter.interfaces;

/**
 * Created by baowei on 2017/10/31.
 * Presenter基类
 */
public interface BasePresenter<V extends BaseView>{

    void attachView(V view);

    void detachView();
}
