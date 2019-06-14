package com.zyc.library.http;

import com.zyc.library.base.BaseIView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

public class Repository {

    public static final int LOADING_TYPE_NULL = 0;
    public static final int LOADING_TYPE_DIALOG = 1;
    public static final int LOADING_TYPE_PAGE = 2;


    protected BaseIView iView;

    public Repository (BaseIView baseIView) {
        iView = baseIView;
    }

    protected Observable addObservable (Observable observable) {
        if (iView == null) {
            return null;
        }
        return customObservable(observable);
    }

    protected Observable addObservable (Observable observable, int type) {
        if (iView == null) {
            return null;
        }
        return customObservable(observable);
    }

    private Observable customObservable(Observable observable) {

        return observable.compose(iView.getProvider().bindToLifecycle())
                  .retryWhen(new RetryWithDelay(2, 2))
                  .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .doFinally(new Action() {
                      @Override
                      public void run() throws Exception {
                          if (iView != null) iView.hideDialogLoading();
                      }
                  })
                  .doOnNext(new Consumer() {
                      @Override
                      public void accept(Object o) throws Exception {
                        if (iView != null) iView.showLoadSuccess();
                      }
                  })
                  .doOnError(new Consumer<Throwable>() {
                      @Override
                      public void accept(Throwable throwable) throws Exception {

                      }
                  });
    }


}
