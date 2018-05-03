package com.hc360.koiambuyer.presenter;

import com.hc360.koiambuyer.api.MyObserver;
import com.hc360.koiambuyer.api.RetrofitService;
import com.hc360.koiambuyer.api.bean.PostPicInfo;
import com.hc360.koiambuyer.api.bean.PublishPurchaseInfo;
import com.hc360.koiambuyer.api.bean.PurchaseDetailInfo;
import com.hc360.koiambuyer.myinterface.ipresenter.IPublishPresenter;
import com.hc360.koiambuyer.myinterface.iview.IPublishView;

import java.io.File;
import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/9/16
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class PublishPresenter implements IPublishPresenter {

    IPublishView mView;
    public PublishPresenter(IPublishView view) {
        this.mView = view;
    }


    @Override
    public void publishPurchase(String prodName, String prodClass, int prodNumber, double prodPrice, double prodHighPrice, int sampleProdNumber, String unit, String intro, List<String> pics, List<PurchaseDetailInfo.StProductParasBean> paramString, int prodId) {
        RetrofitService.publishPurchase(prodName,prodClass,prodNumber,prodPrice,prodHighPrice,sampleProdNumber,unit,intro,pics,paramString,prodId)
                .subscribe(new MyObserver<PublishPurchaseInfo>() {
                    @Override
                    public void onNext(PublishPurchaseInfo publishPurchaseInfo) {
                        mView.publishPurchase(publishPurchaseInfo);
                    }
                });
    }

    @Override
    public void getPurchase(int purId) {
        RetrofitService.getPurchaseDetail(purId)
                .subscribe(new MyObserver<PurchaseDetailInfo>() {
                    @Override
                    public void onNext(PurchaseDetailInfo info) {
                        mView.getPurchase(info);
                    }
                });
    }

    @Override
    public void postPic(File file) {
        RetrofitService.postPic(file)
                .subscribe(new MyObserver<PostPicInfo>() {
                    @Override
                    public void onNext(PostPicInfo postPicInfo) {
                        mView.postPic(postPicInfo);
                    }
                });
    }
}
