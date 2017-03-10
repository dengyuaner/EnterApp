package com.dy.enter.presenter;

import android.content.Context;

import com.trello.rxlifecycle.ActivityLifecycleProvider;

/**
 * Created by zhouzhan on 30/7/16.
 */
public class BasePresenter {

    protected Context mContext;

    public BasePresenter(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 对 ACTIVITY 生命周期进行管理
     *
     * @return
     */
    protected ActivityLifecycleProvider getActivityLifecycleProvider() {
        ActivityLifecycleProvider provider = null;
        if (null != mContext && mContext instanceof ActivityLifecycleProvider) {
            provider = (ActivityLifecycleProvider) mContext;
        }else {

        }
        return provider;
    }

    protected void doError(Throwable error) {
        // TODO 此处可处理错误
    }

    public void doDestroy() {
        this.mContext = null;
    }
}
