package com.hc360.koiambuyer.presenter;


import com.hc360.koiambuyer.api.MyObserver;
import com.hc360.koiambuyer.api.RetrofitService;
import com.hc360.koiambuyer.api.bean.AddressInfo;
import com.hc360.koiambuyer.api.bean.OrderDetailInfo;
import com.hc360.koiambuyer.api.bean.ResponseInfo;
import com.hc360.koiambuyer.api.bean.ShipAddressInfo;
import com.hc360.koiambuyer.myinterface.ipresenter.IStockPresenter;
import com.hc360.koiambuyer.myinterface.iview.IStockView;
import com.hc360.koiambuyer.view.MyApp;

import java.math.BigDecimal;

import rx.Observer;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/13
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class StockPresenter implements IStockPresenter {

    IStockView mView;

    public StockPresenter(IStockView view) {
        this.mView = view;
    }

    /**
     * 获取发货地址
     */
    @Override
    public void getAddresses() {
        RetrofitService.getAddresses(new Integer(MyApp.sUserId))
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
    public void getAddresses(int receiveId) {
        RetrofitService.getAddress(receiveId)
                .subscribe(new Observer<AddressInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.getErrorAddress();
                    }

                    @Override
                    public void onNext(AddressInfo addressInfo) {
                        mView.getAddress(addressInfo);
                    }
                });
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
    public void submit(int proId, int goodsId, int orderCount, BigDecimal orderUnitpri, BigDecimal orderPrice, int orderSimcount, BigDecimal orderSimunitpri, BigDecimal orderSimprice, String orderContent, BigDecimal moneyMap) {
        RetrofitService.submitStock(proId,goodsId,orderCount,orderUnitpri,orderPrice,orderSimcount,orderSimunitpri,orderSimprice,orderContent,moneyMap)
                .subscribe(new MyObserver<ResponseInfo>() {
                    @Override
                    public void onNext(ResponseInfo responseInfo) {
                        mView.submitSuccess();
                    }
                });
    }

}
