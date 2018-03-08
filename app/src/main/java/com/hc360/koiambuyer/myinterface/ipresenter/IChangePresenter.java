package com.hc360.koiambuyer.myinterface.ipresenter;


import com.hc360.koiambuyer.view.base.IBasePresenter;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/26
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public interface IChangePresenter extends IBasePresenter {
    void sendIdentify(String phone, String businessName);
    void changePhone(int userId, String phone, String phoneCode, String businessNumber);
    void changePwd(String phone, String phoneCode, String businessNumber);
}
