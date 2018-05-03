package com.hc360.koiambuyer.presenter;

import com.hc360.koiambuyer.api.MyObserver;
import com.hc360.koiambuyer.api.RetrofitService;
import com.hc360.koiambuyer.api.bean.OrderDetailInfo;
import com.hc360.koiambuyer.api.bean.ResponseInfo;
import com.hc360.koiambuyer.myinterface.ipresenter.IOrderDetailPresenter;
import com.hc360.koiambuyer.myinterface.iview.IOrderDetailView;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/21
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class OrderDetailPresenter implements IOrderDetailPresenter {

    IOrderDetailView mView;
    public OrderDetailPresenter(IOrderDetailView mView) {
        this.mView = mView;
    }

    @Override
    public void getOrderDetail(String orderNo) {
        RetrofitService.getOrderDetail(orderNo)
                .subscribe(new MyObserver<OrderDetailInfo>() {
                    @Override
                    public void onNext(OrderDetailInfo info) {
                        mView.getOrderDetail(info);
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
