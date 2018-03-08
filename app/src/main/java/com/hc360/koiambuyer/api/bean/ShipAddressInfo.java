package com.hc360.koiambuyer.api.bean;

import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/13
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class ShipAddressInfo {

    public List<ListBean> list;

    public static class ListBean {
        public String addStr;
        public int deliverId;
        public String deliverUser;
        public String telphone;
        public int compId;
        public String provinceCode;
        public String cityCode;
        public String areaCode;
        public String addressDetail;
        public String useState;
        public String states;
        public long createTime;
        public String createMan;
        public long updateTime;
        public String updateMan;
    }
}
