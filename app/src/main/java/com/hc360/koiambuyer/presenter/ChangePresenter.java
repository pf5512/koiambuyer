package com.hc360.koiambuyer.presenter;


import com.hc360.koiambuyer.api.MyObserver;
import com.hc360.koiambuyer.api.RetrofitService;
import com.hc360.koiambuyer.api.bean.ResponseInfo;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.model.States;
import com.hc360.koiambuyer.myinterface.ipresenter.IChangePresenter;
import com.hc360.koiambuyer.myinterface.iview.IChangeView;
import com.hc360.koiambuyer.utils.SPUtils;
import com.hc360.koiambuyer.view.MyApp;
import com.orhanobut.logger.Logger;

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

public class ChangePresenter implements IChangePresenter {

    IChangeView mView;

    public ChangePresenter(IChangeView view) {
        this.mView = view;
    }


    @Override
    public void sendIdentify(String phone, String businessName ) {
        RetrofitService.identify(phone,businessName)
                .subscribe(new MyObserver<ResponseInfo>() {
                    @Override
                    public void onNext(ResponseInfo responseInfo) {
                        if (responseInfo.ret.equals(States.STATES_RESULT_OK)){
                            mView.sendIdentify();
                        }else{
                            if (responseInfo.msg.equals("该手机号没有被注册过")){
                                mView.toRegister();
                            }
                        }
                    }
                });
    }

    @Override
    public void changePhone(final int userId, final String phone, String phoneCode, String businessNumber) {
        //先验证验证码，再修改手机号
        Logger.e(userId+phone);
        RetrofitService.checkIdentify(phone,phoneCode,businessNumber)
                .flatMap(new Func1<ResponseInfo, Observable<ResponseInfo>>() {
                    @Override
                    public Observable<ResponseInfo> call(ResponseInfo responseInfo) {
                        return RetrofitService.update(userId,phone, Constant.PHONE);
                    }
                })
                .subscribe(new MyObserver<ResponseInfo>() {
                    @Override
                    public void onNext(ResponseInfo responseInfo) {
                        SPUtils.saveString(MyApp.getAppContext(),Constant._PHONE,phone);
                        MyApp.sPhone = phone;
                        mView.submit();
                    }
                });

    }

    @Override
    public void changePwd(final String phone, String phoneCode, String businessNumber) {
        //先验证验证码
        RetrofitService.checkIdentify(phone,phoneCode,businessNumber)
                .subscribe(new MyObserver<ResponseInfo>() {
                    @Override
                    public void onNext(ResponseInfo responseInfo) {
                        mView.submit();
                    }
                });
    }

}
