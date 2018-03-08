package com.hc360.koiambuyer.api.bean;

import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/13
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class ChatLookedInfo {

    /**
     * count : 42
     * list : [{"createTime":1508752748000,"userHeadImg":"http://img01.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg","sysIndustryName":"家电配件","addStr":"北京 北京市 东城区","compName":"企业名称","userId":51,"userName":"姜家成1","productName":"产品名称21","productId":3},{"createTime":1509931332000,"userHeadImg":"http://img05.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg","sysIndustryName":"家电配件","addStr":"北京 北京市 东城区","compName":"企业名称","userId":55,"userName":"www","productName":"产品名称21","productId":3},{"createTime":1510294157000,"userHeadImg":"http://img02.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg","sysIndustryName":"家电配件","addStr":"北京 北京市 东城区","compName":"企业名称","userId":51,"userName":"姜家成1","productName":"产品名称","productId":5},{"createTime":1510301394000,"userHeadImg":"http://img02.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg","sysIndustryName":"家电配件","addStr":"北京 北京市 东城区","compName":"企业名称","userId":55,"userName":"www","productName":"产品名称","productId":5},{"createTime":1510286081000,"userHeadImg":"http://img04.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg","sysIndustryName":"家电配件","addStr":"北京 北京市 东城区","compName":"企业名称","userId":55,"userName":"www","productName":"产品名称","productId":6}]
     */

    public int count;
    public String isReleased;
    public List<ListBean> list;

    public static class ListBean {
        /**
         * createTime : 1508752748000
         * userHeadImg : http://img01.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg
         * sysIndustryName : 家电配件
         * addStr : 北京 北京市 东城区
         * compName : 企业名称
         * userId : 51
         * userName : 姜家成1
         * productName : 产品名称21
         * productId : 3
         */

        public long createTime;
        public String userHeadImg;
        public String sysIndustryName;
        public String addStr;
        public String compName;
        public String userPosition;
        public int userId;
        public String userName;
        public String productName;
        public int productId;
    }
}
