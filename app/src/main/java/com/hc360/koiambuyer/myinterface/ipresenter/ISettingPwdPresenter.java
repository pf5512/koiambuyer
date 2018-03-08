package com.hc360.koiambuyer.myinterface.ipresenter;


import com.hc360.koiambuyer.view.base.IBasePresenter;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/9/27
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public interface ISettingPwdPresenter extends IBasePresenter {
    void settingPwd(String phone, String setText, String confirmText);
    void updatePwd(String id, String pwd, String secondPwd);
    void updatePwd(String id, String password);
}
