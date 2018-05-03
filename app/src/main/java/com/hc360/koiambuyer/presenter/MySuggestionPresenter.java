package com.hc360.koiambuyer.presenter;

import com.hc360.koiambuyer.api.MyObserver;
import com.hc360.koiambuyer.api.RetrofitService;
import com.hc360.koiambuyer.api.bean.MySuggestionInfo;
import com.hc360.koiambuyer.api.bean.ResponseInfo;
import com.hc360.koiambuyer.myinterface.ipresenter.IMySuggestionPresenter;
import com.hc360.koiambuyer.myinterface.iview.IMySuggestionView;

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

public class MySuggestionPresenter implements IMySuggestionPresenter{
    IMySuggestionView mView;

    public MySuggestionPresenter(IMySuggestionView mView) {
        this.mView = mView;
    }

    @Override
    public void getSuggestions(int useId,int pager) {
        RetrofitService.getSuggestions(useId,pager)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.showLoading();
                    }
                })
                .subscribe(new MyObserver<MySuggestionInfo>() {
                    @Override
                    public void onNext(MySuggestionInfo info) {
                        mView.getSuggestions(info);
                    }
                });
    }

    @Override
    public void deleteMsg(String ids) {
        RetrofitService.deleteSuggestions(ids)
                .subscribe(new MyObserver<ResponseInfo>() {
                    @Override
                    public void onNext(ResponseInfo responseInfo) {
                        mView.deleteMsg();
                    }
                });
    }
}
