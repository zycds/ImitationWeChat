package com.zyc.imitationwechat.http;

import android.annotation.SuppressLint;

import com.zyc.imitationwechat.base.BaseHttpResult;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class HandlerHttpResult{


    /**
     *   直接data 不用转
         {
         "code": 200,
         "data": {
         "name": "wang",
         "pwd": "123321"
         },
         "msg": "add user success"
         }
     *
     *
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<BaseHttpResult<T>, T> handlerResult () {
        return new ObservableTransformer<BaseHttpResult<T>, T>(){

            @Override
            public ObservableSource<T> apply(Observable<BaseHttpResult<T>> upstream) {
                return upstream.flatMap(new Function<BaseHttpResult<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(BaseHttpResult<T> tBaseHttpResult) throws Exception {
                        ObservableSource<T> observable;
                        if (tBaseHttpResult.getCode() == 200) {
                            observable =  createObservable(tBaseHttpResult.getData());
                        } else {
                            observable =  Observable.error(new Throwable("http error. code : " + tBaseHttpResult.getCode()));
                        }
                        return observable;
                    }
                });
            }
        };
    }


    private static <T> ObservableSource<T> createObservable(T data) {
        return Observable.create(emitter -> {
            emitter.onNext(data);
            emitter.onComplete();
        });
    }


    public static <T> ObservableTransformer<T, T>  schedulersThread () {
        return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


}
