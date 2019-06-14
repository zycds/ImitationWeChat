package com.zyc.imitationwechat.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.Unbinder;

public abstract class BaseActivity extends RxAppCompatActivity implements IBaseView {

    protected BasePresenter mBasePresenter;
    protected Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
        bindView();
        initView();
        if (mBasePresenter!=null) mBasePresenter.loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unBindView();
    }

    @Override
    public void unBindView() {
        if (mUnBinder != null) mUnBinder.unbind();
    }

    protected abstract void init ();

    protected void initView () {}

}
