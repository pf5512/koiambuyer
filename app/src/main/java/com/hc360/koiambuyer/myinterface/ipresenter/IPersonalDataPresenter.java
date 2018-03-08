package com.hc360.koiambuyer.myinterface.ipresenter;


import com.hc360.koiambuyer.view.base.IBasePhotoPresenter;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/24
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public interface IPersonalDataPresenter extends IBasePhotoPresenter {
    void updateInfo(int userId, String msg, String type);
    void getUserBaseInfo(String userId);
}
