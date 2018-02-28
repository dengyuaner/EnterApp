package com.dy.enter.presenter;

import android.content.Context;

import com.dy.enter.interfaces.BasePresenter;
import com.dy.enter.interfaces.BaseView;
import com.trello.rxlifecycle.ActivityLifecycleProvider;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zhouzhan on 30/7/16.
 */
public class RxPresenter<V extends BaseView> implements BasePresenter<V> {
    protected V mView;
    protected Context mContext;

    public RxPresenter(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * RxJava对 ACTIVITY 生命周期进行管理
     *
     * @return
     */
    protected ActivityLifecycleProvider getActivityLifecycleProvider() {
        ActivityLifecycleProvider provider = null;
        if (null != mContext && mContext instanceof ActivityLifecycleProvider) {
            provider = (ActivityLifecycleProvider) mContext;
        } else {

        }
        return provider;
    }

    protected void doError(Throwable error) {
        // TODO 此处可处理错误
    }

    public void doDestroy() {
        this.mContext = null;
        detachView();
    }

    @Override
    public void attachView(V view) {
        //目标接口->MvpView
        ClassLoader loader = view.getClass().getClassLoader();
        Class<?>[] interfaces = view.getClass().getInterfaces();
        //目标对象->Activity(具体实现了我们的MvpView的Activity)
        ViewInvocationHandler handler = new ViewInvocationHandler(view);
        //代理对象
        this.mView = (V) Proxy.newProxyInstance(loader, interfaces, handler);
    }

    @Override
    public void detachView() {
        this.mView = null;
    }

    private class ViewInvocationHandler implements InvocationHandler {

        private V view;

        public ViewInvocationHandler(V view) {
            this.view = view;
        }

        //代理对象->控制对目标对象访问
        @Override
        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
            if (this.view != null) {
                //你不等于null，我就立马回调ActivityUI层的方法
                return method.invoke(view, objects);
            }
            return null;
        }
    }
}
