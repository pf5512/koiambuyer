package com.hc360.koiambuyer.myinterface.iview;


import com.hc360.koiambuyer.api.bean.QuoteInfo;
import com.hc360.koiambuyer.view.base.IBaseView;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/3
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public interface IQuoteView extends IBaseView {
    void getGoods(QuoteInfo info);
    void setIntent();
}
