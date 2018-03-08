package com.hc360.koiambuyer.api.bean;

import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/9/29
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class FindCompanyInfo {


    public int count;
    public List<CompanyInfo> list;

    public static class CompanyInfo {
        public String checkState;
        public String userList;
        public int compId;
        public String count;
        public String compLogo;
        public String inName;
        public String banner1;
        public String banner2;
        public String banner3;
        public String banner4;
        public String banner5;
        public String compType;
        public String compName;
        public String proNames;
        public String pName;
        public String cName;
        public String aName;
        public String provinceCode;
        public String cityCode;
        public String areaCode;
        public String addressDetail;
        public int userId;
        public String states;
        public long createTime;
        public String createMan;
        public long updateTime;
        public String updateMan;
        public String compNature;
        public String iscust;
        public String longitude;
        public String latitude;
        public String compShortName;
        public String mainIndustry;
        public String compIntro;
    }
}
