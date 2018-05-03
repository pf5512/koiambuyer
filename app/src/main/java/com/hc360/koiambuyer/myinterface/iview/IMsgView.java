package com.hc360.koiambuyer.myinterface.iview;


import com.hc360.koiambuyer.api.bean.MsgInfo;
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

public interface IMsgView extends IBaseView {
    void getMsgList(MsgInfo info);
    void setRead();
    void deleteMsg();
}
