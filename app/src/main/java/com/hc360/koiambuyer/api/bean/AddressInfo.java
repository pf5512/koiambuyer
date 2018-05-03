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

    /**
     * content : {"pName":"서울특별시","cName":"서울특별시","states":"0","userId":365,"compId":null,"provinceCode":"001","cityCode":"001001","areaCode":null,"addressDetail":"地址","receiveId":1,"receiveUser":"地址","useState":"0","createTime":1521178791000,"createMan":"00","updateTime":1521452594000,"updateMan":"iambuyer","telphone":"18611874477"}
     * ret : 200
     * msg : success
     */

    public ContentBean content;
    public String ret;
    public String msg;

    public static class ContentBean {
        /**
         * pName : 서울특별시
         * cName : 서울특별시
         * states : 0
         * userId : 365
         * compId : null
         * provinceCode : 001
         * cityCode : 001001
         * areaCode : null
         * addressDetail : 地址
         * receiveId : 1
         * receiveUser : 地址
         * useState : 0
         * createTime : 1521178791000
         * createMan : 00
         * updateTime : 1521452594000
         * updateMan : iambuyer
         * telphone : 18611874477
         */

        public String pName;
        public String cName;
        public String states;
        public int userId;
        public Object compId;
        public String provinceCode;
        public String cityCode;
        public Object areaCode;
        public String addressDetail;
        public int receiveId;
        public String receiveUser;
        public String useState;
        public long createTime;
        public String createMan;
        public long updateTime;
        public String updateMan;
        public String telphone;
    }
}
