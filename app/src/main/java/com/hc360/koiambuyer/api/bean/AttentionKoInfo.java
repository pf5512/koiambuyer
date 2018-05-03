package com.hc360.koiambuyer.api.bean;

import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/25
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class AttentionKoInfo {

    /**
     * count : 1
     * list : [{"user":{"userId":"35","pName":null,"cName":null,"headImg":null,"phone":"15901368829","ssoUserId":203,"loginTime":null,"createTimeStart":null,"createTimeEnd":null,"loginTimeEnd":null,"loginTimeStart":null,"referUserName":"王亚蒙","userQrimg":null,"email":null,"role":"1","userName":"王亚蒙","createTime":null},"imgList":["http://img06.iambuyer.com/imgup/upload/images/2017/12/22/IXNbKLg6PaJ3Zh6Thcjjc2Bfy334aHhcjjc2BNNqNylhVZhcjjc2Bmiemb7GK4FaNcW0Gr0vRKFOBNXhcjjc2FGhcjjc2F.jpg"],"id":1,"userId":365,"followState":"0","followUserId":203,"productId":null,"states":"0","createTime":1521109245000,"createMan":"iambuyer","updateTime":1521166519000,"updateMan":"iambuyer","followTime":1521166519000}]
     */

    public int count;
    public List<ListBean> list;

    public static class ListBean {
        /**
         * user : {"userId":"35","pName":null,"cName":null,"headImg":null,"phone":"15901368829","ssoUserId":203,"loginTime":null,"createTimeStart":null,"createTimeEnd":null,"loginTimeEnd":null,"loginTimeStart":null,"referUserName":"王亚蒙","userQrimg":null,"email":null,"role":"1","userName":"王亚蒙","createTime":null}
         * imgList : ["http://img06.iambuyer.com/imgup/upload/images/2017/12/22/IXNbKLg6PaJ3Zh6Thcjjc2Bfy334aHhcjjc2BNNqNylhVZhcjjc2Bmiemb7GK4FaNcW0Gr0vRKFOBNXhcjjc2FGhcjjc2F.jpg"]
         * id : 1
         * userId : 365
         * followState : 0
         * followUserId : 203
         * productId : null
         * states : 0
         * createTime : 1521109245000
         * createMan : iambuyer
         * updateTime : 1521166519000
         * updateMan : iambuyer
         * followTime : 1521166519000
         */

        public UserBean user;
        public int id;
        public int userId;
        public String followState;
        public int followUserId;
        public Object productId;
        public String states;
        public long createTime;
        public String createMan;
        public long updateTime;
        public String updateMan;
        public long followTime;
        public List<String> imgList;

        public static class UserBean {
            /**
             * userId : 35
             * pName : null
             * cName : null
             * headImg : null
             * phone : 15901368829
             * ssoUserId : 203
             * loginTime : null
             * createTimeStart : null
             * createTimeEnd : null
             * loginTimeEnd : null
             * loginTimeStart : null
             * referUserName : 王亚蒙
             * userQrimg : null
             * email : null
             * role : 1
             * userName : 王亚蒙
             * createTime : null
             */

            public String userId;
            public Object pName;
            public Object cName;
            public String headImg;
            public String phone;
            public int ssoUserId;
            public Object loginTime;
            public Object createTimeStart;
            public Object createTimeEnd;
            public Object loginTimeEnd;
            public Object loginTimeStart;
            public String referUserName;
            public Object userQrimg;
            public Object email;
            public String role;
            public String userName;
            public Object createTime;
        }
    }
}
