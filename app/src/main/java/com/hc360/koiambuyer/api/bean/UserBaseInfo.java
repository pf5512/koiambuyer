package com.hc360.koiambuyer.api.bean;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/24
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class UserBaseInfo {


    /**
     * content : {"user":{"createTimeStart":null,"createTimeEnd":null,"email":"158111@qq.com","loginTimeEnd":null,"loginTimeStart":null,"referUserName":"zhaojie","role":"-1","loginTime":null,"userContent":null,"userId":"86","referUserid":396,"pName":null,"cName":null,"headImg":"http://img07.iambuyer.com/imgup/upload/images/2018/03/19/shcjjc2FEMYXVPIi3MLT99ueFNSmOLtUoXg11Ma52TRm58PogsrliNuGtYC1hcjjc2BWH9kZjhcjjc2B19.png","phone":"18611874490","ssoUserId":365,"userName":"名字","createTime":null,"userQrimg":null}}
     * ret : 200
     * msg : success
     */

    public ContentBean content;
    public String ret;
    public String msg;

    public static class ContentBean {
        /**
         * user : {"createTimeStart":null,"createTimeEnd":null,"email":"158111@qq.com","loginTimeEnd":null,"loginTimeStart":null,"referUserName":"zhaojie","role":"-1","loginTime":null,"userContent":null,"userId":"86","referUserid":396,"pName":null,"cName":null,"headImg":"http://img07.iambuyer.com/imgup/upload/images/2018/03/19/shcjjc2FEMYXVPIi3MLT99ueFNSmOLtUoXg11Ma52TRm58PogsrliNuGtYC1hcjjc2BWH9kZjhcjjc2B19.png","phone":"18611874490","ssoUserId":365,"userName":"名字","createTime":null,"userQrimg":null}
         */

        public UserBean user;

        public static class UserBean {
            /**
             * createTimeStart : null
             * createTimeEnd : null
             * email : 158111@qq.com
             * loginTimeEnd : null
             * loginTimeStart : null
             * referUserName : zhaojie
             * role : -1
             * loginTime : null
             * userContent : null
             * userId : 86
             * referUserid : 396
             * pName : null
             * cName : null
             * headImg : http://img07.iambuyer.com/imgup/upload/images/2018/03/19/shcjjc2FEMYXVPIi3MLT99ueFNSmOLtUoXg11Ma52TRm58PogsrliNuGtYC1hcjjc2BWH9kZjhcjjc2B19.png
             * phone : 18611874490
             * ssoUserId : 365
             * userName : 名字
             * createTime : null
             * userQrimg : null
             */

            public Object createTimeStart;
            public Object createTimeEnd;
            public String email;
            public Object loginTimeEnd;
            public Object loginTimeStart;
            public String referUserName;
            public String role;
            public Object loginTime;
            public Object userContent;
            public String userId;
            public int referUserid;
            public Object pName;
            public Object cName;
            public String headImg;
            public String phone;
            public int ssoUserId;
            public String userName;
            public Object createTime;
            public String userQrimg;
        }
    }
}
