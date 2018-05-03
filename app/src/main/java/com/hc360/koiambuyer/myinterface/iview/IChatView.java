package com.hc360.koiambuyer.myinterface.iview;


import com.hc360.koiambuyer.api.bean.GoodsDetailInfo;
import com.hc360.koiambuyer.api.bean.RightNowInfo;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/14
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public interface IChatView {
    void getGoodsDetail(GoodsDetailInfo goodsDetailInfo, boolean sendProUrl);
    void getRightNow(RightNowInfo info);
    void loginSuccess();
}
