package com.hc360.koiambuyer.presenter;


import com.hc360.koiambuyer.api.RetrofitService;
import com.hc360.koiambuyer.api.bean.SearchInfo;
import com.hc360.koiambuyer.myinterface.ipresenter.IFindPresenter;
import com.hc360.koiambuyer.myinterface.iview.IFindView;

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

public class FindPresenter implements IFindPresenter {
    IFindView mView;

    public FindPresenter(IFindView mView) {
        this.mView = mView;
    }

    @Override
    public void getGoods(String productName, int pager) {
        RetrofitService.getSearchByCode(productName,pager)
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
}
