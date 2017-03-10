package com.dy.enter.ui;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dy.enter.R;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

public abstract class BaseActivity extends RxAppCompatActivity {
    //加载框
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        super.onCreate(savedInstanceState);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initVariables();
        initViews(savedInstanceState);
        loadData();


    }
    /**
     * 设置以下三个抽象方法供子类实现，变量初始化，组件初始化，加载数据
     */


    /**
     * initVariables(初始化变量，如intent，全局变量等)
     */
    protected abstract void initVariables();

    /**
     * initViews(初始化组件，如findViewById)
     *
     * @param savedInstanceState Bundle参数
     */
    protected abstract void initViews(Bundle savedInstanceState);

    /**
     * loadData(初始化加载数据，如从网络加载数据)
     */
    protected abstract void loadData();

    public void showDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            // 设置点击屏幕Dialog不消失
            mProgressDialog.setCanceledOnTouchOutside(false);
        }
        mProgressDialog.show();
    }

    public void dismissDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }
}
