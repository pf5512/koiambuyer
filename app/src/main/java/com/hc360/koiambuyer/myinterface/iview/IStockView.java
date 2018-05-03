package com.hc360.koiambuyer.myinterface.iview;


import com.hc360.koiambuyer.api.bean.AddressInfo;
import com.hc360.koiambuyer.api.bean.OrderDetailInfo;
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

public interface IStockView extends IBaseView {
    void getAddress(ShipAddressInfo info);
    void getAddress(AddressInfo info);
    void submitSuccess();
    void getErrorAddress();
    void getOrderDetail(OrderDetailInfo info);

}
