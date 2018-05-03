package com.hc360.koiambuyer.api.bean;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/9/29
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class InitInfo{

    /**
     * content : {"user":{"email":"158111@qq.com","ssoUserId":365,"referUserid":396,"createTime":null,"userQrimg":null,"role":"-1","userName":"我希","createTimeStart":null,"loginTimeEnd":null,"loginTimeStart":null,"referUserName":"zhaojie","createTimeEnd":null,"userId":"86","pName":null,"cName":null,"headImg":"http://img04.iambuyer.com/imgup/upload/images/2018/03/27/kDiaG2pBI8obOwIBsTS6hrcRbcb8qSqhh3AJJTmjNTOgahcjjc2FfZDCwmhGIc7BrRDfhy.jpg","phone":"18611874490","userContent":null,"loginTime":null}}
     * ret : 200
     * msg : success
     */

    public ContentBean content;
    public String ret;
    public String msg;

    public static class ContentBean {
        /**
         * user : {"email":"158111@qq.com","ssoUserId":365,"referUserid":396,"createTime":null,"userQrimg":null,"role":"-1","userName":"我希","createTimeStart":null,"loginTimeEnd":null,"loginTimeStart":null,"referUserName":"zhaojie","createTimeEnd":null,"userId":"86","pName":null,"cName":null,"headImg":"http://img04.iambuyer.com/imgup/upload/images/2018/03/27/kDiaG2pBI8obOwIBsTS6hrcRbcb8qSqhh3AJJTmjNTOgahcjjc2FfZDCwmhGIc7BrRDfhy.jpg","phone":"18611874490","userContent":null,"loginTime":null}
         */

        public UserBean user;
        public String isFollowUser;

        public static class UserBean {
            /**
             * email : 158111@qq.com
             * ssoUserId : 365
             * referUserid : 396
             * createTime : null
             * userQrimg : null
             * role : -1
             * userName : 我希
             * createTimeStart : null
             * loginTimeEnd : null
             * loginTimeStart : null
             * referUserName : zhaojie
             * createTimeEnd : null
             * userId : 86
             * pName : null
             * cName : null
             * headImg : http://img04.iambuyer.com/imgup/upload/images/2018/03/27/kDiaG2pBI8obOwIBsTS6hrcRbcb8qSqhh3AJJTmjNTOgahcjjc2FfZDCwmhGIc7BrRDfhy.jpg
             * phone : 18611874490
             * userContent : null
             * loginTime : null
             */

            public String email;
            public int ssoUserId;
            public int referUserid;
            public Object createTime;
            public Object userQrimg;
            public String role;
            public String userName;
            public Object createTimeStart;
            public Object loginTimeEnd;
            public Object loginTimeStart;
            public String referUserName;
            public Object createTimeEnd;
            public String userId;
            public String referPhone;
            public Object pName;
            public Object cName;
            public String headImg;
            public String phone;
            public String userContent;
            public Object loginTime;
        }
    }
}
