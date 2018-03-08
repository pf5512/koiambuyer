package com.hc360.koiambuyer.presenter;


import com.hc360.koiambuyer.api.MyObserver;
import com.hc360.koiambuyer.api.RetrofitService;
import com.hc360.koiambuyer.api.bean.PostPicInfo;
import com.hc360.koiambuyer.api.bean.ResponseInfo;
import com.hc360.koiambuyer.api.bean.UserBaseInfo;
import com.hc360.koiambuyer.myinterface.ipresenter.IPersonalDataPresenter;
import com.hc360.koiambuyer.myinterface.iview.IPersonalDataView;

import java.io.File;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/24
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class PersonalDataPresenter implements IPersonalDataPresenter {

    IPersonalDataView mView;

    public PersonalDataPresenter(IPersonalDataView view) {
        this.mView = view;
    }
    @Override
    public void postPic(File file) {
        RetrofitService.postPic(file)
                .subscribe(new MyObserver<PostPicInfo>() {
                    @Override
                    public void onNext(PostPicInfo postPicInfo) {
                        mView.postPic(postPicInfo.name);
                    }
                });
    }

    @Override
    public void updateInfo(int userId, String msg, final String type) {
        RetrofitService.updateInfo(userId,msg,type)
                .subscribe(new MyObserver<ResponseInfo>() {
                    @Override
                    public void onNext(ResponseInfo responseInfo) {
                        mView.updateInfo(type);
                    }
                });
    }

    @Override
    public void getUserBaseInfo(String userId) {
        RetrofitService.getUserBaseInfo(userId)
                .subscribe(new MyObserver<UserBaseInfo>() {
                    @Override
                    public void onNext(UserBaseInfo userBaseInfo) {
                        mView.getUserBaseInfo(userBaseInfo);
                    }
                });
    }

}
