package com.hc360.koiambuyer.presenter;

import com.hc360.koiambuyer.api.MyObserver;
import com.hc360.koiambuyer.api.RetrofitService;
import com.hc360.koiambuyer.api.bean.OrderInfo;
import com.hc360.koiambuyer.api.bean.ResponseInfo;
import com.hc360.koiambuyer.myinterface.ipresenter.IOrderPresenter;
import com.hc360.koiambuyer.myinterface.iview.IOrderView;

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

public class OrderPresenter implements IOrderPresenter{

    IOrderView mView;
    public OrderPresenter(IOrderView mView) {
        this.mView = mView;
    }

    @Override
    public void getOrders(String orderState, int pager) {
        RetrofitService.getOrders(orderState,pager)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.showLoading();
                    }
                })
                .subscribe(new MyObserver<OrderInfo>() {
                    @Override
                    public void onNext(OrderInfo orderInfo) {
                        mView.getOrders(orderInfo);
                    }
                });
    }

    @Override
    public void cancelOrder(String orderNo) {
        RetrofitService.cancelOrder(orderNo)
                .subscribe(new MyObserver<ResponseInfo>() {
                    @Override
                    public void onNext(ResponseInfo responseInfo) {
                        mView.cancelSuccess();
                    }
                });
    }

    @Override
    public void sureOrder(String orderNo) {
        RetrofitService.sureOrder(orderNo)
                .subscribe(new MyObserver<ResponseInfo>() {
                    @Override
                    public void onNext(ResponseInfo responseInfo) {
                        mView.sureSuccess();
                    }
                });
    }

}
