package com.hc360.koiambuyer.presenter;


import com.hc360.koiambuyer.api.MyObserver;
import com.hc360.koiambuyer.api.RetrofitService;
import com.hc360.koiambuyer.api.bean.GoodsDetailInfo;
import com.hc360.koiambuyer.api.bean.MyEMCallBack;
import com.hc360.koiambuyer.api.bean.RightNowInfo;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.myinterface.ipresenter.IChatPresenter;
import com.hc360.koiambuyer.myinterface.iview.IChatView;
import com.hc360.koiambuyer.utils.ThreadUtil;
import com.hc360.koiambuyer.utils.ToastUtil;
import com.hc360.koiambuyer.view.MyApp;
import com.hyphenate.chat.EMClient;
import com.orhanobut.logger.Logger;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/14
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class ChatPresenter implements IChatPresenter {

    IChatView mView;

    public ChatPresenter(IChatView mView) {
        this.mView = mView;
    }

    @Override
    public void rightNow(int proid, int userid, int type) {
        RetrofitService.getRightNow(proid,userid,type)
                .subscribe(new MyObserver<RightNowInfo>() {
                    @Override
                    public void onNext(RightNowInfo info) {
                        mView.getRightNow(info);
                    }
                });
    }


    @Override
    public void getGoodsDetail(int productId, String userId, final boolean sendProUrl) {
        RetrofitService.getGoodsDetail(productId,userId)
                .subscribe(new MyObserver<GoodsDetailInfo>() {
                    @Override
                    public void onNext(GoodsDetailInfo goodsDetailInfo) {
                        mView.getGoodsDetail(goodsDetailInfo,sendProUrl);
                    }
                });
    }

    @Override
    public void loginIm(final String imId) {
        //先退出，再登录，防止其他设备登录
        login(imId);
    }

    private void login(final String imId) {
        // TODO Auto-generated method stub
        ThreadUtil.toToOnSubThread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().login(imId, Constant.IM_PWD, new MyEMCallBack() {
                        @Override
                        public void onSuccess() {
                            //环信登录成功
                            //保存信息
                            ThreadUtil.toToOnMainThread(new Runnable() {
                                @Override
                                public void run() {
                                    Logger.e("登录成功");
                                    mView.loginSuccess();
                                }
                            });
                        }
                        @Override
                        public void onError(int code, final String message) {
                            ThreadUtil.toToOnMainThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (message.equals("User is already login")){
                                        Logger.e("重复登录");
                                        mView.loginSuccess();
                                    }else{
                                        ToastUtil.showShort(MyApp.getAppContext(),message);
                                    }
                                }
                            });
                        }
                    });

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

}
