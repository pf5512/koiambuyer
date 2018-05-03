package com.hc360.koiambuyer.engine;

import com.hc360.koiambuyer.api.bean.ChatListInfo;

import java.util.Comparator;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/4/8
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class ChatListTimeComparator implements Comparator<ChatListInfo.ContentBean>{
    @Override
    public int compare(ChatListInfo.ContentBean bean1, ChatListInfo.ContentBean bean2) {
        return (int) (bean2.EndTime-bean1.EndTime)/10000;
    }
}
