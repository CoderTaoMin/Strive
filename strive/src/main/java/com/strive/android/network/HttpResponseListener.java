package com.strive.android.network;


import rx.Subscriber;

public abstract class HttpResponseListener<T> extends Subscriber<T>{
    @Override
    public void onCompleted() {

    }
}
