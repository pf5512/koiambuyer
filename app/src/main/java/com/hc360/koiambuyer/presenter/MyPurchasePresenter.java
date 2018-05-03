package com.hc360.koiambuyer.presenter;

import com.hc360.koiambuyer.api.MyObserver;
import com.hc360.koiambuyer.api.RetrofitService;
import com.hc360.koiambuyer.api.bean.MyPurchaseInfo;
import com.hc360.koiambuyer.myinterface.ipresenter.IMyPurchasePresenter;
import com.hc360.koiambuyer.myinterface.iview.IMyPurchaseView;

import rx.functions.Action0;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/21
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class MyPurchasePresenter implements IMyPurchasePresenter{

    IMyPurchaseView mView;
    public MyPurchasePresenter(IMyPurchaseView mView) {
        this.mView = mView;
    }

    @Override
    public void getMyPurchases(String dateType, String stateType,int pager) {
        RetrofitService.getMyPurchases(dateType,stateType,pager)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.showLoading();
                    }
                })
                .subscribe(new MyObserver<MyPurchaseInfo>() {
                    @Override
                    public void onNext(MyPurchaseInfo myPurchaseInfo) {
                        mView.getMyPurchases(myPurchaseInfo);
                    }
                });
    }
}
