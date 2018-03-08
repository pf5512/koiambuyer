package com.hc360.koiambuyer.api.bean;

import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/7
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class SupplyHomeInfo {

    /**
     * buy : [{"id":1,"serialNumber":1,"states":"0","createMan":null,"updateMan":null,"title":"飞无缝武器","cruxKey":"非单身","createTime":null,"updateTime":null,"imgName":"http://img08.iambuyer.com/imgup/upload/images/2017/10/31/r2tpNhcjjc2BuawQWiWw9r1hcjjc2FmQu65CKT4u6kchUK53zouXnKNgE5wBlUKJ15zpTWsFXuMq.jpg"},{"id":2,"serialNumber":2,"states":"0","createMan":null,"updateMan":null,"title":"2","cruxKey":"321","createTime":null,"updateTime":null,"imgName":"http://img01.iambuyer.com/imgup/upload/images/2017/10/13/MQGjTYyi2ehYaD5bwnTciZcbIouvmtcD0KtbHu5qvfbRQwgEKg6Xxhcjjc2BaJURaXF3m1.jpg"},{"id":3,"serialNumber":3,"states":"0","createMan":null,"updateMan":null,"title":"3","cruxKey":"3214","createTime":null,"updateTime":null,"imgName":"http://img07.iambuyer.com/imgup/upload/images/2017/10/13/MQGjTYyi2ehYaD5bwnTciZcbIouvmtcD0KtbHu5qvfbRQwgEKg6Xxhcjjc2BaJURaXF3m1.jpg"},{"id":4,"serialNumber":4,"states":"0","createMan":null,"updateMan":null,"title":"6","cruxKey":"dwq","createTime":null,"updateTime":null,"imgName":"http://img03.iambuyer.com/imgup/upload/images/2017/10/13/MQGjTYyi2ehYaD5bwnTciZcbIouvmtcD0KtbHu5qvfbRQwgEKg6Xxhcjjc2BaJURaXF3m1.jpg"},{"id":5,"serialNumber":5,"states":"0","createMan":null,"updateMan":null,"title":"金拱门","cruxKey":"好热门","createTime":null,"updateTime":null,"imgName":"http://img01.iambuyer.com/imgup/upload/images/2017/10/30/hBbBi5WaOQPsSFTwTjdWg5u63DTa843QVdY6WEewxATSbEaeJbhcjjc2BMiuSKevkZwhcjjc2FAhcjjc2F.jpg"},{"id":6,"serialNumber":5,"states":"0","createMan":null,"updateMan":"admin","title":"66666","cruxKey":"热风额外热舞","createTime":null,"updateTime":1509439966000,"imgName":"http://img05.iambuyer.com/imgup/upload/images/2017/10/30/8hcjjc2BVLRrOVpOx9h31dgmfOGIp7BbLSB7TKRpVUSxn2tAhOwM4RXUS0jck2hAQfHz83.jpg"},{"id":7,"serialNumber":22,"states":"0","createMan":"admin","updateMan":"admin","title":"1","cruxKey":"1","createTime":1509439778000,"updateTime":1509439952000,"imgName":"http://img03.iambuyer.com/imgup/upload_bak/images/2017/10/31/BqA7UWiGKBVBhhcjjc2BV9eHgg6bJ1zX4a8ukfM1CEX8GSVxvBDKfxM344YV8DohW3pzck.jpg"}]
     * endPurchase : [{"recordCount":1,"userHeadImg":"http://img01.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg","proList":[{"day":69,"recordCount":1,"productListTitle":"公司采购2017年年会奖品","productNum":100,"states":"0","productId":1,"unit":"个","productName":"名称1","productListId":1},{"day":69,"recordCount":1,"productListTitle":"公司采购2017年年会奖品","productNum":111,"states":"0","productId":12,"unit":"个","productName":"名称2","productListId":1}],"userId":51,"day":69,"productListTitle":"公司采购2017年年会奖品","productListId":1},{"recordCount":0,"userHeadImg":"http://img08.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg","proList":[{"day":22,"recordCount":0,"productListTitle":"标题","productNum":100,"states":"0","productId":23,"unit":"个","productName":"名称1","productListId":7},{"day":22,"recordCount":0,"productListTitle":"标题","productNum":111,"states":"0","productId":24,"unit":"个","productName":"名称2","productListId":7}],"userId":51,"day":22,"productListTitle":"标题","productListId":7}]
     * carousel : [{"id":2,"content":"凤舞","serialNumber":2,"jumpContent":"047","states":"0","createMan":null,"updateMan":null,"loginType":"1","jumpType":"0","createTime":1508913878000,"updateTime":1508742960000,"imgName":"http://img01.iambuyer.com/imgup/upload/images/2017/10/31/BzVDehcjjc2FXjs1Gv7btbtbwuVEhcjjc2BjEO1lJJiLiwzfBRtGPtjzOW7sLviOEpERPSuE2Dhcjjc2B7.jpg"},{"id":4,"content":"v无非","serialNumber":4,"jumpContent":"http://www.baidu.com","states":"0","createMan":null,"updateMan":null,"loginType":"1","jumpType":"1","createTime":1508915896000,"updateTime":1508915898000,"imgName":"http://img08.iambuyer.com/imgup/upload/images/2017/10/27/L8vRF4hcjjc2Fv5KHmQZ5IdD2d0JYT1AtJBSBaxLiD548H5fntANYbDCTg4i51yxpCX7rE.jpg"}]
     */

    public List<BuyBean> buy;
    public List<EndPurchaseBean> endPurchase;
    public List<CarouselBean> carousel;

    public static class BuyBean {
        /**
         * id : 1
         * serialNumber : 1
         * states : 0
         * createMan : null
         * updateMan : null
         * title : 飞无缝武器
         * cruxKey : 非单身
         * createTime : null
         * updateTime : null
         * imgName : http://img08.iambuyer.com/imgup/upload/images/2017/10/31/r2tpNhcjjc2BuawQWiWw9r1hcjjc2FmQu65CKT4u6kchUK53zouXnKNgE5wBlUKJ15zpTWsFXuMq.jpg
         */

        public int id;
        public int serialNumber;
        public String states;
        public Object createMan;
        public Object updateMan;
        public String title;
        public String cruxKey;
        public Object createTime;
        public Object updateTime;
        public String imgName;
    }

    public static class EndPurchaseBean {
        /**
         * recordCount : 1
         * userHeadImg : http://img01.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg
         * proList : [{"day":69,"recordCount":1,"productListTitle":"公司采购2017年年会奖品","productNum":100,"states":"0","productId":1,"unit":"个","productName":"名称1","productListId":1},{"day":69,"recordCount":1,"productListTitle":"公司采购2017年年会奖品","productNum":111,"states":"0","productId":12,"unit":"个","productName":"名称2","productListId":1}]
         * userId : 51
         * day : 69
         * productListTitle : 公司采购2017年年会奖品
         * productListId : 1
         */

        public int recordCount;
        public String userHeadImg;
        public int userId;
        public int day;
        public String productListTitle;
        public int productListId;
        public List<ProListBean> proList;

        public static class ProListBean {
            /**
             * day : 69
             * recordCount : 1
             * productListTitle : 公司采购2017年年会奖品
             * productNum : 100
             * states : 0
             * productId : 1
             * unit : 个
             * productName : 名称1
             * productListId : 1
             */

            public int day;
            public int recordCount;
            public String productListTitle;
            public int productNum;
            public String states;
            public int productId;
            public String unit;
            public String productName;
            public int productListId;
        }
    }

    public static class CarouselBean {
        /**
         * id : 2
         * content : 凤舞
         * serialNumber : 2
         * jumpContent : 047
         * states : 0
         * createMan : null
         * updateMan : null
         * loginType : 1
         * jumpType : 0
         * createTime : 1508913878000
         * updateTime : 1508742960000
         * imgName : http://img01.iambuyer.com/imgup/upload/images/2017/10/31/BzVDehcjjc2FXjs1Gv7btbtbwuVEhcjjc2BjEO1lJJiLiwzfBRtGPtjzOW7sLviOEpERPSuE2Dhcjjc2B7.jpg
         */

        public int id;
        public String content;
        public int serialNumber;
        public String jumpContent;
        public String states;
        public Object createMan;
        public Object updateMan;
        public String loginType;
        public String jumpType;
        public long createTime;
        public long updateTime;
        public String imgName;
    }
}
