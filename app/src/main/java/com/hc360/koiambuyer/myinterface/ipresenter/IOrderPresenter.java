package com.hc360.koiambuyer.myinterface.ipresenter;


import com.hc360.koiambuyer.view.base.IBasePresenter;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/3
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public interface IOrderPresenter extends IBasePresenter {
    void getOrders(String orderState, int pager);
    void cancelOrder(String orderNo);
    void sureOrder(String orderNo);
}
