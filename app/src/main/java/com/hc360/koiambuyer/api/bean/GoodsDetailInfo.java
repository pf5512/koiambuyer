package com.hc360.koiambuyer.api.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/20
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class GoodsDetailInfo implements Serializable{

    /**
     * content : {"spProduct":{"createTime":1513924439000,"loopImg002":null,"loopImg003":null,"loopImg004":null,"loopImg005":null,"thirdCateId":"004050002","secondCateId":"004050","deliverGoogsId":62,"providerId":61,"states":"0","shareCount":0,"loopImg001":"http://img07.iambuyer.com/imgup/upload/images/2017/12/22/IXNbKLg6PaJ3Zh6Thcjjc2Bfy334aHhcjjc2BNNqNylhVZhcjjc2Bmiemb7GK4FaNcW0Gr0vRKFOBNXhcjjc2FGhcjjc2F.jpg","productId":221,"sysType":"IambuyerKorea","recordCount":5,"lookCount":13,"sendTime":"1","productUnit":"1","productPrice":"0","productName":"2017款 苹果笔记本","phone":"1","userId":203,"compName":"1","productNum":"1","productSize":"1","productPkgsize":"1","productWeight":"1","productColor":"1","productMoney":1,"productStates":null,"brandName":"RO","isOnline":"1","firstCateId":"004","fourthCateId":null,"productTotal":1,"productMaxcount":1,"buyerContent":"1","isHaveGoods":"1","isMade":"1","createMan":"iambuyer","updateTime":1513924574000,"updateMan":"iambuyer"},"SpProductPice":[{"states":"0","productId":221,"price":1000,"unit":"台","priceId":10,"minNumber":10,"maxNumber":100},{"states":"0","productId":221,"price":10,"unit":"件","priceId":9,"minNumber":100,"maxNumber":200}],"followCount":3,"maxPice":1000,"spProductImgS":[{"id":667,"createTime":1513924574000,"productId":221,"imgName":"http://img01.iambuyer.com/imgup/upload/images/2017/12/22/ZQLwKAhcjjc2Ft5bPG17bOBnrwmHUUprNsVAz1uF4Gyhcjjc2FZjHX41a9nDRAswhcmD2p07ahQ6.jpg"}],"followComp":0,"user":{"referUserid":null,"createTime":null,"userQrimg":null,"email":null,"role":"1","userName":"王亚蒙","headImg":null,"ssoUserId":203,"pName":"전라북도","cName":"정읍시","phone":"15901368829","userContent":null,"loginTime":null,"createTimeStart":null,"createTimeEnd":null,"loginTimeEnd":null,"loginTimeStart":null,"referUserName":null,"userId":"35"},"minPice":10,"spProductIntro":{"states":"0","productId":221,"introId":370,"img006":null,"img007":null,"img008":null,"img009":null,"img010":null,"productIntro":"了","img001":null,"img002":null,"img003":null,"img004":null,"img005":null}}
     * ret : 200
     * msg : success
     */

    public ContentBean content;
    public String ret;
    public String msg;

    public static class ContentBean implements Serializable{
        /**
         * spProduct : {"createTime":1513924439000,"loopImg002":null,"loopImg003":null,"loopImg004":null,"loopImg005":null,"thirdCateId":"004050002","secondCateId":"004050","deliverGoogsId":62,"providerId":61,"states":"0","shareCount":0,"loopImg001":"http://img07.iambuyer.com/imgup/upload/images/2017/12/22/IXNbKLg6PaJ3Zh6Thcjjc2Bfy334aHhcjjc2BNNqNylhVZhcjjc2Bmiemb7GK4FaNcW0Gr0vRKFOBNXhcjjc2FGhcjjc2F.jpg","productId":221,"sysType":"IambuyerKorea","recordCount":5,"lookCount":13,"sendTime":"1","productUnit":"1","productPrice":"0","productName":"2017款 苹果笔记本","phone":"1","userId":203,"compName":"1","productNum":"1","productSize":"1","productPkgsize":"1","productWeight":"1","productColor":"1","productMoney":1,"productStates":null,"brandName":"RO","isOnline":"1","firstCateId":"004","fourthCateId":null,"productTotal":1,"productMaxcount":1,"buyerContent":"1","isHaveGoods":"1","isMade":"1","createMan":"iambuyer","updateTime":1513924574000,"updateMan":"iambuyer"}
         * SpProductPice : [{"states":"0","productId":221,"price":1000,"unit":"台","priceId":10,"minNumber":10,"maxNumber":100},{"states":"0","productId":221,"price":10,"unit":"件","priceId":9,"minNumber":100,"maxNumber":200}]
         * followCount : 3
         * maxPice : 1000
         * spProductImgS : [{"id":667,"createTime":1513924574000,"productId":221,"imgName":"http://img01.iambuyer.com/imgup/upload/images/2017/12/22/ZQLwKAhcjjc2Ft5bPG17bOBnrwmHUUprNsVAz1uF4Gyhcjjc2FZjHX41a9nDRAswhcmD2p07ahQ6.jpg"}]
         * followComp : 0
         * user : {"referUserid":null,"createTime":null,"userQrimg":null,"email":null,"role":"1","userName":"王亚蒙","headImg":null,"ssoUserId":203,"pName":"전라북도","cName":"정읍시","phone":"15901368829","userContent":null,"loginTime":null,"createTimeStart":null,"createTimeEnd":null,"loginTimeEnd":null,"loginTimeStart":null,"referUserName":null,"userId":"35"}
         * minPice : 10
         * spProductIntro : {"states":"0","productId":221,"introId":370,"img006":null,"img007":null,"img008":null,"img009":null,"img010":null,"productIntro":"了","img001":null,"img002":null,"img003":null,"img004":null,"img005":null}
         */

        public SpProductBean spProduct;
        public int followCount;
        public BigDecimal maxPice;
        public int followPro;
        public UserBean user;
        public BigDecimal minPice;
        public int isCreateOrder = -1;
        public SpProductIntroBean spProductIntro;
        public List<SpProductPiceBean> SpProductPice;
        public List<SpProductImgSBean> spProductImgS;

        public static class SpProductBean implements Serializable {
            /**
             * createTime : 1513924439000
             * loopImg002 : null
             * loopImg003 : null
             * loopImg004 : null
             * loopImg005 : null
             * thirdCateId : 004050002
             * secondCateId : 004050
             * deliverGoogsId : 62
             * providerId : 61
             * states : 0
             * shareCount : 0
             * loopImg001 : http://img07.iambuyer.com/imgup/upload/images/2017/12/22/IXNbKLg6PaJ3Zh6Thcjjc2Bfy334aHhcjjc2BNNqNylhVZhcjjc2Bmiemb7GK4FaNcW0Gr0vRKFOBNXhcjjc2FGhcjjc2F.jpg
             * productId : 221
             * sysType : IambuyerKorea
             * recordCount : 5
             * lookCount : 13
             * sendTime : 1
             * productUnit : 1
             * productPrice : 0
             * productName : 2017款 苹果笔记本
             * phone : 1
             * userId : 203
             * compName : 1
             * productNum : 1
             * productSize : 1
             * productPkgsize : 1
             * productWeight : 1
             * productColor : 1
             * productMoney : 1
             * productStates : null
             * brandName : RO
             * isOnline : 1
             * firstCateId : 004
             * fourthCateId : null
             * productTotal : 1
             * productMaxcount : 1
             * buyerContent : 1
             * isHaveGoods : 1
             * isMade : 1
             * createMan : iambuyer
             * updateTime : 1513924574000
             * updateMan : iambuyer
             */

            public long createTime;
            public String loopImg002;
            public String loopImg003;
            public String loopImg004;
            public String loopImg005;
            public String thirdCateId;
            public String secondCateId;
            public int deliverGoogsId;
            public int providerId;
            public String states;
            public int shareCount;
            public String loopImg001;
            public int productId;
            public String sysType;
            public int recordCount;
            public int lookCount;
            public String sendTime;
            public String productUnit;
            public String productPrice;
            public String productName;
            public String phone;
            public int userId;
            public String compName;
            public String productNum;
            public String productSize;
            public String productPkgsize;
            public String productWeight;
            public String productColor;
            public BigDecimal productMoney;
            public Object productStates;
            public String brandName;
            public String isOnline;
            public String firstCateId;
            public Object fourthCateId;
            public int productTotal;
            public int productMaxcount;
            public String buyerContent;
            public String isHaveGoods;
            public String isMade;
            public String createMan;
            public long updateTime;
            public String updateMan;
        }

        public static class UserBean implements Serializable{
            /**
             * referUserid : null
             * createTime : null
             * userQrimg : null
             * email : null
             * role : 1
             * userName : 王亚蒙
             * headImg : null
             * ssoUserId : 203
             * pName : 전라북도
             * cName : 정읍시
             * phone : 15901368829
             * userContent : null
             * loginTime : null
             * createTimeStart : null
             * createTimeEnd : null
             * loginTimeEnd : null
             * loginTimeStart : null
             * referUserName : null
             * userId : 35
             */

            public Object referUserid;
            public Object createTime;
            public Object userQrimg;
            public Object email;
            public String role;
            public String userName;
            public String headImg;
            public int ssoUserId;
            public String pName;
            public String cName;
            public String phone;
            public String userContent;
            public Object loginTime;
            public Object createTimeStart;
            public Object createTimeEnd;
            public Object loginTimeEnd;
            public Object loginTimeStart;
            public Object referUserName;
            public String userId;
        }

        public static class SpProductIntroBean implements Serializable{
            /**
             * states : 0
             * productId : 221
             * introId : 370
             * img006 : null
             * img007 : null
             * img008 : null
             * img009 : null
             * img010 : null
             * productIntro : 了
             * img001 : null
             * img002 : null
             * img003 : null
             * img004 : null
             * img005 : null
             */

            public String states;
            public int productId;
            public int introId;
            public Object img006;
            public Object img007;
            public Object img008;
            public Object img009;
            public Object img010;
            public String productIntro;
            public String img001;
            public String img002;
            public String img003;
            public String img004;
            public String img005;
        }

        public static class SpProductPiceBean implements Serializable{

            /**
             * states : 0
             * productId : 221
             * price : 1000
             * unit : 台
             * priceId : 10
             * minNumber : 10
             * maxNumber : 100
             */

            public String states;
            public int productId;
            public BigDecimal price;
            public String unit;
            public int priceId;
            public int minNumber;
            public int maxNumber;

            public SpProductPiceBean(String states, int productId, BigDecimal price, String unit, int priceId, int minNumber, int maxNumber) {
                this.states = states;
                this.productId = productId;
                this.price = price;
                this.unit = unit;
                this.priceId = priceId;
                this.minNumber = minNumber;
                this.maxNumber = maxNumber;
            }
        }

        public static class SpProductImgSBean implements Serializable{

            /**
             * id : 667
             * createTime : 1513924574000
             * productId : 221
             * imgName : http://img01.iambuyer.com/imgup/upload/images/2017/12/22/ZQLwKAhcjjc2Ft5bPG17bOBnrwmHUUprNsVAz1uF4Gyhcjjc2FZjHX41a9nDRAswhcmD2p07ahQ6.jpg
             */

            public int id;
            public long createTime;
            public int productId;
            public String imgName;
        }
    }
}
