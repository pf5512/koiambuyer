package com.hc360.koiambuyer.myinterface.ipresenter;


import com.hc360.koiambuyer.view.base.IBasePresenter;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/16
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public interface IEditShipAddressPresenter extends IBasePresenter {
    void getAddress(int id);
    void saveAddress(int receiveId, int userId, String provinceCode, String cityCode, String addressDetail, String receiveUser, String telphone, String useState,String pName,String cName);
    void saveAddress(int userId, String provinceCode, String cityCode, String addressDetail, String receiveUser,String telphone, String useState,String pName,String cName);
}
