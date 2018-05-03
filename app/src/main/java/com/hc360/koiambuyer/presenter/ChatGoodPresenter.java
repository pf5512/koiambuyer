package com.hc360.koiambuyer.presenter;

import com.hc360.koiambuyer.api.RetrofitService;
import com.hc360.koiambuyer.api.bean.ChatGoodInfo;
import com.hc360.koiambuyer.myinterface.ipresenter.IChatGoodPresenter;
import com.hc360.koiambuyer.myinterface.iview.IChatGoodView;

import rx.Observer;
import rx.functions.Action0;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/9/16
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class ChatGoodPresenter implements IChatGoodPresenter {

    IChatGoodView mView;
    public ChatGoodPresenter(IChatGoodView view) {
        this.mView = view;
    }

    @Override
    public void getChatGood(int pager) {
        RetrofitService.getChatGood(pager)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.showLoading();
                    }
                })
                .subscribe(new Observer<ChatGoodInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showNetError();
                    }

                    @Override
                    public void onNext(ChatGoodInfo info) {
                        mView.getChatGood(info);
                    }
                });
    }
}
