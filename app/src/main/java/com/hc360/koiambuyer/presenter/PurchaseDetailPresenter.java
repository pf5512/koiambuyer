package com.hc360.koiambuyer.presenter;

import com.hc360.koiambuyer.api.MyObserver;
import com.hc360.koiambuyer.api.RetrofitService;
import com.hc360.koiambuyer.api.bean.PurchaseDetailInfo;
import com.hc360.koiambuyer.api.bean.ResponseInfo;
import com.hc360.koiambuyer.myinterface.ipresenter.IPurchaseDetailPresenter;
import com.hc360.koiambuyer.myinterface.iview.IPurchaseDetailView;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/21
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class PurchaseDetailPresenter implements IPurchaseDetailPresenter{
    IPurchaseDetailView mView;

    public PurchaseDetailPresenter(IPurchaseDetailView mView) {
        this.mView = mView;
    }

    @Override
    public void getPurchaseDetail(int purId) {
        RetrofitService.getPurchaseDetail(purId)
                .subscribe(new MyObserver<PurchaseDetailInfo>() {
                    @Override
                    public void onNext(PurchaseDetailInfo purchaseDetailInfo) {
                        mView.getPurchaseDetail(purchaseDetailInfo);
                    }
                });
    }

    @Override
    public void deletePurchase(int purId) {
        RetrofitService.deletePurchase(purId)
                .subscribe(new MyObserver<ResponseInfo>() {
                    @Override
                    public void onNext(ResponseInfo responseInfo) {
                        mView.deletePurchase();
                    }
                });
    }
}
