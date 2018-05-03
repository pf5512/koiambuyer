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
import com.hc360.koiambuyer.model.States;
import com.hc360.koiambuyer.myinterface.ipresenter.IAccountPresenter;
import com.hc360.koiambuyer.myinterface.iview.IAccountView;
import com.hc360.koiambuyer.utils.FormTool;
import com.hc360.koiambuyer.utils.SPUtils;
import com.hc360.koiambuyer.utils.ThreadUtil;
import com.hc360.koiambuyer.utils.ToastUtil;
import com.hc360.koiambuyer.view.MyApp;
import com.hyphenate.chat.EMClient;
import com.orhanobut.logger.Logger;

import rx.Observer;
import rx.functions.Action0;
import rx.functions.Func1;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/9/16
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class AccountPresenter implements IAccountPresenter {

    IAccountView mView;
    public AccountPresenter(IAccountView view) {
        this.mView = view;
    }

    @Override
    public void loginByPwd(final String userName, final String pwd) {
        RetrofitService.loginByPwd(userName,pwd)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.dialogShow(MyApp.getAppContext().getResources().getString(R.string.logining));
                    }
                })
                .subscribe(new Observer<LoginInfo>() {
                    @Override
                    public void onNext(LoginInfo loginInfo) {
                        if (loginInfo.ret.equals(States.STATES_RESULT_OK)){
                            mView.loginSuccess(loginInfo.content.loginRetType,loginInfo.content.retSsoUser.id+"");
                            SPUtils.saveInt(MyApp.getAppContext(),userName,0);
                        }else{
                            mView.dialogDismiss();
                            mView.loginCount(userName);
                            ToastUtil.showShort(MyApp.getAppContext(),loginInfo.msg);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.dialogDismiss();
                    }

                    @Override
                    public void onCompleted() {

                    }
                });

    }

    @Override
    public void loginByIdentify(String userName, String identify) {
        RetrofitService.loginByIdentifyAccount(userName,identify)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mView.dialogShow(MyApp.getAppContext().getResources().getString(R.string.logining));
                    }
                })
                .subscribe(new Observer<LoginInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.dialogDismiss();
                    }

                    @Override
                    public void onNext(LoginInfo loginInfo) {
                        if (loginInfo.ret.equals(States.STATES_RESULT_OK)){
                            login("iambuyer_"+loginInfo.content.retSsoUser.id,loginInfo);

                        }else{
                            mView.dialogDismiss();
                            ToastUtil.showShort(MyApp.getAppContext(),loginInfo.msg);
                        }
                    }
                });
    }

    /**
     * 其实是验证验证码
     * @param userName
     * @param identify
     */
    @Override
    public void register(final String userName, final String identify) {
        //注册操作，其实是检验验证码
        RetrofitService.confirmIdentify(userName,identify)
                .subscribe(new MyObserver<ResponseInfo>() {
                    @Override
                    public void onNext(ResponseInfo responseInfo) {
                        if (responseInfo.msg.equals("success")){
                            //验证码正确，跳转到设置密码界面
                            mView.registerSuccessByIdentify();
                        }else{
                            ToastUtil.showShort(MyApp.getAppContext(),responseInfo.msg);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.dialogDismiss();
                    }
                });
    }

    @Override
    public void sendIdentify(String phone, final String businessName) {
        if (!FormTool.isPhone(phone)){
            ToastUtil.showShort(MyApp.getAppContext(),MyApp.getAppContext().getResources().getString(R.string.input_right_phone));
            return;
        }
        RetrofitService.sendIdentify(phone, businessName)
                .filter(new Func1<ResponseInfo, Boolean>() {
                    @Override
                    public Boolean call(ResponseInfo responseInfo) {
                        return responseInfo.msg.equals("success");
                    }
                })
                .subscribe(new MyObserver<ResponseInfo>(){
                    @Override
                    public void onNext(ResponseInfo responseInfo) {
                        mView.sendSuccess(businessName);
                    }
                });
    }

    @Override
    public void getInitStates(final String id) {
        RetrofitService.getInitStates(id)
                .subscribe(new MyObserver<InitInfo>() {
                    @Override
                    public void onNext(InitInfo initInfo) {
                        mView.getUserStates(initInfo.content.user.role);
                        String userPhone = initInfo.content.user.phone;
                        String userName = initInfo.content.user.userName;
                        String userHeadImg = initInfo.content.user.headImg;
                        SPUtils.saveString(MyApp.getAppContext(), Constant._ID, id);
                        MyApp.sUserId = id;
                        if (!TextUtils.isEmpty(userPhone)){
                            SPUtils.saveString(MyApp.getAppContext(),Constant._PHONE,userPhone);
                            MyApp.sPhone = userPhone;
                        }else{
                            SPUtils.saveString(MyApp.getAppContext(),Constant._PHONE,"");
                            MyApp.sPhone = "";
                        }
                        SPUtils.saveString(MyApp.getAppContext(),Constant._LOGIN_TYPE,Constant.BUYER);
                        if (!TextUtils.isEmpty(userName)){
                            SPUtils.saveString(MyApp.getAppContext(),Constant._USER_NAME,userName);
                        }else{
                            SPUtils.saveString(MyApp.getAppContext(),Constant._USER_NAME,"");
                        }
                        if (!TextUtils.isEmpty(userHeadImg)){
                            SPUtils.saveString(MyApp.getAppContext(), Constant.USER_IMG,userHeadImg);
                        }
                        SPUtils.saveBoolean(MyApp.getAppContext(),Constant._IS_BOSS,true);
                        if (!TextUtils.isEmpty(initInfo.content.user.referPhone)){
                            SPUtils.saveString(MyApp.getAppContext(),Constant.REFER_PHONE,initInfo.content.user.referPhone);
                        }else {
                            SPUtils.saveString(MyApp.getAppContext(),Constant.REFER_PHONE,"");
                        }
                        mView.dialogDismiss();
                    }
                });
    }

    private void login(final String imId, final LoginInfo info) {
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
                                    mView.loginSuccess(info.content.loginRetType,info.content.retSsoUser.id+"");
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
                                        mView.loginSuccess(info.content.loginRetType,info.content.retSsoUser.id+"");
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
