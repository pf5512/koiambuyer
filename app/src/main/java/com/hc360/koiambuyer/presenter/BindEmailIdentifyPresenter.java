package com.hc360.koiambuyer.presenter;


import com.hc360.koiambuyer.api.MyObserver;
import com.hc360.koiambuyer.api.RetrofitService;
import com.hc360.koiambuyer.api.bean.ResponseInfo;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.myinterface.ipresenter.IBindEmailIdentifyPresenter;
import com.hc360.koiambuyer.myinterface.iview.IBindEmailIdentifyView;
import com.hc360.koiambuyer.view.MyApp;

import rx.Observable;
import rx.functions.Func1;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/26
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class BindEmailIdentifyPresenter implements IBindEmailIdentifyPresenter {

    IBindEmailIdentifyView mView;

    public BindEmailIdentifyPresenter(IBindEmailIdentifyView view) {
        this.mView = view;
    }

    @Override
    public void sendEmail(String email, String businessName) {
        RetrofitService.sendEmail(email,businessName)
                .subscribe(new MyObserver<ResponseInfo>() {
                    @Override
                    public void onNext(ResponseInfo responseInfo) {
                        mView.sendEmail();
                    }
                });
    }

    @Override
    public void checkEmail(final String email, String businessName, String emailCode) {
        RetrofitService.checkEmail(email,businessName,emailCode)
                .flatMap(new Func1<ResponseInfo, Observable<ResponseInfo>>() {
                    @Override
                    public Observable<ResponseInfo> call(ResponseInfo responseInfo) {
                        return RetrofitService.update(new Integer(MyApp.sUserId),email, Constant.EMAIL);
                    }
                })
                .subscribe(new MyObserver<ResponseInfo>() {
                    @Override
                    public void onNext(ResponseInfo responseInfo) {
                        mView.checkEmail();
                    }
                });
    }

}
