package com.hc360.koiambuyer.myinterface.iview;


import com.hc360.koiambuyer.api.bean.SettingInfo;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/26
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public interface ISettingView {
    void getSettingInfo(SettingInfo info);
    void loginOutSuccess();
    void dialogShow(String msg);
    void dialogDismiss();
}
