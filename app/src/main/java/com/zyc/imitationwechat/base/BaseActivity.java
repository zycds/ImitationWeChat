package com.zyc.imitationwechat.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public abstract class BaseActivity extends AppCompatActivity implements IBaseView {

    protected BasePresenter mBasePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
        bindView();
        if (mBasePresenter!=null) mBasePresenter.loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unBindView();
    }

    protected abstract void init ();

    protected void initView () {}

}
