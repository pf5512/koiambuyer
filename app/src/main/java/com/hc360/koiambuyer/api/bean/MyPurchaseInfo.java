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

public class MyPurchaseInfo {

    /**
     * count : 1
     * list : [{"userName":"姜家成1","compName":"姜家成股份有限公司","stProduct":[{"states":"0","productName":"名称1","productNum":100,"productId":23,"productListId":7,"unit":"个"},{"states":"0","productName":"名称2","productNum":111,"productId":24,"productListId":7,"unit":"个"},{"states":"0","productName":"名称3","productNum":122,"productId":25,"productListId":7,"unit":"个"},{"states":"0","productName":"名称4","productNum":133,"productId":26,"productListId":7,"unit":"个"}],"userHeadImg":"http://img03.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg","states":"0","createMan":"iambuyer","updateMan":"iambuyer","lookCount":0,"soldCause":null,"createTime":1508328613000,"updateTime":1508328613000,"shareCount":0,"recordCount":0,"productListTitle":"标题","productListDetail":"内容","compId":1,"userId":51,"listState":"0","endTime":1510046873000,"productListId":7}]
     */

    public int count;
    public List<ListBean> list;

    public static class ListBean {
        /**
         * userName : 姜家成1
         * compName : 姜家成股份有限公司
         * stProduct : [{"states":"0","productName":"名称1","productNum":100,"productId":23,"productListId":7,"unit":"个"},{"states":"0","productName":"名称2","productNum":111,"productId":24,"productListId":7,"unit":"个"},{"states":"0","productName":"名称3","productNum":122,"productId":25,"productListId":7,"unit":"个"},{"states":"0","productName":"名称4","productNum":133,"productId":26,"productListId":7,"unit":"个"}]
         * userHeadImg : http://img03.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg
         * states : 0
         * createMan : iambuyer
         * updateMan : iambuyer
         * lookCount : 0
         * soldCause : null
         * createTime : 1508328613000
         * updateTime : 1508328613000
         * shareCount : 0
         * recordCount : 0
         * productListTitle : 标题
         * productListDetail : 内容
         * compId : 1
         * userId : 51
         * listState : 0
         * endTime : 1510046873000
         * productListId : 7
         */

        public String userName;
        public String compName;
        public String userHeadImg;
        public String states;
        public String createMan;
        public String updateMan;
        public int lookCount;
        public Object soldCause;
        public long createTime;
        public long updateTime;
        public int shareCount;
        public int recordCount;
        public String productListTitle;
        public String productListDetail;
        public int compId;
        public int userId;
        public String listState;
        public long endTime;
        public int productListId;
        public int productId;
        public List<StProductBean> stProduct;

        public static class StProductBean {
            /**
             * states : 0
             * productName : 名称1
             * productNum : 100
             * productId : 23
             * productListId : 7
             * unit : 个
             */

            public String states;
            public String productName;
            public int productNum;
            public int productId;
            public int productListId;
            public String unit;
        }
    }
}
