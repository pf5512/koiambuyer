package com.hc360.koiambuyer.api.bean;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/30
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class MeInfo {

    public ContentBean content;
    public String ret;
    public String msg;

    public static class ContentBean {
        public String userName;
        public int proCount;
        public int chatCount;
        public int collCount;
        public int compCount;
        public String compName;
        public String checkState;
        public String userHeadImg;
    }
}
