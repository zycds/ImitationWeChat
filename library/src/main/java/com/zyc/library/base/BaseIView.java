package com.zyc.library.base;

import android.arch.lifecycle.Lifecycle;

import com.trello.rxlifecycle2.LifecycleProvider;

public interface BaseIView {

    void showLoading();

    void showLoadSuccess();

    void showLoadFailed();

    void showEmpty();

    void onLoadRetry();

    void showDialogLoading();

    void showDialogLoading(String msg);

    void hideDialogLoading();

    void showNetWorkError();

    LifecycleProvider<Lifecycle.Event> getProvider();

}
