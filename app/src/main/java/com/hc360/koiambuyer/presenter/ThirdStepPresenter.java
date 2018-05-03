package com.hc360.koiambuyer.presenter;


import com.hc360.koiambuyer.api.MyObserver;
import com.hc360.koiambuyer.api.RetrofitService;
import com.hc360.koiambuyer.api.bean.ResponseInfo;
import com.hc360.koiambuyer.myinterface.ipresenter.IThirdStepPresenter;
import com.hc360.koiambuyer.myinterface.iview.IThirdStepView;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/26
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class ThirdStepPresenter implements IThirdStepPresenter {

    IThirdStepView mView;

    public ThirdStepPresenter(IThirdStepView view) {
        this.mView = view;
    }


    @Override
    public void setPwd(String phone, String pwd) {
        RetrofitService.forgetPwd(phone,pwd)
                .subscribe(new MyObserver<ResponseInfo>() {
                    @Override
                    public void onNext(ResponseInfo responseInfo) {
                        mView.setPwdSuccess();
                    }
                });
    }
}
