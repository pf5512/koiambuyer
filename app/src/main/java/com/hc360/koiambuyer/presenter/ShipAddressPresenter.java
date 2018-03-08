package com.hc360.koiambuyer.presenter;


import com.hc360.koiambuyer.api.MyObserver;
import com.hc360.koiambuyer.api.RetrofitService;
import com.hc360.koiambuyer.api.bean.ResponseInfo;
import com.hc360.koiambuyer.api.bean.ShipAddressInfo;
import com.hc360.koiambuyer.myinterface.ipresenter.IShipAddressPresenter;
import com.hc360.koiambuyer.myinterface.iview.IShipAddressView;

import rx.Observer;
import rx.functions.Action0;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/13
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class ShipAddressPresenter implements IShipAddressPresenter {

    IShipAddressView mView;

    public ShipAddressPresenter(IShipAddressView view) {
        this.mView = view;
    }


    /**
     * 获取发货地址
     */
    @Override
    public void getAddresses(String id, final boolean setDefault) {
        RetrofitService.getAddresses(new Integer(id))
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        if (!setDefault){
                            mView.showLoading();
                        }
                    }
                })
                .subscribe(new Observer<ShipAddressInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showNetError();
                    }

                    @Override
                    public void onNext(ShipAddressInfo shipAddressInfo) {
                        mView.getAddress(shipAddressInfo);
                    }
                });
    }

    @Override
    public void deleteAddress(int id) {
        RetrofitService.deleteAddress(id)
                .subscribe(new MyObserver<ResponseInfo>() {
                    @Override
                    public void onNext(ResponseInfo responseInfo) {
                        mView.deleteAddress();
                    }
                });
    }

    @Override
    public void setDefaultAddress(int deliverId,int comId , String id) {
        RetrofitService.setDefaultAddress(deliverId,comId,id)
                .subscribe(new MyObserver<ResponseInfo>() {
                    @Override
                    public void onNext(ResponseInfo responseInfo) {
                        mView.setDefaultAddress();
                    }
                });
    }
}
