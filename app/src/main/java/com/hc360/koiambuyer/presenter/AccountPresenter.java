package com.hc360.koiambuyer.presenter;

import android.text.TextUtils;

import com.hc360.koiambuyer.api.MyObserver;
import com.hc360.koiambuyer.api.RetrofitService;
import com.hc360.koiambuyer.api.bean.InitInfo;
import com.hc360.koiambuyer.api.bean.LoginInfo;
import com.hc360.koiambuyer.api.bean.ResponseInfo;
import com.hc360.koiambuyer.model.Constant;
import com.hc360.koiambuyer.model.States;
import com.hc360.koiambuyer.myinterface.ipresenter.IAccountPresenter;
import com.hc360.koiambuyer.myinterface.iview.IAccountView;
import com.hc360.koiambuyer.utils.FormTool;
import com.hc360.koiambuyer.utils.SPUtils;
import com.hc360.koiambuyer.utils.ToastUtil;
import com.hc360.koiambuyer.view.MyApp;

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
                        mView.dialogShow("登录中");
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
                        mView.dialogShow("登录中");
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
                            mView.loginSuccess(loginInfo.content.loginRetType,loginInfo.content.retSsoUser.id+"");
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
            ToastUtil.showShort(MyApp.getAppContext(),"请输入正确的手机号");
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
                        mView.getUserStates(initInfo.content.initType);
                        int compId = initInfo.content.compId;
                        String userPhone = initInfo.content.userPhone;
                        String userName = initInfo.content.userName;
                        String userHeadImg = initInfo.content.userHeadImg;
                        String checkState = initInfo.content.checkState;
                        String isBoss = initInfo.content.isBoss+"";
                        SPUtils.saveString(MyApp.getAppContext(), Constant._ID, id);
                        MyApp.sUserId = id;
                        SPUtils.saveString(MyApp.getAppContext(),Constant._COM_ID,compId+"");

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
                        if (!TextUtils.isEmpty(checkState)){
                            SPUtils.saveString(MyApp.getAppContext(),Constant.CHECK_STATE,checkState);
                        }else{
                            SPUtils.saveString(MyApp.getAppContext(),Constant.CHECK_STATE,States.NO_CHECK_STATE);
                        }
                        if (!TextUtils.isEmpty(isBoss)){
                            SPUtils.saveBoolean(MyApp.getAppContext(),Constant._IS_BOSS,isBoss.equals("0"));
                        }else{
                            SPUtils.saveBoolean(MyApp.getAppContext(),Constant._IS_BOSS,false);
                        }
                        SPUtils.saveBoolean(MyApp.getAppContext(),Constant.HAVE_BUY_INTENT,initInfo.content.myStProductIntentionList.size()!=0);
                        MyApp.sComId = initInfo.content.compId+"";
                        mView.dialogDismiss();
                    }
                });
    }

}
