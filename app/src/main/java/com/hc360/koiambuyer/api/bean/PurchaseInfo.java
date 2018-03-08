package com.hc360.koiambuyer.api.bean;

import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/30
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class PurchaseInfo {

    public int count;
    public List<ListBean> list;

    public static class ListBean {
        public int compId;
        public long createTime;
        public String userHeadImg;
        public String sysIndustryName;
        public String addStr;
        public String compName;
        public int userId;
        public int productNum;
        public String userName;
        public long endTime;
        public String productListTitle;
        public String productName;
        public String unit;
        public int productId;
        public int productListId;
    }
}
