package com.hc360.koiambuyer.api.bean;

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

public class GoodsInfo {

    public int count;
    public List<ListBean> list;

    public static class ListBean {
        public int lookCount;
        public int recordCount;
        public String productStates;
        public String productName;
        public String brandName;
        public String isOnline;
        public String firstCateId;
        public String secondCateId;
        public String thirdCateId;
        public String fourthCateId;
        public int deliverGoogsId;
        public String productPrice;
        public String isHaveGoods;
        public String isMade;
        public int userId;
        public String states;
        public long createTime;
        public BigDecimal minPrice;
        public String unit;
        public String createMan;
        public long updateTime;
        public String updateMan;
        public String loopImg001;
        public String loopImg002;
        public String loopImg003;
        public String loopImg004;
        public String loopImg005;
        public int providerId;
        public int productId;
    }
}
