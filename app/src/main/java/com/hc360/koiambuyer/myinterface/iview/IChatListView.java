package com.hc360.koiambuyer.myinterface.iview;

import com.hc360.koiambuyer.api.bean.ChatListInfo;
import com.hc360.koiambuyer.api.bean.ChatMsgInfo;
import com.hc360.koiambuyer.view.base.IBaseView;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/4/8
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public interface IChatListView extends IBaseView{
    void getChatList(ChatListInfo info);
    void getChatMsg(ChatMsgInfo info);
}
