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

public class OrderInfo {

    /**
     * count : 2
     * list : [{"id":3,"productName":"2017款 苹果笔记本","loopImg001":"http://img04.iambuyer.com/imgup/upload/images/2017/12/22/IXNbKLg6PaJ3Zh6Thcjjc2Bfy334aHhcjjc2BNNqNylhVZhcjjc2Bmiemb7GK4FaNcW0Gr0vRKFOBNXhcjjc2FGhcjjc2F.jpg","productId":221,"minNumber":10,"maxNumber":200,"money":200.5,"maxPice":1000,"orderStat":"3","orderNo":"f6e1b05a-b1ab-4321-926d-c6a7a20e93e0","createTime":1521183713000,"goodsId":null,"minPice":10,"productUnit":"1","productTotal":100,"productMaxcount":20,"orderSimunitpri":null,"orderSimprice":null,"orderPrice":null,"orderContent":"备注说明--JJC测试","orderSimcount":10,"orderCode":"201803161501539bj5krh","orderCount":100,"orderUnitpri":200.1},{"id":5,"productName":"2017款 苹果笔记本","loopImg001":"http://img03.iambuyer.com/imgup/upload/images/2017/12/22/IXNbKLg6PaJ3Zh6Thcjjc2Bfy334aHhcjjc2BNNqNylhVZhcjjc2Bmiemb7GK4FaNcW0Gr0vRKFOBNXhcjjc2FGhcjjc2F.jpg","productId":221,"minNumber":10,"maxNumber":200,"money":952,"maxPice":1000,"orderStat":"3","orderNo":"57bb6cc6-db28-4128-b1d6-c6011c4e36ab","createTime":1521530629000,"goodsId":null,"minPice":10,"productUnit":"1","productTotal":100,"productMaxcount":20,"orderSimunitpri":5.2,"orderSimprice":52,"orderPrice":900,"orderContent":"备注说明--JJC测试","orderSimcount":10,"orderCode":"20180320152349kjikzns","orderCount":100,"orderUnitpri":9.2}]
     */

    public int count;
    public List<ListBean> list;

    public static class ListBean {
        /**
         * id : 3
         * productName : 2017款 苹果笔记本
         * loopImg001 : http://img04.iambuyer.com/imgup/upload/images/2017/12/22/IXNbKLg6PaJ3Zh6Thcjjc2Bfy334aHhcjjc2BNNqNylhVZhcjjc2Bmiemb7GK4FaNcW0Gr0vRKFOBNXhcjjc2FGhcjjc2F.jpg
         * productId : 221
         * minNumber : 10
         * maxNumber : 200
         * money : 200.5
         * maxPice : 1000
         * orderStat : 3
         * orderNo : f6e1b05a-b1ab-4321-926d-c6a7a20e93e0
         * createTime : 1521183713000
         * goodsId : null
         * minPice : 10
         * productUnit : 1
         * productTotal : 100
         * productMaxcount : 20
         * orderSimunitpri : null
         * orderSimprice : null
         * orderPrice : null
         * orderContent : 备注说明--JJC测试
         * orderSimcount : 10
         * orderCode : 201803161501539bj5krh
         * orderCount : 100
         * orderUnitpri : 200.1
         */

        public int id;
        public String productName;
        public String loopImg001;
        public int productId;
        public int minNumber;
        public int maxNumber;
        public BigDecimal money;
        public BigDecimal maxPice;
        public String orderStat;
        public String orderNo;
        public long createTime;
        public int goodsId;
        public BigDecimal minPice;
        public String productUnit;
        public int productTotal;
        public int productMaxcount;
        public BigDecimal orderSimunitpri;
        public BigDecimal orderSimprice;
        public BigDecimal orderPrice;
        public String orderContent;
        public int orderSimcount;
        public String orderCode;
        public int orderCount;
        public BigDecimal orderUnitpri;
    }
}
