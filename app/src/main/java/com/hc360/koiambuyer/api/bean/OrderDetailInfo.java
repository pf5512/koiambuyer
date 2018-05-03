package com.hc360.koiambuyer.api.bean;

import java.math.BigDecimal;
import java.util.List;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/30
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class OrderDetailInfo {


    /**
     * content : {"stProductOrderContents":[{"id":1,"content":"办理办理","userId":365,"orderState":"0","createTime":1521540645000,"orderNo":"f6e1b05a-b1ab-4321-926d-c6a7a20e93e0"},{"id":2,"content":"办理成功已发货","userId":365,"orderState":"1","createTime":1522308025000,"orderNo":"f6e1b05a-b1ab-4321-926d-c6a7a20e93e0"}],"stProductOrderAccount":{"id":2,"states":"0","createTime":1521183713000,"payImg":null,"orderNo":"f6e1b05a-b1ab-4321-926d-c6a7a20e93e0","createMan":"iambuyerKorea","updateTime":1521183713000,"updateMan":"iambuyerKorea","money":200.5,"payMoney":null,"payDate":null},"stProductOrderExpress":{"id":1,"states":"0","createTime":1521183713000,"expressImg":null,"expressNo":null,"expressComp":"家成物流","sendDate":null,"orderNo":"f6e1b05a-b1ab-4321-926d-c6a7a20e93e0","createMan":"iambuyerKorea","updateTime":1521183713000,"updateMan":"iambuyerKorea"},"priceList":[{"states":"0","productId":221,"price":1000,"unit":"台","priceId":10,"minNumber":10,"maxNumber":100},{"states":"0","productId":221,"price":10,"unit":"件","priceId":9,"minNumber":100,"maxNumber":200}],"successTime":null,"sendTime":1522308025000,"spProduct":{"productName":"2017款 苹果笔记本","states":"0","userId":203,"compName":"1","createTime":1513924439000,"loopImg002":null,"loopImg003":null,"loopImg004":null,"loopImg005":null,"thirdCateId":"004050002","secondCateId":"004050","deliverGoogsId":62,"providerId":61,"loopImg001":"http://img03.iambuyer.com/imgup/upload/images/2017/12/22/IXNbKLg6PaJ3Zh6Thcjjc2Bfy334aHhcjjc2BNNqNylhVZhcjjc2Bmiemb7GK4FaNcW0Gr0vRKFOBNXhcjjc2FGhcjjc2F.jpg","productId":221,"sysType":"IambuyerKorea","recordCount":5,"lookCount":13,"phone":"1","createMan":"iambuyer","updateTime":1513924574000,"updateMan":"iambuyer","productPrice":"0","shareCount":0,"productTotal":100,"productMaxcount":20,"buyerContent":"1","productNum":"1","productSize":"1","productPkgsize":"1","productWeight":"1","productColor":"1","productMoney":1,"productStates":null,"brandName":"RO","isOnline":"1","firstCateId":"004","fourthCateId":null,"isHaveGoods":"1","isMade":"1","sendTime":"1","productUnit":"1"},"cancelTime":null,"goodsReceive":{"pName":"서울특별시","cName":"서울특별시","states":"0","userId":365,"compId":null,"provinceCode":"001","cityCode":"001001","areaCode":null,"addressDetail":"地址","useState":"0","createTime":1521178791000,"createMan":"00","updateTime":1522659751000,"updateMan":"iambuyer","telphone":"18611874477","receiveId":1,"receiveUser":"地址"},"id":3,"states":"0","userId":365,"orderState":"4","createTime":1521183713000,"goodsId":1,"endTime":null,"proId":221,"orderNo":"f6e1b05a-b1ab-4321-926d-c6a7a20e93e0","createMan":"iambuyerKorea","updateTime":1522822105000,"updateMan":"iambuyerKorea","orderSimunitpri":null,"orderSimprice":null,"orderPrice":null,"orderContent":"备注说明--JJC测试","orderSimcount":10,"orderCode":"201803161501539bj5krh","orderCount":100,"orderUnitpri":200.1}
     * ret : 200
     * msg : success
     */

    public ContentBean content;
    public String ret;
    public String msg;

    public static class ContentBean {
        /**
         * stProductOrderContents : [{"id":1,"content":"办理办理","userId":365,"orderState":"0","createTime":1521540645000,"orderNo":"f6e1b05a-b1ab-4321-926d-c6a7a20e93e0"},{"id":2,"content":"办理成功已发货","userId":365,"orderState":"1","createTime":1522308025000,"orderNo":"f6e1b05a-b1ab-4321-926d-c6a7a20e93e0"}]
         * stProductOrderAccount : {"id":2,"states":"0","createTime":1521183713000,"payImg":null,"orderNo":"f6e1b05a-b1ab-4321-926d-c6a7a20e93e0","createMan":"iambuyerKorea","updateTime":1521183713000,"updateMan":"iambuyerKorea","money":200.5,"payMoney":null,"payDate":null}
         * stProductOrderExpress : {"id":1,"states":"0","createTime":1521183713000,"expressImg":null,"expressNo":null,"expressComp":"家成物流","sendDate":null,"orderNo":"f6e1b05a-b1ab-4321-926d-c6a7a20e93e0","createMan":"iambuyerKorea","updateTime":1521183713000,"updateMan":"iambuyerKorea"}
         * priceList : [{"states":"0","productId":221,"price":1000,"unit":"台","priceId":10,"minNumber":10,"maxNumber":100},{"states":"0","productId":221,"price":10,"unit":"件","priceId":9,"minNumber":100,"maxNumber":200}]
         * successTime : null
         * sendTime : 1522308025000
         * spProduct : {"productName":"2017款 苹果笔记本","states":"0","userId":203,"compName":"1","createTime":1513924439000,"loopImg002":null,"loopImg003":null,"loopImg004":null,"loopImg005":null,"thirdCateId":"004050002","secondCateId":"004050","deliverGoogsId":62,"providerId":61,"loopImg001":"http://img03.iambuyer.com/imgup/upload/images/2017/12/22/IXNbKLg6PaJ3Zh6Thcjjc2Bfy334aHhcjjc2BNNqNylhVZhcjjc2Bmiemb7GK4FaNcW0Gr0vRKFOBNXhcjjc2FGhcjjc2F.jpg","productId":221,"sysType":"IambuyerKorea","recordCount":5,"lookCount":13,"phone":"1","createMan":"iambuyer","updateTime":1513924574000,"updateMan":"iambuyer","productPrice":"0","shareCount":0,"productTotal":100,"productMaxcount":20,"buyerContent":"1","productNum":"1","productSize":"1","productPkgsize":"1","productWeight":"1","productColor":"1","productMoney":1,"productStates":null,"brandName":"RO","isOnline":"1","firstCateId":"004","fourthCateId":null,"isHaveGoods":"1","isMade":"1","sendTime":"1","productUnit":"1"}
         * cancelTime : null
         * goodsReceive : {"pName":"서울특별시","cName":"서울특별시","states":"0","userId":365,"compId":null,"provinceCode":"001","cityCode":"001001","areaCode":null,"addressDetail":"地址","useState":"0","createTime":1521178791000,"createMan":"00","updateTime":1522659751000,"updateMan":"iambuyer","telphone":"18611874477","receiveId":1,"receiveUser":"地址"}
         * id : 3
         * states : 0
         * userId : 365
         * orderState : 4
         * createTime : 1521183713000
         * goodsId : 1
         * endTime : null
         * proId : 221
         * orderNo : f6e1b05a-b1ab-4321-926d-c6a7a20e93e0
         * createMan : iambuyerKorea
         * updateTime : 1522822105000
         * updateMan : iambuyerKorea
         * orderSimunitpri : null
         * orderSimprice : null
         * orderPrice : null
         * orderContent : 备注说明--JJC测试
         * orderSimcount : 10
         * orderCode : 201803161501539bj5krh
         * orderCount : 100
         * orderUnitpri : 200.1
         */

        public StProductOrderAccountBean stProductOrderAccount;
        public StProductOrderExpressBean stProductOrderExpress;
        public KorCoreUserVoBean korCoreUserVo;
        public long successTime;
        public long sendTime;
        public SpProductBean spProduct;
        public long cancelTime;
        public long sendDate;
        public GoodsReceiveBean goodsReceive;
        public int id;
        public String states;
        public int userId;
        public String orderState;
        public long createTime;
        public int goodsId;
        public Object endTime;
        public int proId;
        public String orderNo;
        public String createMan;
        public long updateTime;
        public String updateMan;
        public BigDecimal orderSimunitpri;
        public BigDecimal orderSimprice;
        public BigDecimal orderPrice;
        public String orderContent;
        public int orderSimcount;
        public String orderCode;
        public int orderCount;
        public double orderUnitpri;
        public List<StProductOrderContentsBean> stProductOrderContents;
        public List<PriceListBean> priceList;

        public static class StProductOrderAccountBean {
            /**
             * id : 2
             * states : 0
             * createTime : 1521183713000
             * payImg : null
             * orderNo : f6e1b05a-b1ab-4321-926d-c6a7a20e93e0
             * createMan : iambuyerKorea
             * updateTime : 1521183713000
             * updateMan : iambuyerKorea
             * money : 200.5
             * payMoney : null
             * payDate : null
             */

            public int id;
            public String states;
            public long createTime;
            public Object payImg;
            public String orderNo;
            public String createMan;
            public long updateTime;
            public String updateMan;
            public double money;
            public Object payMoney;
            public Object payDate;
        }

        public static class StProductOrderExpressBean {
            /**
             * id : 1
             * states : 0
             * createTime : 1521183713000
             * expressImg : null
             * expressNo : null
             * expressComp : 家成物流
             * sendDate : null
             * orderNo : f6e1b05a-b1ab-4321-926d-c6a7a20e93e0
             * createMan : iambuyerKorea
             * updateTime : 1521183713000
             * updateMan : iambuyerKorea
             */

            public int id;
            public String states;
            public long createTime;
            public String expressImg;
            public String expressNo;
            public String expressComp;
            public long sendDate;
            public String orderNo;
            public String createMan;
            public long updateTime;
            public String updateMan;
        }

        public static class SpProductBean {
            /**
             * productName : 2017款 苹果笔记本
             * states : 0
             * userId : 203
             * compName : 1
             * createTime : 1513924439000
             * loopImg002 : null
             * loopImg003 : null
             * loopImg004 : null
             * loopImg005 : null
             * thirdCateId : 004050002
             * secondCateId : 004050
             * deliverGoogsId : 62
             * providerId : 61
             * loopImg001 : http://img03.iambuyer.com/imgup/upload/images/2017/12/22/IXNbKLg6PaJ3Zh6Thcjjc2Bfy334aHhcjjc2BNNqNylhVZhcjjc2Bmiemb7GK4FaNcW0Gr0vRKFOBNXhcjjc2FGhcjjc2F.jpg
             * productId : 221
             * sysType : IambuyerKorea
             * recordCount : 5
             * lookCount : 13
             * phone : 1
             * createMan : iambuyer
             * updateTime : 1513924574000
             * updateMan : iambuyer
             * productPrice : 0
             * shareCount : 0
             * productTotal : 100
             * productMaxcount : 20
             * buyerContent : 1
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
             * isHaveGoods : 1
             * isMade : 1
             * sendTime : 1
             * productUnit : 1
             */

            public String productName;
            public String states;
            public int userId;
            public String compName;
            public long createTime;
            public Object loopImg002;
            public Object loopImg003;
            public Object loopImg004;
            public Object loopImg005;
            public String thirdCateId;
            public String secondCateId;
            public int deliverGoogsId;
            public int providerId;
            public String loopImg001;
            public int productId;
            public String sysType;
            public int recordCount;
            public int lookCount;
            public String phone;
            public String createMan;
            public long updateTime;
            public String updateMan;
            public String productPrice;
            public int shareCount;
            public int productTotal;
            public int productMaxcount;
            public String buyerContent;
            public String productNum;
            public String productSize;
            public String productPkgsize;
            public String productWeight;
            public String productColor;
            public int productMoney;
            public Object productStates;
            public String brandName;
            public String isOnline;
            public String firstCateId;
            public Object fourthCateId;
            public String isHaveGoods;
            public String isMade;
            public String sendTime;
            public String productUnit;
        }

        public static class KorCoreUserVoBean {


            public String role;
            public Object email;
            public String userQrimg;
            public String userId;
            public String pName;
            public String cName;
            public String headImg;
            public String phone;
            public int ssoUserId;
            public Object referUserid;
            public String userName;
            public Object createTime;
            public Object userContent;
            public Object loginTime;
            public Object referPhone;
            public Object createTimeStart;
            public Object createTimeEnd;
            public Object loginTimeEnd;
            public Object loginTimeStart;
            public Object referUserName;
        }

        public static class GoodsReceiveBean {
            /**
             * pName : 서울특별시
             * cName : 서울특별시
             * states : 0
             * userId : 365
             * compId : null
             * provinceCode : 001
             * cityCode : 001001
             * areaCode : null
             * addressDetail : 地址
             * useState : 0
             * createTime : 1521178791000
             * createMan : 00
             * updateTime : 1522659751000
             * updateMan : iambuyer
             * telphone : 18611874477
             * receiveId : 1
             * receiveUser : 地址
             */

            public String pName;
            public String cName;
            public String states;
            public int userId;
            public Object compId;
            public String provinceCode;
            public String cityCode;
            public Object areaCode;
            public String addressDetail;
            public String useState;
            public long createTime;
            public String createMan;
            public long updateTime;
            public String updateMan;
            public String telphone;
            public int receiveId;
            public String receiveUser;
        }

        public static class StProductOrderContentsBean {
            /**
             * id : 1
             * content : 办理办理
             * userId : 365
             * orderState : 0
             * createTime : 1521540645000
             * orderNo : f6e1b05a-b1ab-4321-926d-c6a7a20e93e0
             */

            public int id;
            public String content;
            public int userId;
            public String orderState;
            public long createTime;
            public String orderNo;
        }

        public static class PriceListBean {
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
        }
    }
}
