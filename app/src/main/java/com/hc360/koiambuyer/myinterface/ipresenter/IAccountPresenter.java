package com.hc360.koiambuyer.myinterface.ipresenter;


import com.hc360.koiambuyer.view.base.IBasePresenter;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/9/16
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public interface IAccountPresenter extends IBasePresenter {
    void loginByPwd(String userName, String pwd);
    void loginByIdentify(String userName, String identify);
    void register(String userName, String identify);
    void sendIdentify(String phone, String businessName);
    void getInitStates(String id);
}
