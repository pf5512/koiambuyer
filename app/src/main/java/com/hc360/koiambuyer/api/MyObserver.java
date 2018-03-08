package com.hc360.koiambuyer.api;

import com.orhanobut.logger.Logger;

import rx.Observer;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/9/26
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public abstract class MyObserver<T extends Object> implements Observer<T>{

    @Override
    public void onCompleted() {
        Logger.e("onCompleted");
    }

    @Override
    public void onError(Throwable e) {
        Logger.e(e.toString());
    }

    @Override
    public abstract void onNext(T  t);

}
