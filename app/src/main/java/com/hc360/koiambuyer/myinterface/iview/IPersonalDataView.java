package com.hc360.koiambuyer.myinterface.iview;


import com.hc360.koiambuyer.api.bean.UserBaseInfo;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/24
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public interface IPersonalDataView {
    void postPic(String name);
    void updateInfo(String type);
    void getUserBaseInfo(UserBaseInfo info);
}
