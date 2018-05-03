package com.hc360.koiambuyer.presenter;

import android.text.TextUtils;

import com.hc360.koiambuyer.api.MyObserver;
import com.hc360.koiambuyer.api.RetrofitService;
import com.hc360.koiambuyer.api.bean.InitInfo;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.myinterface.ipresenter.IHomePresenter;
import com.hc360.koiambuyer.myinterface.iview.IHomeView;
import com.hc360.koiambuyer.utils.SPUtils;
import com.hc360.koiambuyer.view.MyApp;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/9/16
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class HomePresenter implements IHomePresenter {

    IHomeView mView;
    public HomePresenter(IHomeView view) {
        this.mView = view;
    }

    @Override
    public void getInitStates(final String id) {
        RetrofitService.getInitStates(id)
                .subscribe(new MyObserver<InitInfo>() {
                    @Override
                    public void onNext(InitInfo initInfo) {
                        if (!TextUtils.isEmpty(initInfo.content.user.referPhone)){
                            SPUtils.saveString(MyApp.getAppContext(), Constant.REFER_PHONE,initInfo.content.user.referPhone);
                        }
                    }
                });
    }
}
