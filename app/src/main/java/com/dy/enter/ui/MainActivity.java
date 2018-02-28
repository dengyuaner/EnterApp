package com.dy.enter.ui;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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

//        TextView textView = (TextView)findViewById(R.id.textView);
//        StateListAnimator stateListAnimator = AnimatorInflater
//                .loadStateListAnimator(this, R.animator.selector_animator);
//
//        textView.setStateListAnimator(stateListAnimator);

    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void loadData() {

    }

}
