package com.hc360.koiambuyer.presenter;

import android.text.TextUtils;

import com.hc360.koiambuyer.api.MyObserver;
import com.hc360.koiambuyer.api.RetrofitService;
import com.hc360.koiambuyer.api.bean.InitInfo;
import com.hc360.koiambuyer.api.bean.LoginInfo;
import com.hc360.koiambuyer.api.bean.ResponseInfo;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.model.States;
import com.hc360.koiambuyer.myinterface.ipresenter.ISettingPwdPresenter;
import com.hc360.koiambuyer.myinterface.iview.ISettingPwdView;
import com.hc360.koiambuyer.utils.FormTool;
import com.hc360.koiambuyer.utils.SPUtils;
import com.hc360.koiambuyer.utils.ToastUtil;
import com.hc360.koiambuyer.view.MyApp;

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
            ToastUtil.showShort(MyApp.getAppContext(), "输入不能为空");
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
                                    mView.updateSuccess(info.content.initType);
                                }
                            });
                } else {
                    ToastUtil.showShort(MyApp.getAppContext(), "密码须是数字和字母的组合");
                }
            } else {
                ToastUtil.showShort(MyApp.getAppContext(), "请输入6~18位密码");
            }
        } else {
            ToastUtil.showShort(MyApp.getAppContext(), "两次密码不一样");
        }
    }

    @Override
    public void updatePwd(final String id, String pwd, String secondPwd) {
        if (TextUtils.isEmpty(pwd) || TextUtils.isEmpty(secondPwd)) {
            ToastUtil.showShort(MyApp.getAppContext(), "输入不能为空");
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
                                    mView.updateSuccess(info.content.initType);
                                }
                            });
                } else {
                    ToastUtil.showShort(MyApp.getAppContext(), "密码须是数字和字母的组合");
                }

            } else {
                ToastUtil.showShort(MyApp.getAppContext(), "请输入6~18位密码");
            }
        } else {
            ToastUtil.showShort(MyApp.getAppContext(), "两次密码不一样");
        }
    }

    /**
     * 验证码登录返回200，需要更新密码
     *
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
                        save(initInfo);
                        mView.updateSuccess(initInfo.content.initType);
                    }
                });
    }



    private void save(InitInfo info) {
        int compId = info.content.compId;
        String userPhone = info.content.userPhone;
        String userName = info.content.userName;
        String userHeadImg = info.content.userHeadImg;
        String checkState = info.content.checkState;
        String isBoss = info.content.isBoss + "";
        SPUtils.saveString(MyApp.getAppContext(), Constant._ID, info.content.userId + "");
        MyApp.sUserId = info.content.userId + "";

        SPUtils.saveString(MyApp.getAppContext(), Constant._COM_ID, compId + "");

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
        if (!TextUtils.isEmpty(checkState)) {
            SPUtils.saveString(MyApp.getAppContext(), Constant.CHECK_STATE, checkState);
        }else{
            SPUtils.saveString(MyApp.getAppContext(),Constant.CHECK_STATE, States.NO_CHECK_STATE);
        }
        if (!TextUtils.isEmpty(isBoss)) {
            SPUtils.saveBoolean(MyApp.getAppContext(), Constant._IS_BOSS, isBoss.equals("0"));
        }else{
            SPUtils.saveBoolean(MyApp.getAppContext(),Constant._IS_BOSS,false);
        }
    }
}
