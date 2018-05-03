package com.hc360.koiambuyer.presenter;

import android.text.TextUtils;

import com.hc360.koiambuyer.R;
import com.hc360.koiambuyer.api.MyObserver;
import com.hc360.koiambuyer.api.RetrofitService;
import com.hc360.koiambuyer.api.bean.InitInfo;
import com.hc360.koiambuyer.api.bean.LoginInfo;
import com.hc360.koiambuyer.api.bean.MyEMCallBack;
import com.hc360.koiambuyer.api.bean.ResponseInfo;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.myinterface.ipresenter.ISettingPwdPresenter;
import com.hc360.koiambuyer.myinterface.iview.ISettingPwdView;
import com.hc360.koiambuyer.utils.FormTool;
import com.hc360.koiambuyer.utils.SPUtils;
import com.hc360.koiambuyer.utils.ThreadUtil;
import com.hc360.koiambuyer.utils.ToastUtil;
import com.hc360.koiambuyer.view.MyApp;
import com.hyphenate.chat.EMClient;
import com.orhanobut.logger.Logger;

import rx.Observable;
import rx.Observer;
import rx.functions.Action0;
import rx.functions.Func1;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/9/27
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class SettingPwdPresenter implements ISettingPwdPresenter {
    ISettingPwdView mView;

    public SettingPwdPresenter(ISettingPwdView mView) {
        this.mView = mView;
    }

    @Override
    public void settingPwd(final String phone, final String setText, String confirmText) {
        if (TextUtils.isEmpty(setText) || TextUtils.isEmpty(confirmText)) {
            ToastUtil.showShort(MyApp.getAppContext(),getStr(R.string.pwd_empty));
            return;
        }
        if (setText.equals(confirmText)) {
            if (setText.length() > 5 && setText.length() < 19) {
                if (FormTool.isNumAndChar(setText)) {
                    //格式正确，进行注册
                    RetrofitService.register(phone, setText)
                            .doOnSubscribe(new Action0() {
                                @Override
                                public void call() {
                                    //注册中
                                }
                            })
                            .flatMap(new Func1<ResponseInfo, Observable<LoginInfo>>() {
                                @Override
                                public Observable<LoginInfo> call(ResponseInfo responseInfo) {
                                    return RetrofitService.loginByPwd(phone, setText);
                                }
                            })
                            .flatMap(new Func1<LoginInfo, Observable<InitInfo>>() {
                                @Override
                                public Observable<InitInfo> call(LoginInfo loginInfo) {
                                    return RetrofitService.getInitStates(loginInfo.content.retSsoUser.id + "");
                                }
                            })
                            .subscribe(new Observer<InitInfo>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onNext(InitInfo info) {
                                    save(info);
                                    mView.updateSuccess(String.valueOf(info.content.user.referUserid));
                                }
                            });
                } else {
                    ToastUtil.showShort(MyApp.getAppContext(),  getStr(R.string.pwd_form));
                }
            } else {
                ToastUtil.showShort(MyApp.getAppContext(),  getStr(R.string.pwd_six));
            }
        } else {
            ToastUtil.showShort(MyApp.getAppContext(), getStr(R.string.pwd_twin_error));
        }
    }

    @Override
    public void updatePwd(final String id, String pwd, String secondPwd) {
        if (TextUtils.isEmpty(pwd) || TextUtils.isEmpty(secondPwd)) {
            ToastUtil.showShort(MyApp.getAppContext(), getStr(R.string.pwd_empty));
            return;
        }
        if (pwd.equals(secondPwd)) {
            if (pwd.length() > 5 && pwd.length() < 19) {
                if (FormTool.isNumAndChar(pwd)) {
                    RetrofitService.updateLoginPwd(new Integer(id), pwd)
                            .doOnSubscribe(new Action0() {
                                @Override
                                public void call() {

                                }
                            })
                            .flatMap(new Func1<ResponseInfo, Observable<InitInfo>>() {
                                @Override
                                public Observable<InitInfo> call(ResponseInfo responseInfo) {
                                    return RetrofitService.getInitStates(id);
                                }
                            })
                            .subscribe(new Observer<InitInfo>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onNext(InitInfo info) {
                                    save(info);
                                    mView.updateSuccess(String.valueOf(info.content.user.referUserid));
                                }
                            });
                } else {
                    ToastUtil.showShort(MyApp.getAppContext(), getStr(R.string.pwd_form));
                }

            } else {
                ToastUtil.showShort(MyApp.getAppContext(), getStr(R.string.pwd_six));
            }
        } else {
            ToastUtil.showShort(MyApp.getAppContext(), getStr(R.string.pwd_twin_error));
        }
    }

    /**
     * 验证码登录返回200，需要更新密码
     * @param id
     * @param password
     */
    @Override
    public void updatePwd(final String id, String password) {
        RetrofitService.updatePwd(id, password)
                .flatMap(new Func1<ResponseInfo, Observable<InitInfo>>() {
                    @Override
                    public Observable<InitInfo> call(ResponseInfo responseInfo) {
                        return RetrofitService.getInitStates(id);
                    }
                })
                .subscribe(new MyObserver<InitInfo>() {
                    @Override
                    public void onNext(InitInfo initInfo) {

                        login(""+initInfo.content.user.userId,initInfo);
//                        mView.updateSuccess(String.valueOf(initInfo.content.user.referUserid));
                    }
                });
    }

    private void save(InitInfo info) {
        String userPhone = info.content.user.phone;
        String userName = info.content.user.userName;
        String userHeadImg = info.content.user.headImg;
        SPUtils.saveString(MyApp.getAppContext(), Constant._ID, info.content.user.ssoUserId + "");
        MyApp.sUserId = info.content.user.ssoUserId + "";

        if (!TextUtils.isEmpty(userPhone)) {
            SPUtils.saveString(MyApp.getAppContext(), Constant._PHONE, userPhone);
            MyApp.sPhone = userPhone;
        }else{
            SPUtils.saveString(MyApp.getAppContext(),Constant._PHONE,"");
            MyApp.sPhone = "";
        }
        SPUtils.saveString(MyApp.getAppContext(), Constant._LOGIN_TYPE, Constant.BUYER);
        if (!TextUtils.isEmpty(userName)) {
            SPUtils.saveString(MyApp.getAppContext(), Constant._USER_NAME, userName);
        } else {
            SPUtils.saveString(MyApp.getAppContext(), Constant._USER_NAME, "");
        }
        if (!TextUtils.isEmpty(userHeadImg)) {
            SPUtils.saveString(MyApp.getAppContext(), Constant.USER_IMG, userHeadImg);
        }
        SPUtils.saveBoolean(MyApp.getAppContext(),Constant._IS_BOSS,true);
        if (info.content.user.referUserid>0){
            SPUtils.saveInt(MyApp.getAppContext(),Constant.REFERUSERID,info.content.user.referUserid);
        }
        if (!TextUtils.isEmpty(info.content.user.referPhone)){
            SPUtils.saveString(MyApp.getAppContext(),Constant.REFER_PHONE,info.content.user.referPhone);
        }else {
            SPUtils.saveString(MyApp.getAppContext(),Constant.REFER_PHONE,"");
        }
    }

    public String getStr(int strRes){
        return MyApp.getAppContext().getResources().getString(strRes);
    }

    private void login(final String imId, final InitInfo info) {
        // TODO Auto-generated method stub
        ThreadUtil.toToOnSubThread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().login(imId, Constant.IM_PWD, new MyEMCallBack() {
                        @Override
                        public void onSuccess() {
                            //环信登录成功
                            ThreadUtil.toToOnMainThread(new Runnable() {
                                @Override
                                public void run() {
                                    Logger.e("登录成功");
                                    mView.updateSuccess(String.valueOf(info.content.user.referUserid));
                                    save(info);
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
                                        mView.updateSuccess(String.valueOf(info.content.user.referUserid));
                                        save(info);
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
