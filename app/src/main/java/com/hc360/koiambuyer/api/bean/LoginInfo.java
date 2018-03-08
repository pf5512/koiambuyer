package com.hc360.koiambuyer.api.bean;

/**
 * Project: Ruhefu
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/3/16
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class LoginInfo {

    public ContentBean content;
    public String ret;
    public String msg;

    public static class ContentBean {
        public String loginRetType;
        public RetSsoUserBean retSsoUser;

        public static class RetSsoUserBean {
            public int id;
            public String username;
            public String email;
            public String mobile;
        }
    }
}
