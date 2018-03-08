package com.hc360.koiambuyer.api.bean;

import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/3
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class KeepGoodsInfo {

    /**
     * count : 1
     * list : [{"userName":"姜家成1","compName":"姜家成股份有限公司","unit":"个","userHeadImg":"http://img02.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg","proName":"产品名称21","minPice":2.1,"proImg":"http://img06.iambuyer.com/imgup/upload/images/2017/10/12/zQZOUeKhcjjc2Bhcjjc2BR6ZfJQryzwjQB5IA5HDxb8BP0gFLDShUCe5eWZEbZc0kBEdu9revhcjjc2F1P.png","id":1,"followState":"0","compId":1,"userId":51,"productId":3,"productUserId":51,"states":"0","createMan":"iambuyer","updateMan":"iambuyer","createTime":1507700986000,"updateTime":1507701835000,"followTime":1507701835000}]
     */

    public int count;
    public List<ListBean> list;

    public static class ListBean {
        /**
         * userName : 姜家成1
         * compName : 姜家成股份有限公司
         * unit : 个
         * userHeadImg : http://img02.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg
         * proName : 产品名称21
         * minPice : 2.1
         * proImg : http://img06.iambuyer.com/imgup/upload/images/2017/10/12/zQZOUeKhcjjc2Bhcjjc2BR6ZfJQryzwjQB5IA5HDxb8BP0gFLDShUCe5eWZEbZc0kBEdu9revhcjjc2F1P.png
         * id : 1
         * followState : 0
         * compId : 1
         * userId : 51
         * productId : 3
         * productUserId : 51
         * states : 0
         * createMan : iambuyer
         * updateMan : iambuyer
         * createTime : 1507700986000
         * updateTime : 1507701835000
         * followTime : 1507701835000
         */

        public String userName;
        public String compName;
        public String unit;
        public String userHeadImg;
        public String proName;
        public double minPice;
        public String proImg;
        public int id;
        public String followState;
        public int compId;
        public int userId;
        public int productId;
        public int productUserId;
        public String states;
        public String createMan;
        public String updateMan;
        public long createTime;
        public long updateTime;
        public long followTime;
        public int recordCount;
        public int lookCount;
    }
}
