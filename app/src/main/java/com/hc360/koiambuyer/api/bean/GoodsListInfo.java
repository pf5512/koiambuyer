package com.hc360.koiambuyer.api.bean;

import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/16
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class GoodsListInfo {


    /**
     * count : 2
     * list : [{"typeName":null,"userName":"www","userPosition":"销售经理","userHeadImg":"http://img04.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg","compName":"企业名称","unit":"个","addStr":"北京 北京市 东城区","priceList":null,"intro":null,"imgs":null,"minPrice":2.1,"sysCategoryStr":"CD盒","productName":"张家林产品","loopImg001":"http://img07.iambuyer.com/imgup/upload/images/2017/10/20/AtmOshYCWZ8B8xhcjjc2BfjfbQ4jVP4rJk3gAAsJ25eJFUAofXsemvgLY1FO3aUQDAhoFl.jpg","loopImg002":"http://img05.iambuyer.com/imgup/upload/images/2017/10/20/tOhcjjc2BMpx2ix8idYqB11jTIPCVDoyxzoQdcQhcjjc2Bhcjjc2Ft37rIIiz18hcjjc2BcKVUEgk0Rjx5qLc4jk.jpg","loopImg003":"http://img07.iambuyer.com/imgup/upload/images/2017/10/20/Y0txU8gOyKyyblCopn877SzX3dOgCMRfC6bzeCTa2cja6hcjjc2FZLxaTD2bgw3f15anwd.jpg","loopImg004":"http://img08.iambuyer.com/imgup/upload/images/2017/10/20/HtwiGSYqrl9MWsLsFkuSQStvagXB1WvjEUqke5cZYtJgJfFpQA0KijR3L87WGA3R.jpg","loopImg005":"http://img07.iambuyer.com/imgup/upload/images/2017/10/20/LOWIUhcjjc2BZznjZ0EkGziB2hcjjc2Fxx2TjlFeJwVaE6IeawouNNZmuTo60cUIG55XHWDP7lMhcjjc2F.jpg","thirdCateId":"001001001","secondCateId":"001001","deliverGoogsId":1,"providerId":1,"userId":55,"productId":52,"states":"0","createMan":"iambuyer","updateMan":"iambuyer","isMade":"1","brandName":"品牌名称","isOnline":"1","createTime":1508482444000,"updateTime":1508482444000,"shareCount":0,"recordCount":1,"productStates":null,"firstCateId":"001","fourthCateId":"","productPrice":"1","isHaveGoods":"0","lookCount":2},{"typeName":null,"userName":"www","userPosition":"销售经理","userHeadImg":"http://img07.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg","compName":"企业名称","unit":"个","addStr":"北京 北京市 东城区","priceList":null,"intro":null,"imgs":null,"minPrice":2.1,"sysCategoryStr":"CD盒","productName":"张家林产品","loopImg001":"http://img04.iambuyer.com/imgup/upload/images/2017/10/20/9EDNp1A19p3Ts6D7EF3F7kYM7L6LbAoxhcjjc2FKsfkrX44xlCgERpbhcjjc2Fn0yjh12xWVbRQi.jpg","loopImg002":"http://img08.iambuyer.com/imgup/upload/images/2017/10/20/aEg30hcjjc2Bzhcjjc2BvZEib6UIfSPL2UwnYw8FUzHKZMYBKBhcjjc2FSZKhcjjc2FdzaMhcjjc2FQh6tjdFq5qhLTdP1.jpg","loopImg003":"http://img01.iambuyer.com/imgup/upload/images/2017/10/20/APSxDhcjjc2FU39CU7Rv8FQyhcjjc2Fl5Qhcjjc2FiznsooC0KvlDuNXl3vLNk88O52RGPHfNuhcjjc2BoQXhcjjc2BExH.jpg","loopImg004":"http://img06.iambuyer.com/imgup/upload/images/2017/10/20/GiSBkhcjjc2Bd2hcjjc2Bp7dJmYUTOXnNhcjjc2FkRciKG1mNjzLZjiUhSIOkUBFcdDxF4B5wOvIjJZGjh.jpg","loopImg005":"http://img07.iambuyer.com/imgup/upload/images/2017/10/20/pJlMzKG5pmc6SQ7T304UpnA0Lg3V8flRkuRE3Q45jH45gHzu8Oz2r077RE2c6W6O.jpg","thirdCateId":"001001001","secondCateId":"001001","deliverGoogsId":1,"providerId":1,"userId":55,"productId":53,"states":"0","createMan":"iambuyer","updateMan":"iambuyer","isMade":"1","brandName":"品牌名称","isOnline":"1","createTime":1508482445000,"updateTime":1508482445000,"shareCount":0,"recordCount":1,"productStates":null,"firstCateId":"001","fourthCateId":"","productPrice":"1","isHaveGoods":"0","lookCount":2}]
     */

    public int count;
    public List<ListBean> list;

    public static class ListBean {
        /**
         * typeName : null
         * userName : www
         * userPosition : 销售经理
         * userHeadImg : http://img04.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg
         * compName : 企业名称
         * unit : 个
         * addStr : 北京 北京市 东城区
         * priceList : null
         * intro : null
         * imgs : null
         * minPrice : 2.1
         * sysCategoryStr : CD盒
         * productName : 张家林产品
         * loopImg001 : http://img07.iambuyer.com/imgup/upload/images/2017/10/20/AtmOshYCWZ8B8xhcjjc2BfjfbQ4jVP4rJk3gAAsJ25eJFUAofXsemvgLY1FO3aUQDAhoFl.jpg
         * loopImg002 : http://img05.iambuyer.com/imgup/upload/images/2017/10/20/tOhcjjc2BMpx2ix8idYqB11jTIPCVDoyxzoQdcQhcjjc2Bhcjjc2Ft37rIIiz18hcjjc2BcKVUEgk0Rjx5qLc4jk.jpg
         * loopImg003 : http://img07.iambuyer.com/imgup/upload/images/2017/10/20/Y0txU8gOyKyyblCopn877SzX3dOgCMRfC6bzeCTa2cja6hcjjc2FZLxaTD2bgw3f15anwd.jpg
         * loopImg004 : http://img08.iambuyer.com/imgup/upload/images/2017/10/20/HtwiGSYqrl9MWsLsFkuSQStvagXB1WvjEUqke5cZYtJgJfFpQA0KijR3L87WGA3R.jpg
         * loopImg005 : http://img07.iambuyer.com/imgup/upload/images/2017/10/20/LOWIUhcjjc2BZznjZ0EkGziB2hcjjc2Fxx2TjlFeJwVaE6IeawouNNZmuTo60cUIG55XHWDP7lMhcjjc2F.jpg
         * thirdCateId : 001001001
         * secondCateId : 001001
         * deliverGoogsId : 1
         * providerId : 1
         * userId : 55
         * productId : 52
         * states : 0
         * createMan : iambuyer
         * updateMan : iambuyer
         * isMade : 1
         * brandName : 品牌名称
         * isOnline : 1
         * createTime : 1508482444000
         * updateTime : 1508482444000
         * shareCount : 0
         * recordCount : 1
         * productStates : null
         * firstCateId : 001
         * fourthCateId :
         * productPrice : 1
         * isHaveGoods : 0
         * lookCount : 2
         */

        public Object typeName;
        public String userName;
        public String userPosition;
        public String userHeadImg;
        public String compName;
        public String unit;
        public String addStr;
        public Object priceList;
        public Object intro;
        public Object imgs;
        public double minPrice;
        public String sysCategoryStr;
        public String productName;
        public String loopImg001;
        public String loopImg002;
        public String loopImg003;
        public String loopImg004;
        public String loopImg005;
        public String thirdCateId;
        public String secondCateId;
        public int deliverGoogsId;
        public int providerId;
        public int userId;
        public int productId;
        public String states;
        public String createMan;
        public String updateMan;
        public String isMade;
        public String brandName;
        public String isOnline;
        public long createTime;
        public long updateTime;
        public int shareCount;
        public int recordCount;
        public Object productStates;
        public String firstCateId;
        public String fourthCateId;
        public String productPrice;
        public String isHaveGoods;
        public int lookCount;
    }
}
