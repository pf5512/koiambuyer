package com.hc360.koiambuyer.api.bean;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/20
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class ChatStatesInfo {

    /**
     * content : {"id":30,"type":"0","checkState":"0","states":"0","createMan":"iambuyer","updateMan":"iambuyer","createTime":1510906265000,"updateTime":1510906269000,"formuserid":55,"touserid":51}
     * ret : 200
     * msg : success
     */

    public ContentBean content;
    public String ret;
    public String msg;

    public static class ContentBean {
        /**
         * id : 30
         * type : 0
         * checkState : 0
         * states : 0
         * createMan : iambuyer
         * updateMan : iambuyer
         * createTime : 1510906265000
         * updateTime : 1510906269000
         * formuserid : 55
         * touserid : 51
         */

        public int id;
        public String type;
        public String checkState;
        public String states;
        public String createMan;
        public String updateMan;
        public long createTime;
        public long updateTime;
        public int formuserid;
        public int touserid;
    }
}
