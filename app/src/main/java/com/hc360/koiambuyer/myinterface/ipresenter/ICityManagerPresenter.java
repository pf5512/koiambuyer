package com.hc360.koiambuyer.myinterface.ipresenter;

import com.hc360.koiambuyer.view.base.IBasePresenter;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/7
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public interface ICityManagerPresenter extends IBasePresenter {
    void getCityManagers(String userName,int pager);
    void selectCityManager(int managerId);
}
