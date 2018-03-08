package com.hc360.koiambuyer.api.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/31
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class PurchaseDetailInfo implements Serializable{


    /**
     * content : {"recommendList":[{"createTime":1504254582000,"compId":1,"userHeadImg":"http://img03.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg","sysIndustryName":"家电配件","compName":"企业名称","endTime":1510046873000,"unit":"个","addStr":"北京 北京市 东城区","productNum":122,"userId":51,"userName":"姜家成1","productListTitle":"公司采购2017年年会奖品","productName":"名称3","productListId":1},{"createTime":1504254582000,"compId":1,"userHeadImg":"http://img02.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg","sysIndustryName":"家电配件","compName":"企业名称","endTime":1510046873000,"unit":"个","addStr":"北京 北京市 东城区","productNum":111,"userId":51,"userName":"姜家成1","productListTitle":"公司采购2017年年会奖品","productName":"名称2","productListId":1},{"createTime":1504254582000,"compId":1,"userHeadImg":"http://img03.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg","sysIndustryName":"家电配件","compName":"企业名称","endTime":1510046873000,"unit":"个","addStr":"北京 北京市 东城区","productNum":100,"userId":51,"userName":"姜家成1","productListTitle":"公司采购2017年年会奖品","productName":"名称1","productListId":1},{"compId":1,"createTime":1509353681000,"userHeadImg":"http://img07.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg","sysIndustryName":"家电配件","addStr":"北京 北京市 东城区","compName":"企业名称","userId":55,"userName":"www","endTime":1509580800000,"productListTitle":"标题1","productListId":8},{"createTime":1508328613000,"compId":1,"userHeadImg":"http://img01.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg","sysIndustryName":"家电配件","compName":"企业名称","endTime":1510046873000,"unit":"个","addStr":"北京 北京市 东城区","productNum":100,"userId":51,"userName":"姜家成1","productListTitle":"标题","productName":"名称1","productListId":7},{"createTime":1508328613000,"compId":1,"userHeadImg":"http://img05.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg","sysIndustryName":"家电配件","compName":"企业名称","endTime":1510046873000,"unit":"个","addStr":"北京 北京市 东城区","productNum":133,"userId":51,"userName":"姜家成1","productListTitle":"标题","productName":"名称4","productListId":7},{"createTime":1510048269000,"compId":1,"userHeadImg":"http://img06.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg","sysIndustryName":"家电配件","compName":"企业名称","endTime":1511913600000,"unit":"进口","addStr":"北京 北京市 东城区","productNum":12,"userId":55,"userName":"www","productListTitle":"好开心","productName":"行吗","productListId":20},{"createTime":1510048269000,"compId":1,"userHeadImg":"http://img03.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg","sysIndustryName":"家电配件","compName":"企业名称","endTime":1511913600000,"unit":"很OK哦咯","addStr":"北京 北京市 东城区","productNum":12,"userId":55,"userName":"www","productListTitle":"好开心","productName":"howl","productListId":20},{"compId":1,"createTime":1509522121000,"userHeadImg":"http://img06.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg","sysIndustryName":"家电配件","addStr":"北京 北京市 东城区","compName":"企业名称","userId":55,"userName":"www","endTime":1509408000000,"productListTitle":"ww","productListId":19},{"createTime":1508328613000,"compId":1,"userHeadImg":"http://img06.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg","sysIndustryName":"家电配件","compName":"企业名称","endTime":1510046873000,"unit":"个","addStr":"北京 北京市 东城区","productNum":122,"userId":51,"userName":"姜家成1","productListTitle":"标题","productName":"名称3","productListId":7},{"compId":1,"createTime":1509502312000,"userHeadImg":"http://img02.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg","sysIndustryName":"家电配件","addStr":"北京 北京市 东城区","compName":"企业名称","userId":55,"userName":"www","endTime":1509408000000,"productListTitle":"22","productListId":17},{"createTime":1508328613000,"compId":1,"userHeadImg":"http://img02.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg","sysIndustryName":"家电配件","compName":"企业名称","endTime":1510046873000,"unit":"个","addStr":"北京 北京市 东城区","productNum":111,"userId":51,"userName":"姜家成1","productListTitle":"标题","productName":"名称2","productListId":7}],"stProduct_list":{"compId":1,"productListId":1,"userId":51,"listState":"0","endTime":1510046873000,"createTime":1504254582000,"updateTime":1506760217000,"shareCount":0,"recordCount":1,"productListTitle":"公司采购2017年年会奖品","productListDetail":"内容","states":"0","createMan":"admin","updateMan":"admin","lookCount":2,"soldCause":null},"userHeadImg":"http://img01.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg","sysIndustryName":"家电配件","compName":"企业名称","addStr":"北京 北京市 东城区","userName":"姜家成1","followComp":1,"stProducts":[{"productName":"名称1","productListId":1,"productId":1,"unit":"个","productNum":100,"states":"0"},{"productName":"名称2","productListId":1,"productId":12,"unit":"个","productNum":111,"states":"0"},{"productName":"名称3","productListId":1,"productId":13,"unit":"个","productNum":122,"states":"0"}]}
     * ret : 200
     * msg : success
     */

    public ContentBean content;
    public String ret;
    public String msg;

    public static class ContentBean {
        /**
         * recommendList : [{"createTime":1504254582000,"compId":1,"userHeadImg":"http://img03.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg","sysIndustryName":"家电配件","compName":"企业名称","endTime":1510046873000,"unit":"个","addStr":"北京 北京市 东城区","productNum":122,"userId":51,"userName":"姜家成1","productListTitle":"公司采购2017年年会奖品","productName":"名称3","productListId":1},{"createTime":1504254582000,"compId":1,"userHeadImg":"http://img02.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg","sysIndustryName":"家电配件","compName":"企业名称","endTime":1510046873000,"unit":"个","addStr":"北京 北京市 东城区","productNum":111,"userId":51,"userName":"姜家成1","productListTitle":"公司采购2017年年会奖品","productName":"名称2","productListId":1},{"createTime":1504254582000,"compId":1,"userHeadImg":"http://img03.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg","sysIndustryName":"家电配件","compName":"企业名称","endTime":1510046873000,"unit":"个","addStr":"北京 北京市 东城区","productNum":100,"userId":51,"userName":"姜家成1","productListTitle":"公司采购2017年年会奖品","productName":"名称1","productListId":1},{"compId":1,"createTime":1509353681000,"userHeadImg":"http://img07.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg","sysIndustryName":"家电配件","addStr":"北京 北京市 东城区","compName":"企业名称","userId":55,"userName":"www","endTime":1509580800000,"productListTitle":"标题1","productListId":8},{"createTime":1508328613000,"compId":1,"userHeadImg":"http://img01.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg","sysIndustryName":"家电配件","compName":"企业名称","endTime":1510046873000,"unit":"个","addStr":"北京 北京市 东城区","productNum":100,"userId":51,"userName":"姜家成1","productListTitle":"标题","productName":"名称1","productListId":7},{"createTime":1508328613000,"compId":1,"userHeadImg":"http://img05.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg","sysIndustryName":"家电配件","compName":"企业名称","endTime":1510046873000,"unit":"个","addStr":"北京 北京市 东城区","productNum":133,"userId":51,"userName":"姜家成1","productListTitle":"标题","productName":"名称4","productListId":7},{"createTime":1510048269000,"compId":1,"userHeadImg":"http://img06.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg","sysIndustryName":"家电配件","compName":"企业名称","endTime":1511913600000,"unit":"进口","addStr":"北京 北京市 东城区","productNum":12,"userId":55,"userName":"www","productListTitle":"好开心","productName":"行吗","productListId":20},{"createTime":1510048269000,"compId":1,"userHeadImg":"http://img03.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg","sysIndustryName":"家电配件","compName":"企业名称","endTime":1511913600000,"unit":"很OK哦咯","addStr":"北京 北京市 东城区","productNum":12,"userId":55,"userName":"www","productListTitle":"好开心","productName":"howl","productListId":20},{"compId":1,"createTime":1509522121000,"userHeadImg":"http://img06.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg","sysIndustryName":"家电配件","addStr":"北京 北京市 东城区","compName":"企业名称","userId":55,"userName":"www","endTime":1509408000000,"productListTitle":"ww","productListId":19},{"createTime":1508328613000,"compId":1,"userHeadImg":"http://img06.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg","sysIndustryName":"家电配件","compName":"企业名称","endTime":1510046873000,"unit":"个","addStr":"北京 北京市 东城区","productNum":122,"userId":51,"userName":"姜家成1","productListTitle":"标题","productName":"名称3","productListId":7},{"compId":1,"createTime":1509502312000,"userHeadImg":"http://img02.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg","sysIndustryName":"家电配件","addStr":"北京 北京市 东城区","compName":"企业名称","userId":55,"userName":"www","endTime":1509408000000,"productListTitle":"22","productListId":17},{"createTime":1508328613000,"compId":1,"userHeadImg":"http://img02.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg","sysIndustryName":"家电配件","compName":"企业名称","endTime":1510046873000,"unit":"个","addStr":"北京 北京市 东城区","productNum":111,"userId":51,"userName":"姜家成1","productListTitle":"标题","productName":"名称2","productListId":7}]
         * stProduct_list : {"compId":1,"productListId":1,"userId":51,"listState":"0","endTime":1510046873000,"createTime":1504254582000,"updateTime":1506760217000,"shareCount":0,"recordCount":1,"productListTitle":"公司采购2017年年会奖品","productListDetail":"内容","states":"0","createMan":"admin","updateMan":"admin","lookCount":2,"soldCause":null}
         * userHeadImg : http://img01.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg
         * sysIndustryName : 家电配件
         * compName : 企业名称
         * addStr : 北京 北京市 东城区
         * userName : 姜家成1
         * followComp : 1
         * stProducts : [{"productName":"名称1","productListId":1,"productId":1,"unit":"个","productNum":100,"states":"0"},{"productName":"名称2","productListId":1,"productId":12,"unit":"个","productNum":111,"states":"0"},{"productName":"名称3","productListId":1,"productId":13,"unit":"个","productNum":122,"states":"0"}]
         */

        public StProductListBean stProduct_list;
        public String userHeadImg;
        public String sysIndustryName;
        public String compName;
        public String addStr;
        public String userName;
        public String checkState;
        public String userPosition;
        public int followComp;
        public List<RecommendListBean> recommendList;
        public List<StProductsBean> stProducts;

        public static class StProductListBean {
            /**
             * compId : 1
             * productListId : 1
             * userId : 51
             * listState : 0
             * endTime : 1510046873000
             * createTime : 1504254582000
             * updateTime : 1506760217000
             * shareCount : 0
             * recordCount : 1
             * productListTitle : 公司采购2017年年会奖品
             * productListDetail : 内容
             * states : 0
             * createMan : admin
             * updateMan : admin
             * lookCount : 2
             * soldCause : null
             */

            public int compId;
            public int productListId;
            public int userId;
            public String listState;
            public long endTime;
            public long createTime;
            public long updateTime;
            public int shareCount;
            public int recordCount;
            public String productListTitle;
            public String productListDetail;
            public String states;
            public String createMan;
            public String updateMan;
            public int lookCount;
            public Object soldCause;
        }

        public static class RecommendListBean {
            /**
             * createTime : 1504254582000
             * compId : 1
             * userHeadImg : http://img03.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg
             * sysIndustryName : 家电配件
             * compName : 企业名称
             * endTime : 1510046873000
             * unit : 个
             * addStr : 北京 北京市 东城区
             * productNum : 122
             * userId : 51
             * userName : 姜家成1
             * productListTitle : 公司采购2017年年会奖品
             * productName : 名称3
             * productListId : 1
             */

            public long createTime;
            public int compId;
            public String userHeadImg;
            public String sysIndustryName;
            public String compName;
            public long endTime;
            public String unit;
            public String addStr;
            public int productNum;
            public int userId;
            public String userName;
            public String productListTitle;
            public String productName;
            public int productListId;
            public int productId;
        }

        public static class StProductsBean implements Serializable{
            /**
             * productName : 名称1
             * productListId : 1
             * productId : 1
             * unit : 个
             * productNum : 100
             * states : 0
             */

            public String productName;
            public int productListId;
            public int productId;
            public String unit;
            public int productNum;
            public String states;
        }
    }
}
