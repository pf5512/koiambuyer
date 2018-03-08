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

public class ChatAttentionInfo{

    /**
     * count : 1
     * list : [{"compId":1,"userHeadImg":"http://img07.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg","sysIndustryName":"家电配件","addStr":"北京 北京市 东城区","compName":"企业名称","userId":51,"userName":"姜家成1","followTime":1507701835000,"productName":"产品名称21","productId":3}]
     */

    public int count;
    public List<ListBean> list;

    public static class ListBean {
        /**
         * compId : 1
         * userHeadImg : http://img07.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg
         * sysIndustryName : 家电配件
         * addStr : 北京 北京市 东城区
         * compName : 企业名称
         * userId : 51
         * userName : 姜家成1
         * followTime : 1507701835000
         * productName : 产品名称21
         * productId : 3
         */

        public int compId;
        public String userHeadImg;
        public String sysIndustryName;
        public String addStr;
        public String compName;
        public int userId;
        public String userName;
        public String userPosition;
        public long followTime;
        public String productName;
        public int productId;
    }
}
