package com.hc360.koiambuyer.myinterface.iview;


import com.hc360.koiambuyer.api.bean.AddressInfo;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/16
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public interface IEditShipAddressView {
    void getAddress(AddressInfo addressInfo);
    void saveAddress(int receiveId,String addStr,String userStr);
}
