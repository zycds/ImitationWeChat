package com.zyc.library.base.presenter;

import android.support.v7.app.AppCompatActivity;

import com.zyc.library.base.BaseIView;
import com.zyc.library.http.Repository;

public abstract class BasePresenter<V extends BaseIView, M extends Repository> {


    protected AppCompatActivity activity;
    protected V iView;
    protected M iModel;

    protected void attachView(AppCompatActivity activity, BaseIView baseIView) {
        iView = (V) baseIView;
        this.activity = activity;
    }

    public abstract void initRepository();

    public void detachView() {
        this.iView = null;
    }

    protected void handlerEvent() {

    }

    protected boolean isAttachView() {
        return this.iView != null;
    }

    protected void onDestroyPresenter () {
        activity = null;
        detachView();
    }

}
