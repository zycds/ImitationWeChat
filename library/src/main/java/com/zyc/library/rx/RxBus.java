package com.zyc.library.rx;


import com.jakewharton.rxrelay2.PublishRelay;
import com.jakewharton.rxrelay2.Relay;


import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class RxBus {

    private Relay<Object> mRxBusRelay;

    private Subject<Object> mRxBusSubject;

    private FlowableProcessor<Object> mRxBusFlowable;

    private RxBus () {
        mRxBusRelay = PublishRelay.create().toSerialized();
        mRxBusFlowable = PublishProcessor.create().toSerialized();
        mRxBusSubject = PublishSubject.create().toSerialized();
    }

    public static RxBus getRelayInstance () {
        return Holder.rxBusRelay;
    }
    public static RxBus getObservableInstance () {
        return Holder.rxBusSubject;
    }

    public static RxBus getFlowableInstance () {
        return Holder.rxBusFlowable;
    }

    public void postSubject (Object o) {
        mRxBusSubject.onNext(o);
    }

    public void postFlowable (Object o) {
        mRxBusFlowable.onNext(o);
    }

    public void post(Object o) {
        mRxBusRelay.accept(o);
    }

    public <T> Observable<T> tObservable (Class<T> tClass) {
        return mRxBusRelay.ofType(tClass);
    }

    public Observable<Object> toObservable () {
        return mRxBusRelay;
    }


    public <T> Flowable<T> toFlowable (Class<T> tClass) {
        return mRxBusFlowable.ofType(tClass);
    }

    public Flowable<Object> toFlowable () {
        return mRxBusFlowable;
    }


    public boolean hasObservables () {

        Observable observable = mRxBusRelay;

        return mRxBusRelay.hasObservers();
    }

    private static class Holder {
        private static final RxBus rxBusRelay = new RxBus();
        private static final RxBus rxBusSubject = new RxBus();
        private static final RxBus rxBusFlowable = new RxBus();
    }

}
