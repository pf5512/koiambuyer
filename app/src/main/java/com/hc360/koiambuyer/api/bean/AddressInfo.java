package com.hc360.koiambuyer.api.bean;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/16
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class AddressInfo {
    public ContentBean content;
    public String ret;
    public String msg;
    public static class ContentBean {
        public String addStr;
        public String useState;
        public int compId;
        public String provinceCode;
        public String cityCode;
        public String areaCode;
        public String addressDetail;
        public String states;
        public long createTime;
        public String createMan;
        public long updateTime;
        public String updateMan;
        public int deliverId;
        public String deliverUser;
        public String telphone;
    }
}
