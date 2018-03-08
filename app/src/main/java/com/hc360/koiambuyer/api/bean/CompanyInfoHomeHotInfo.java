package com.hc360.koiambuyer.api.bean;

import java.math.BigDecimal;
import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/27
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class CompanyInfoHomeHotInfo {

    public int count;
    public List<ListBean> list;

    public static class ListBean {
        /**
         * typeName : null
         * userName : 姜家成1
         * compName : 姜家成股份有限公司
         * addStr : 北京 北京市 东城区
         * priceList : null
         * intro : null
         * imgs : null
         * userPosition : 销售经理
         * userHeadImg : http://img02.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg
         * sysCategoryStr : CD盒
         * userId : 51
         * productId : 5
         * states : 0
         * createMan : iambuyer
         * updateMan : iambuyer
         * lookCount : 2
         * brandName : 品牌名称
         * isOnline : 1
         * isMade : 1
         * loopImg001 : null
         * loopImg002 : null
         * loopImg003 : null
         * loopImg004 : null
         * loopImg005 : null
         * thirdCateId : 001001001
         * secondCateId : 001001
         * deliverGoogsId : 1
         * providerId : 1
         * createTime : 1508288234000
         * updateTime : 1508288234000
         * shareCount : 0
         * recordCount : 0
         * productStates : null
         * productName : 产品名称
         * firstCateId : 001
         * fourthCateId :
         * productPrice : 1
         * isHaveGoods : 0
         */

        public Object typeName;
        public String userName;
        public String compName;
        public String addStr;
        public Object priceList;
        public Object intro;
        public Object imgs;
        public String userPosition;
        public String userHeadImg;
        public String sysCategoryStr;
        public int userId;
        public int productId;
        public String states;
        public String createMan;
        public String updateMan;
        public BigDecimal minPrice;
        public int lookCount;
        public String brandName;
        public String isOnline;
        public String isMade;
        public String loopImg001;
        public String loopImg002;
        public String loopImg003;
        public String loopImg004;
        public String loopImg005;
        public String thirdCateId;
        public String secondCateId;
        public int deliverGoogsId;
        public int providerId;
        public long createTime;
        public long updateTime;
        public int shareCount;
        public int recordCount;
        public Object productStates;
        public String productName;
        public String firstCateId;
        public String fourthCateId;
        public String productPrice;
        public String isHaveGoods;
    }
}
