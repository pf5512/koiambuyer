package com.hc360.koiambuyer.presenter;


import com.hc360.koiambuyer.api.MyObserver;
import com.hc360.koiambuyer.api.RetrofitService;
import com.hc360.koiambuyer.api.bean.GoodsDetailInfo;
import com.hc360.koiambuyer.api.bean.ResponseInfo;
import com.hc360.koiambuyer.myinterface.ipresenter.IGoodsDetailPresenter;
import com.hc360.koiambuyer.myinterface.iview.IGoodsDetailView;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/20
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class GoodsDetailPresenter implements IGoodsDetailPresenter {
    IGoodsDetailView mView;

    public GoodsDetailPresenter(IGoodsDetailView view) {
        this.mView = view;
    }

    @Override
    public void getGoodsDetail(int productId, String userId) {
        RetrofitService.getGoodsDetail(productId,userId)
                .subscribe(new MyObserver<GoodsDetailInfo>() {
                    @Override
                    public void onNext(GoodsDetailInfo goodsDetailInfo) {
                        mView.getData(goodsDetailInfo);
                    }
                });
    }

    @Override
    public void attentionGood(int productUserId, int productId) {
        RetrofitService.attentionGood(productUserId,productId)
                .subscribe(new MyObserver<ResponseInfo>() {
                    @Override
                    public void onNext(ResponseInfo responseInfo) {
                        mView.attentionSuccess(responseInfo);
                    }
                });
    }

    @Override
    public void noAttentionGood(int productId) {
        RetrofitService.noAttentionGood(productId)
                .subscribe(new MyObserver<ResponseInfo>() {
                    @Override
                    public void onNext(ResponseInfo responseInfo) {
                        mView.noAttentionSuccess();
                    }
                });
    }
}
