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
    void saveAddress(int deliverId, int compId, String provinceCode, String cityCode, String areaCode, String addressDetail, String useState);
    void saveAddress(int compId, String provinceCode, String cityCode, String areaCode, String addressDetail, String useState);
}
