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


    /**
     * list : [{"pName":"서울특별시","cName":"서울특별시","states":"0","userId":365,"compId":null,"provinceCode":"001","cityCode":"001001","areaCode":null,"addressDetail":"收货1详细地址","useState":"0","createTime":1521431489000,"createMan":"iambuyer","updateTime":1521431676000,"updateMan":"iambuyer","telphone":"18611874477","receiveId":2,"receiveUser":"姜家1成"},{"pName":"서울특별시","cName":"서울특별시","states":"0","userId":365,"compId":null,"provinceCode":"001","cityCode":"001001","areaCode":null,"addressDetail":"地址","useState":"0","createTime":1521178791000,"createMan":"00","updateTime":1521178794000,"updateMan":"00","telphone":"18611874477","receiveId":1,"receiveUser":"地址"}]
     */

    public List<ListBean> list;

    public static class ListBean {
        /**
         * pName : 서울특별시
         * cName : 서울특별시
         * states : 0
         * userId : 365
         * compId : null
         * provinceCode : 001
         * cityCode : 001001
         * areaCode : null
         * addressDetail : 收货1详细地址
         * useState : 0
         * createTime : 1521431489000
         * createMan : iambuyer
         * updateTime : 1521431676000
         * updateMan : iambuyer
         * telphone : 18611874477
         * receiveId : 2
         * receiveUser : 姜家1成
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
        public String useState;
        public long createTime;
        public String createMan;
        public long updateTime;
        public String updateMan;
        public String telphone;
        public int receiveId;
        public String receiveUser;
    }
}
