package com.hc360.koiambuyer.presenter;


import com.hc360.koiambuyer.api.MyObserver;
import com.hc360.koiambuyer.api.RetrofitService;
import com.hc360.koiambuyer.api.bean.MeInfo;
import com.hc360.koiambuyer.myinterface.ipresenter.IMePresenter;
import com.hc360.koiambuyer.myinterface.iview.IMeView;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/9/21
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class MePresenter implements IMePresenter {

    IMeView mView;

    public MePresenter(IMeView view) {
        this.mView = view;
    }


    @Override
    public void getMeInfo() {
        RetrofitService.getMeInfo()
                .subscribe(new MyObserver<MeInfo>() {
                    @Override
                    public void onNext(MeInfo meInfo) {
                        mView.getMeInfo(meInfo);
                    }
                });
    }
}
