package com.hc360.koiambuyer.api.bean;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/26
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class SettingInfo {

    /**
     * content : {"iswx":"1","isemail":"0","isqq":"1","version":"1.0"}
     * ret : 200
     * msg : success
     */

    public ContentBean content;
    public String ret;
    public String msg;

    public static class ContentBean {
        /**
         * iswx : 1
         * isemail : 0
         * isqq : 1
         * version : 1.0
         */

        public String iswx;
        public String isemail;
        public String isqq;
        public String version;
    }
}
