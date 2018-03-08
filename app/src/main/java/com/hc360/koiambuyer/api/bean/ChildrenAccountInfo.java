package com.hc360.koiambuyer.api.bean;

import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/16
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class ChildrenAccountInfo {

    public int count;
    public List<ListBean> list;

    public static class ListBean {
        public int compId;
        public String userHeadImg;
        public int userId;
        public long applyTime;
        public long checkTime;
        public String userName;
    }
}
