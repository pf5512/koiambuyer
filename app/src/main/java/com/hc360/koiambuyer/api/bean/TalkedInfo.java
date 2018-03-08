package com.hc360.koiambuyer.api.bean;

import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/25
 * Modify:  //TODO
 * Description: //已洽谈
 * Copyright notice:
 */

public class TalkedInfo {


    /**
     * count : 1
     * list : [{"compId":1,"createTime":1508288245000,"userHeadImg":"http://img01.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg","sysIndustryName":"家电配件","compName":"姜家成股份有限公司","proImg":"zQZOUeKhcjjc2Bhcjjc2BR6ZfJQryzwjQB5IA5HDxb8BP0gFLDShUCe5eWZEbZc0kBEdu9revhcjjc2F1P.png","productId":6,"content":"你好啊测试----","title":"产品名称","userId":51,"addStr":"北京 北京市 东城区","userName":"姜家成1","minPice":2.1},{"compId":1,"createTime":1508223920000,"userHeadImg":"http://img01.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg","sysIndustryName":"家电配件","compName":"姜家成股份有限公司","proImg":"zQZOUeKhcjjc2Bhcjjc2BR6ZfJQryzwjQB5IA5HDxb8BP0gFLDShUCe5eWZEbZc0kBEdu9revhcjjc2F1P.png","productId":4,"content":"你好啊测试----","title":"产品名称","userId":51,"addStr":"北京 北京市 东城区","userName":"姜家成1","minPice":2.1}]
     */

    public int count;
    public List<ListBean> list;

    public static class ListBean {
        /**
         * compId : 1
         * createTime : 1508288245000
         * userHeadImg : http://img01.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg
         * sysIndustryName : 家电配件
         * compName : 姜家成股份有限公司
         * proImg : zQZOUeKhcjjc2Bhcjjc2BR6ZfJQryzwjQB5IA5HDxb8BP0gFLDShUCe5eWZEbZc0kBEdu9revhcjjc2F1P.png
         * productId : 6
         * content : 你好啊测试----
         * title : 产品名称
         * userId : 51
         * addStr : 北京 北京市 东城区
         * userName : 姜家成1
         * minPice : 2.1
         */

        public int compId;
        public long createTime;
        public String userHeadImg;
        public String sysIndustryName;
        public String compName;
        public String proImg;
        public int productId;
        public int lookCount;
        public int recordCount;
        public String content;
        public String title;
        public int userId;
        public String addStr;
        public String userName;
        public double minPice;
        public long endTime;
        public String isState;
        public String unit;
    }
}
