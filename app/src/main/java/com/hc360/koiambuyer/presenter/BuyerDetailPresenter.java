package com.hc360.koiambuyer.presenter;


import com.hc360.koiambuyer.api.MyObserver;
import com.hc360.koiambuyer.api.RetrofitService;
import com.hc360.koiambuyer.api.bean.InitInfo;
import com.hc360.koiambuyer.api.bean.ResponseInfo;
import com.hc360.koiambuyer.api.bean.SearchInfo;
import com.hc360.koiambuyer.myinterface.ipresenter.IBuyerDetailPresenter;
import com.hc360.koiambuyer.myinterface.iview.IBuyerDetailView;
import com.hc360.koiambuyer.view.MyApp;

import rx.Observer;
import rx.functions.Action0;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/3
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class BuyerDetailPresenter implements IBuyerDetailPresenter {
    IBuyerDetailView mView;

    public BuyerDetailPresenter(IBuyerDetailView mView) {
        this.mView = mView;
    }

    @Override
    public void getGoods(String productName,int buyerId, int pager) {
        RetrofitService.getBuyerSearchByCode(productName,buyerId,pager)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.showLoading();
                    }
                })
                .subscribe(new Observer<SearchInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showNetError();
                    }

                    @Override
                    public void onNext(SearchInfo searchInfo) {
                        mView.getGoods(searchInfo);
                        if (searchInfo.list.size()>0){
                            mView.hideLoading();
                        }
                    }
                });
    }

    @Override
    public void getBuyerDetail(String buyerId) {
        RetrofitService.getBuyerInfo(buyerId+"", MyApp.sUserId)
                .subscribe(new MyObserver<InitInfo>() {
                    @Override
                    public void onNext(InitInfo initInfo) {
                        mView.getBuyerDetail(initInfo);
                    }
                });
    }

    @Override
    public void attentionBuyer(int followUserId) {
        RetrofitService.attentionBuyer(followUserId)
                .subscribe(new MyObserver<ResponseInfo>() {
                    @Override
                    public void onNext(ResponseInfo responseInfo) {
                        mView.attentionBuyer();
                    }
                });
    }

    @Override
    public void noAttentionBuyer(int followUserId) {
        RetrofitService.noAttentionBuyer(followUserId)
                .subscribe(new MyObserver<ResponseInfo>() {
                    @Override
                    public void onNext(ResponseInfo responseInfo) {
                        mView.noAttentionBuyer();
                    }
                });
    }
}
