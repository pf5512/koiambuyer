package com.hc360.koiambuyer.myinterface.iview;


import com.hc360.koiambuyer.api.bean.ChoiceSellerInfo;
import com.hc360.koiambuyer.api.bean.HotInfo;
import com.hc360.koiambuyer.api.bean.SearchInfo;
import com.hc360.koiambuyer.view.base.IBaseView;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/2
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public interface ISearchView extends IBaseView {
    void getHot(HotInfo info);
    void getCompany(ChoiceSellerInfo info);
    void getSearchGoods(SearchInfo info);
}
