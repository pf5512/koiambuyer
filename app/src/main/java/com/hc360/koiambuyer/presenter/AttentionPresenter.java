package com.hc360.koiambuyer.presenter;


import com.hc360.koiambuyer.api.MyObserver;
import com.hc360.koiambuyer.api.RetrofitService;
import com.hc360.koiambuyer.api.bean.AttentionKoInfo;
import com.hc360.koiambuyer.api.bean.ResponseInfo;
import com.hc360.koiambuyer.myinterface.ipresenter.IAttentionPresenter;
import com.hc360.koiambuyer.myinterface.iview.IAttentionView;
import com.hc360.koiambuyer.view.MyApp;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/3
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class AttentionPresenter implements IAttentionPresenter {

    IAttentionView mView;

    public AttentionPresenter(IAttentionView mView) {
        this.mView = mView;
    }

    @Override
    public void getAttentions(int pager) {
        RetrofitService.getAttentions(MyApp.sUserId,pager)
                .subscribe(new MyObserver<AttentionKoInfo>() {
                    @Override
                    public void onNext(AttentionKoInfo attentionKoInfo) {
                        mView.getAttentions(attentionKoInfo);
                    }
                });
    }

    @Override
    public void notFollow(int userId, int followUserId) {
        RetrofitService.notFollow(userId,followUserId)
                .subscribe(new MyObserver<ResponseInfo>() {
                    @Override
                    public void onNext(ResponseInfo responseInfo) {
                        mView.notFollow();
                    }
                });
    }
}
