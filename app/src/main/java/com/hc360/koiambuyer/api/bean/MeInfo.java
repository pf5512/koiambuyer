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

    /**
     * content : {"pendingDeliveryCount":9,"deliverGoodsCount":0,"phone":"18611874490","cancelCount":2,"matchmakingCount":1,"recordProCount":3,"followProCount":0,"followUserCount":1,"followProdCount":0,"productSuccessCount":2,"headImg":"http://img06.iambuyer.com/imgup/upload/images/2018/03/27/kDiaG2pBI8obOwIBsTS6hrcRbcb8qSqhh3AJJTmjNTOgahcjjc2FfZDCwmhGIc7BrRDfhy.jpg","userName":"我希","successCount":1,"shopCount":2,"publishedCount":22}
     * ret : 200
     * msg : success
     */

    public ContentBean content;
    public String ret;
    public String msg;

    public static class ContentBean {
        /**
         * pendingDeliveryCount : 9
         * deliverGoodsCount : 0
         * phone : 18611874490
         * cancelCount : 2
         * matchmakingCount : 1
         * recordProCount : 3
         * followProCount : 0
         * followUserCount : 1
         * followProdCount : 0
         * productSuccessCount : 2
         * headImg : http://img06.iambuyer.com/imgup/upload/images/2018/03/27/kDiaG2pBI8obOwIBsTS6hrcRbcb8qSqhh3AJJTmjNTOgahcjjc2FfZDCwmhGIc7BrRDfhy.jpg
         * userName : 我希
         * successCount : 1
         * shopCount : 2
         * publishedCount : 22
         */

        public int pendingDeliveryCount;
        public int deliverGoodsCount;
        public String phone;
        public int cancelCount;
        public int matchmakingCount;
        public int recordProCount;
        public int followProCount;
        public int followUserCount;
        public int followProdCount;
        public int productSuccessCount;
        public String headImg;
        public String userName;
        public int successCount;
        public int shopCount;
        public int publishedCount;
    }
}
