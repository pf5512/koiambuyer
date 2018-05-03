package com.hc360.koiambuyer.presenter;


import com.hc360.koiambuyer.api.MyObserver;
import com.hc360.koiambuyer.api.RetrofitService;
import com.hc360.koiambuyer.api.bean.MsgInfo;
import com.hc360.koiambuyer.api.bean.ResponseInfo;
import com.hc360.koiambuyer.myinterface.ipresenter.IMsgPresenter;
import com.hc360.koiambuyer.myinterface.iview.IMsgView;
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

public class MsgPresenter implements IMsgPresenter {
    IMsgView mView;

    public MsgPresenter(IMsgView mView) {
        this.mView = mView;
    }

    @Override
    public void getMsgList(int pager) {
        RetrofitService.getMsgs(MyApp.sUserId,pager)
                .subscribe(new MyObserver<MsgInfo>() {
                    @Override
                    public void onNext(MsgInfo msgInfo) {
                        mView.getMsgList(msgInfo);
                    }
                });
    }

    @Override
    public void setRead(String msgIds) {
        RetrofitService.setRead(msgIds)
                .subscribe(new MyObserver<ResponseInfo>() {
                    @Override
                    public void onNext(ResponseInfo responseInfo) {
                        mView.setRead();
                    }
                });
    }

    @Override
    public void deleteMsg(String msgIds) {
        RetrofitService.deleteMsg(msgIds)
                .subscribe(new MyObserver<ResponseInfo>() {
                    @Override
                    public void onNext(ResponseInfo responseInfo) {
                        mView.deleteMsg();
                    }
                });
    }
}
