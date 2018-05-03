package com.hc360.koiambuyer.myinterface.iview;


import com.hc360.koiambuyer.api.bean.OrderDetailInfo;
import com.hc360.koiambuyer.view.base.IBaseView;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/9/16
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public interface IOrderDetailView extends IBaseView {
    void getOrderDetail(OrderDetailInfo info);
    void cancelSuccess();
    void sureSuccess();

}
