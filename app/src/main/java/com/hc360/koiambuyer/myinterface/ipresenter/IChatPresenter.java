package com.hc360.koiambuyer.myinterface.ipresenter;


import com.hc360.koiambuyer.view.base.IBasePresenter;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/14
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public interface IChatPresenter extends IBasePresenter {
    void rightNow(int proid, int userid, int type);
    void getGoodsDetail(int productId, String userId, boolean sendProUrl);
    void loginIm(String imId);
}
