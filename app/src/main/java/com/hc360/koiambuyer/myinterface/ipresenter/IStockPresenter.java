package com.hc360.koiambuyer.myinterface.ipresenter;


import com.hc360.koiambuyer.view.base.IBasePresenter;

import java.math.BigDecimal;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/13
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public interface IStockPresenter extends IBasePresenter {
    void getAddresses();
    void getAddresses(int receiveId);
    void getOrderDetail(String orderNo);
    void submit(int proId, int goodsId, int orderCount, BigDecimal orderUnitpri, BigDecimal orderPrice, int orderSimcount, BigDecimal orderSimunitpri, BigDecimal orderSimprice, String orderContent, BigDecimal moneyMap);
}
