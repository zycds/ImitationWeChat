package com.zyc.imitationwechat.base;

public abstract class BasePresenter {

    protected IBaseView iBaseView;
    public BasePresenter (IBaseView iBaseView) {
        this.iBaseView = iBaseView;
    }

    protected String TAG = getClass().getSimpleName();

    protected abstract void loadData ();

}
