package com.hc360.koiambuyer.presenter;


import com.hc360.koiambuyer.api.MyObserver;
import com.hc360.koiambuyer.api.RetrofitService;
import com.hc360.koiambuyer.api.bean.SettingInfo;
import com.hc360.koiambuyer.myinterface.ipresenter.ISettingPresenter;
import com.hc360.koiambuyer.myinterface.iview.ISettingView;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/26
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class SettingPresenter implements ISettingPresenter {
    ISettingView mView;

    public SettingPresenter(ISettingView view) {
        this.mView = view;
    }

    @Override
    public void getSettingInfo(String userId) {
        RetrofitService.getSettingInfo(userId)
                .subscribe(new MyObserver<SettingInfo>() {
                    @Override
                    public void onNext(SettingInfo settingInfo) {
                        mView.getSettingInfo(settingInfo);
                    }
                });

    }

    @Override
    public void loginOut() {
        mView.dialogShow("退出中");
        mView.loginOutSuccess();
    }


}
