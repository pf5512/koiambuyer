package com.hc360.koiambuyer.api.bean;

import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/1
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class ChoiceSellerInfo{


    public int count;
    public List<ListBean> list;

    public static class ListBean {
        public String userName;
        public String count;
        public String pName;
        public String cName;
        public String aName;
        public String inName;
        public Object refuseReason;
        public String checkState;
        public String isFollow;
        public Object userPhone;
        public String proNames;
        public Object userList;
        public int userId;
        public String banner1;
        public Object banner2;
        public Object banner3;
        public Object banner4;
        public Object banner5;
        public String compLogo;
        public int compId;
        public String compType;
        public String compName;
        public String cityCode;
        public String areaCode;
        public String iscust;
        public String mainIndustry;
        public String provinceCode;
        public String addressDetail;
        public String compNature;
        public String compShortName;
        public long createTime;
        public long updateTime;
        public String updateMan;
        public String states;
        public String createMan;
        public Object longitude;
        public String compIntro;
        public Object latitude;
        public List<ImgListBean> imgList;

        public static class ImgListBean {
            public String img;
        }
    }
}
