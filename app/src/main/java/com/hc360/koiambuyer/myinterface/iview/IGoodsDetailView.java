package com.hc360.koiambuyer.myinterface.iview;


import com.hc360.koiambuyer.api.bean.GoodsDetailInfo;
import com.hc360.koiambuyer.api.bean.ResponseInfo;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/20
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public interface IGoodsDetailView {
    void getData(GoodsDetailInfo goodsDetailInfo);
    void attentionSuccess(ResponseInfo responseInfo);
    void noAttentionSuccess();
}
