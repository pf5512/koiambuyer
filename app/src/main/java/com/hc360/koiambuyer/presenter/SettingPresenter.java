package com.hc360.koiambuyer.presenter;


import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.MyObserver;
import com.hc360.koiambuyer.api.RetrofitService;
import com.hc360.koiambuyer.api.bean.SettingInfo;
import com.hc360.koiambuyer.myinterface.ipresenter.ISettingPresenter;
import com.hc360.koiambuyer.myinterface.iview.ISettingView;
import com.hc360.koiambuyer.view.MyApp;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

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
    public void getSettingInfo() {
        RetrofitService.getSettingInfo()
                .subscribe(new MyObserver<SettingInfo>() {
                    @Override
                    public void onNext(SettingInfo settingInfo) {
                        mView.getSettingInfo(settingInfo);
                    }
                });

    }

    @Override
    public void loginOut() {
        EMClient.getInstance().logout(true, new EMCallBack() {

            @Override
            public void onSuccess() {

            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {

            }
        });
        mView.dialogShow(MyApp.getAppContext().getResources().getString(R.string.login_out));
        mView.loginOutSuccess();
    }
}
