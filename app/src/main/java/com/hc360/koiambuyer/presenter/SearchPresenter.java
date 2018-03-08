package com.hc360.koiambuyer.presenter;


import com.hc360.koiambuyer.api.MyObserver;
import com.hc360.koiambuyer.api.RetrofitService;
import com.hc360.koiambuyer.api.bean.ChoiceSellerInfo;
import com.hc360.koiambuyer.api.bean.HotInfo;
import com.hc360.koiambuyer.api.bean.SearchInfo;
import com.hc360.koiambuyer.myinterface.ipresenter.ISearchPresenter;
import com.hc360.koiambuyer.myinterface.iview.ISearchView;

import rx.Observer;
import rx.functions.Action0;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/2
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class SearchPresenter implements ISearchPresenter {
    ISearchView mView;

    public SearchPresenter(ISearchView mView) {
        this.mView = mView;
    }


    @Override
    public void getHot() {
        RetrofitService.getHot()
                .subscribe(new MyObserver<HotInfo>() {
                    @Override
                    public void onNext(HotInfo hotInfo) {
                        mView.getHot(hotInfo);
                    }
                });
    }

    @Override
    public void getCompany(String compName, int pager) {
        RetrofitService.getChoiceSeller(compName,null,pager)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.showLoading();
                    }
                })
                .subscribe(new Observer<ChoiceSellerInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showNetError();
                    }

                    @Override
                    public void onNext(ChoiceSellerInfo choiceSellerInfo) {
                        mView.getCompany(choiceSellerInfo);
                    }
                });
    }

    @Override
    public void getSearchGoods(String productName, int pager) {
        RetrofitService.getSearch(productName,pager)
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
                        mView.getSearchGoods(searchInfo);
                    }
                });
    }
}
