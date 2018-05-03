package com.hc360.koiambuyer.presenter;

import com.hc360.koiambuyer.api.MyObserver;
import com.hc360.koiambuyer.api.RetrofitService;
import com.hc360.koiambuyer.api.bean.CityManagerInfo;
import com.hc360.koiambuyer.api.bean.ResponseInfo;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.myinterface.ipresenter.ICityManagerPresenter;
import com.hc360.koiambuyer.myinterface.iview.ICityManagerView;
import com.hc360.koiambuyer.utils.SPUtils;
import com.hc360.koiambuyer.view.MyApp;

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

public class CityManagerPresenter implements ICityManagerPresenter{

    ICityManagerView mView;

    public CityManagerPresenter(ICityManagerView mView) {
        this.mView = mView;
    }

    @Override
    public void getCityManagers(String userName, int pager) {
        RetrofitService.getCityManagers(userName,pager)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.showLoading();
                    }
                })
                .subscribe(new MyObserver<CityManagerInfo>() {
                    @Override
                    public void onNext(CityManagerInfo info) {
                        mView.getCityManger(info);
                    }
                });
    }

    @Override
    public void selectCityManager(final int managerId) {
        RetrofitService.selectCityManager(MyApp.sUserId,managerId)
                .subscribe(new MyObserver<ResponseInfo>() {
                    @Override
                    public void onNext(ResponseInfo responseInfo) {
                        SPUtils.saveInt(MyApp.getAppContext(), Constant.REFERUSERID,managerId);
                        mView.selectCityManger();
                    }
                });
    }
}
