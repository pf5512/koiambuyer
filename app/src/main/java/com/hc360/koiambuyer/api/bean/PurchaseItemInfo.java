package com.hc360.koiambuyer.api.bean;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/15
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class PurchaseItemInfo {

    /**
     * content : {"unit":"个","compId":1,"userHeadImg":"http://img08.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg","sysIndustryName":"家电配件","addStr":"北京 北京市 东城区","compName":"企业名称","productNum":111,"userId":51,"userName":"姜家成1","productName":"名称2","productListId":1,"userPosition":"销售经理"}
     * ret : 200
     * msg : success
     */

    public ContentBean content;
    public String ret;
    public String msg;

    public static class ContentBean {
        /**
         * unit : 个
         * compId : 1
         * userHeadImg : http://img08.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg
         * sysIndustryName : 家电配件
         * addStr : 北京 北京市 东城区
         * compName : 企业名称
         * productNum : 111
         * userId : 51
         * userName : 姜家成1
         * productName : 名称2
         * productListId : 1
         * userPosition : 销售经理
         */

        public String unit;
        public int compId;
        public long createTime;
        public long endTime;
        public String userHeadImg;
        public String sysIndustryName;
        public String addStr;
        public String compName;
        public int productNum;
        public int userId;
        public String userName;
        public String productName;
        public int productListId;
        public int productId;
        public String userPosition;
    }
}
