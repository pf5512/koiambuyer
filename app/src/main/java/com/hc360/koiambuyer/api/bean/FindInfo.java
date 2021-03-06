package com.hc360.koiambuyer.api.bean;

import java.math.BigDecimal;
import java.util.List;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/4/18
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class FindInfo {

    /**
     * count : 2
     * list : [{"typeName":null,"userName":null,"minPrice":null,"compName":"321","unit":null,"intro":null,"addStr":null,"priceList":null,"imgs":null,"korCoreUserVo":{"userName":"柳","email":null,"role":"-1","loginTime":null,"userId":"171","pName":null,"cName":null,"headImg":null,"phone":"18032263017","ssoUserId":202,"userQrimg":null,"userContent":null,"referPhone":"15901368829","createTimeStart":null,"createTimeEnd":null,"loginTimeEnd":null,"loginTimeStart":null,"referUserName":"王亚蒙","referUserid":203,"createTime":null},"sysCategoryStr":null,"userPosition":null,"userHeadImg":null,"createMan":"iambuyerKorea","updateMan":"iambuyerKorea","sendTime":"三个月内","brandName":null,"isMade":"1","userId":202,"productId":244,"sysType":"IambuyerKorea","isOnline":"1","lookCount":7,"phone":"3213123","states":"0","productUnit":"3123","productPrice":"0","shareCount":0,"prodownUserid":null,"prodownTime":null,"publishTime":null,"prodownContent":null,"productTotal":3123,"productMaxcount":323,"buyerContent":"31232131","productNum":"2312312","productSize":"213","productPkgsize":"123123","productWeight":"123123","productColor":"123123","productMoney":21312,"productStates":null,"firstCateId":null,"fourthCateId":null,"isHaveGoods":null,"updateTime":1523497262000,"createTime":1523497262000,"loopImg002":null,"loopImg003":"http://img01.iambuyer.com/imgup/upload/images/2018/04/12/0d7HE4yUufr9uev8bhcjjc2B4jrlgoKLhcjjc2BqSFw5hcjjc2BwHdlpkb8EzQYHhcjjc2BJqdZYFYTjxilsh4it.png","loopImg004":null,"loopImg005":null,"thirdCateId":null,"secondCateId":null,"deliverGoogsId":null,"providerId":null,"productName":"312312","loopImg001":"http://img04.iambuyer.com/imgup/upload/images/2018/04/12/VYTYyGRENejwnaNcSfhcjjc2Fhcjjc2BQg3gqYLQ2pAwTVy3Z7yjhcjjc2FriXGHMWS27BWbYy1Y6PR7yq.png","recordCount":3},{"typeName":null,"userName":null,"minPrice":null,"compName":"1","unit":null,"intro":null,"addStr":null,"priceList":null,"imgs":null,"korCoreUserVo":{"userName":"王亚蒙","email":null,"role":"1","loginTime":null,"userId":"35","pName":"전라북도","cName":"정읍시","headImg":"http://img03.iambuyer.com/imgup/upload/images/2018/03/27/kDiaG2pBI8obOwIBsTS6hrcRbcb8qSqhh3AJJTmjNTOgahcjjc2FfZDCwmhGIc7BrRDfhy.jpg","phone":"15901368829","ssoUserId":203,"userQrimg":"http://api.iambuyer.com/rest-iambuyer/upload/qr/city/18610115025.jpg","userContent":null,"referPhone":null,"createTimeStart":null,"createTimeEnd":null,"loginTimeEnd":null,"loginTimeStart":null,"referUserName":null,"referUserid":null,"createTime":null},"sysCategoryStr":null,"userPosition":null,"userHeadImg":null,"createMan":"iambuyer","updateMan":"iambuyer","sendTime":"1","brandName":"RO","isMade":"1","userId":203,"productId":221,"sysType":"IambuyerKorea","isOnline":"1","lookCount":16,"phone":"1","states":"0","productUnit":"1","productPrice":"0","shareCount":0,"prodownUserid":null,"prodownTime":null,"publishTime":null,"prodownContent":null,"productTotal":100,"productMaxcount":20,"buyerContent":"1","productNum":"1","productSize":"1","productPkgsize":"1","productWeight":"1","productColor":"1","productMoney":1,"productStates":null,"firstCateId":"001","fourthCateId":null,"isHaveGoods":"1","updateTime":1513924574000,"createTime":1513924439000,"loopImg002":null,"loopImg003":null,"loopImg004":null,"loopImg005":null,"thirdCateId":"","secondCateId":"","deliverGoogsId":62,"providerId":61,"productName":"2017款 苹果笔记本","loopImg001":"http://img07.iambuyer.com/imgup/upload/images/2017/12/22/IXNbKLg6PaJ3Zh6Thcjjc2Bfy334aHhcjjc2BNNqNylhVZhcjjc2Bmiemb7GK4FaNcW0Gr0vRKFOBNXhcjjc2FGhcjjc2F.jpg","recordCount":5}]
     */

    public int count;
    public List<ListBean> list;

    public static class ListBean {
        /**
         * typeName : null
         * userName : null
         * minPrice : null
         * compName : 321
         * unit : null
         * intro : null
         * addStr : null
         * priceList : null
         * imgs : null
         * korCoreUserVo : {"userName":"柳","email":null,"role":"-1","loginTime":null,"userId":"171","pName":null,"cName":null,"headImg":null,"phone":"18032263017","ssoUserId":202,"userQrimg":null,"userContent":null,"referPhone":"15901368829","createTimeStart":null,"createTimeEnd":null,"loginTimeEnd":null,"loginTimeStart":null,"referUserName":"王亚蒙","referUserid":203,"createTime":null}
         * sysCategoryStr : null
         * userPosition : null
         * userHeadImg : null
         * createMan : iambuyerKorea
         * updateMan : iambuyerKorea
         * sendTime : 三个月内
         * brandName : null
         * isMade : 1
         * userId : 202
         * productId : 244
         * sysType : IambuyerKorea
         * isOnline : 1
         * lookCount : 7
         * phone : 3213123
         * states : 0
         * productUnit : 3123
         * productPrice : 0
         * shareCount : 0
         * prodownUserid : null
         * prodownTime : null
         * publishTime : null
         * prodownContent : null
         * productTotal : 3123
         * productMaxcount : 323
         * buyerContent : 31232131
         * productNum : 2312312
         * productSize : 213
         * productPkgsize : 123123
         * productWeight : 123123
         * productColor : 123123
         * productMoney : 21312
         * productStates : null
         * firstCateId : null
         * fourthCateId : null
         * isHaveGoods : null
         * updateTime : 1523497262000
         * createTime : 1523497262000
         * loopImg002 : null
         * loopImg003 : http://img01.iambuyer.com/imgup/upload/images/2018/04/12/0d7HE4yUufr9uev8bhcjjc2B4jrlgoKLhcjjc2BqSFw5hcjjc2BwHdlpkb8EzQYHhcjjc2BJqdZYFYTjxilsh4it.png
         * loopImg004 : null
         * loopImg005 : null
         * thirdCateId : null
         * secondCateId : null
         * deliverGoogsId : null
         * providerId : null
         * productName : 312312
         * loopImg001 : http://img04.iambuyer.com/imgup/upload/images/2018/04/12/VYTYyGRENejwnaNcSfhcjjc2Fhcjjc2BQg3gqYLQ2pAwTVy3Z7yjhcjjc2FriXGHMWS27BWbYy1Y6PR7yq.png
         * recordCount : 3
         */

        public Object typeName;
        public String userName;
        public Object minPrice;
        public String compName;
        public Object unit;
        public Object intro;
        public Object addStr;
        public Object priceList;
        public Object imgs;
        public KorCoreUserVoBean korCoreUserVo;
        public Object sysCategoryStr;
        public Object userPosition;
        public String userHeadImg;
        public String createMan;
        public String updateMan;
        public String sendTime;
        public Object brandName;
        public String isMade;
        public int userId;
        public int productId;
        public String sysType;
        public String isOnline;
        public int lookCount;
        public String phone;
        public String states;
        public String productUnit;
        public String productPrice;
        public int shareCount;
        public Object prodownUserid;
        public Object prodownTime;
        public Object publishTime;
        public Object prodownContent;
        public int productTotal;
        public int productMaxcount;
        public String buyerContent;
        public String productNum;
        public String productSize;
        public String productPkgsize;
        public String productWeight;
        public String productColor;
        public BigDecimal productMoney;
        public Object productStates;
        public Object firstCateId;
        public Object fourthCateId;
        public Object isHaveGoods;
        public long updateTime;
        public long createTime;
        public Object loopImg002;
        public String loopImg003;
        public Object loopImg004;
        public Object loopImg005;
        public Object thirdCateId;
        public Object secondCateId;
        public Object deliverGoogsId;
        public Object providerId;
        public String productName;
        public String loopImg001;
        public int recordCount;
        public int followCount;

        public static class KorCoreUserVoBean {
            /**
             * userName : 柳
             * email : null
             * role : -1
             * loginTime : null
             * userId : 171
             * pName : null
             * cName : null
             * headImg : null
             * phone : 18032263017
             * ssoUserId : 202
             * userQrimg : null
             * userContent : null
             * referPhone : 15901368829
             * createTimeStart : null
             * createTimeEnd : null
             * loginTimeEnd : null
             * loginTimeStart : null
             * referUserName : 王亚蒙
             * referUserid : 203
             * createTime : null
             */

            public String userName;
            public Object email;
            public String role;
            public Object loginTime;
            public String userId;
            public Object pName;
            public Object cName;
            public String headImg;
            public String phone;
            public int ssoUserId;
            public Object userQrimg;
            public Object userContent;
            public String referPhone;
            public Object createTimeStart;
            public Object createTimeEnd;
            public Object loginTimeEnd;
            public Object loginTimeStart;
            public String referUserName;
            public int referUserid;
            public Object createTime;
        }
    }
}
