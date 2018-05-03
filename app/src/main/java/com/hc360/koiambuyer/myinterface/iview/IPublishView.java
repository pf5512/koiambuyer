package com.hc360.koiambuyer.myinterface.iview;

import com.hc360.koiambuyer.api.bean.PostPicInfo;
import com.hc360.koiambuyer.api.bean.PublishPurchaseInfo;
import com.hc360.koiambuyer.api.bean.PurchaseDetailInfo;
import com.hc360.koiambuyer.view.base.IBaseView;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/22
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public interface IPublishView extends IBaseView{
    void publishPurchase(PublishPurchaseInfo info);
    void postPic(PostPicInfo info);
    void getPurchase(PurchaseDetailInfo info);
}
