package com.hc360.koiambuyer.presenter;


import com.hc360.koiambuyer.api.MyObserver;
import com.hc360.koiambuyer.api.RetrofitService;
import com.hc360.koiambuyer.api.bean.LikeInfo;
import com.hc360.koiambuyer.myinterface.ipresenter.ILikePresenter;
import com.hc360.koiambuyer.myinterface.iview.ILikeView;
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

public class LikePresenter implements ILikePresenter {

    ILikeView mView;

    public LikePresenter(ILikeView mView) {
        this.mView = mView;
    }

    @Override
    public void getGoods(int pager) {
        RetrofitService.getLikeGoods(MyApp.sUserId,pager)
                .subscribe(new MyObserver<LikeInfo>() {
                    @Override
                    public void onNext(LikeInfo likeInfo) {
                        mView.getGoods(likeInfo);
                    }
                });
    }
}
