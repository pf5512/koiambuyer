package com.hc360.koiambuyer.myinterface.iview;


import com.hc360.koiambuyer.api.bean.ShipAddressInfo;
import com.hc360.koiambuyer.view.base.IBaseView;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/13
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public interface IShipAddressView extends IBaseView {
    void getAddress(ShipAddressInfo info);
    void deleteAddress(int id);
    void deleteAddress();
    void setDefaultAddress(int id, int comId, String useState);
    void setDefaultAddress();
    void editAddress(int id);
    void selectAddress(String addStr, int deliverId);
}
