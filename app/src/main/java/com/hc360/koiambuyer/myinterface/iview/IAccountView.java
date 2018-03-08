package com.hc360.koiambuyer.myinterface.iview;


import com.hc360.koiambuyer.view.base.IBaseView;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/9/16
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public interface IAccountView extends IBaseView {
    void dialogShow(String msg);
    void dialogDismiss();
    void sendSuccess(String businessName);
    void loginSuccess(String msg, String id);
    void registerSuccessByIdentify();
    void getUserStates(String state);
    void loginCount(String phone);
}
