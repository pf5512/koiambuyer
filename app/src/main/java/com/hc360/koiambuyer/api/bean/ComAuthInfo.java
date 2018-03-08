package com.hc360.koiambuyer.api.bean;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/12
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class ComAuthInfo {
    public ContentBean content;
    public String ret;
    public String msg;

    public static class ContentBean {
        public String compType;
        public String compName;
        public String provinceCode;
        public String cityCode;
        public String areaCode;
        public String addressDetail;
        public String addNames;
        public int id;
        public int compId;
        public String businLicenseImg;
        public String accountLicenseImg;
        public String legalIdcardBackImg;
        public String legalIdcardFrontImg;
        public String legalIdcardHandleImg;
        public String agentIdcardBackImg;
        public String agentIdcardFrontImg;
        public String agentIdcardHandleImg;
        public String applyUserName;
        public long regDate;
        public String legalPersonName;
        public String businLicenseCode;
        public String applyUserType;
        public String applyUserTelphone;
        public String checkState;
        public long checkTime;
        public String checkMan;
        public String refuseReason;
        public String states;
        public long createTime;
        public String createMan;
        public long updateTime;
        public String updateMan;
    }
}
