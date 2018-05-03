package com.hc360.koiambuyer.myinterface.ipresenter;


import com.hc360.koiambuyer.api.bean.PurchaseDetailInfo;
import com.hc360.koiambuyer.view.base.IBasePhotoPresenter;

import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/9/16
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public interface IPublishPresenter extends IBasePhotoPresenter {
    void publishPurchase(String prodName, String prodClass, int prodNumber, double prodPrice, double prodHighPrice, int sampleProdNumber, String unit, String intro, List<String> pics, List<PurchaseDetailInfo.StProductParasBean> paramString, int prodId);
    void getPurchase(int purId);
}
