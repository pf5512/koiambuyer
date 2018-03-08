package com.hc360.koiambuyer.myinterface.ipresenter;


import com.hc360.koiambuyer.view.base.IBasePresenter;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/2
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public interface ISearchPresenter extends IBasePresenter {
    void getHot();
    void getCompany(String compName, int pager);
    void getSearchGoods(String productName, int pager);
}
