package com.hc360.koiambuyer.myinterface.ipresenter;


import com.hc360.koiambuyer.view.base.IBasePresenter;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/20
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public interface IGoodsDetailPresenter extends IBasePresenter {
    void getGoodsDetail(int productId, String userId);
    void attentionGood(int productUserId,int productId);
    void noAttentionGood(int productId);
}
