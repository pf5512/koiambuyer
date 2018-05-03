package com.hc360.koiambuyer.api.bean;

import java.math.BigDecimal;
import java.util.List;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/4/8
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class ChatGoodInfo {

    /**
     * count : 3
     * list : [{"proId":221,"createTime":1523156253000,"userHeadImg":null,"proName":"2017款 苹果笔记本","userId":203,"userName":"王亚蒙","proImg":"http://img03.iambuyer.com/imgup/upload/images/2017/12/22/IXNbKLg6PaJ3Zh6Thcjjc2Bfy334aHhcjjc2BNNqNylhVZhcjjc2Bmiemb7GK4FaNcW0Gr0vRKFOBNXhcjjc2FGhcjjc2F.jpg"},{"proId":229,"createTime":1523155264000,"userHeadImg":"http://img04.iambuyer.com/imgup/upload/images/2018/04/02/lFwQazC9qBQy0B2rE2Cmhcjjc2Bm0SvmzcogT9Ao56k2ggOkhHfZAIthcjjc2FY4UcBumLVy5D3z.jpeg","proName":"好纠结","userId":57,"userName":"吴启万","proImg":"http://img05.iambuyer.com/imgup/upload/images/2018/01/11/PpCtpvFqeWlA8TpwycsDPmyTF3f0bsudRISMaASG2nD7Fw6ho9FSefoIhcjjc2Bhcjjc2BOAnGDX.jpg"},{"proId":234,"createTime":1522725949000,"userHeadImg":null,"proName":"-成功后女啊v不过好难吃 跟我走徐v不是真丰富V型不好听安慰 个@想去操场v吃的","userId":202,"userName":"柳","proImg":"http://img01.iambuyer.com/imgup/upload/images/2018/01/26/qroXnARS2hcjjc2B3921OZC6hgsz9rFGK9JYVqx6ooOn8ViVVMEWYKj4jRVXVlhcjjc2FBsHkiCC.jpg"}]
     */

    public int count;
    public List<ListBean> list;

    public static class ListBean {
        /**
         * proId : 221
         * createTime : 1523156253000
         * userHeadImg : null
         * proName : 2017款 苹果笔记本
         * userId : 203
         * userName : 王亚蒙
         * proImg : http://img03.iambuyer.com/imgup/upload/images/2017/12/22/IXNbKLg6PaJ3Zh6Thcjjc2Bfy334aHhcjjc2BNNqNylhVZhcjjc2Bmiemb7GK4FaNcW0Gr0vRKFOBNXhcjjc2FGhcjjc2F.jpg
         */

        public int proId;
        public long createTime;
        public String userHeadImg;
        public String proName;
        public int userId;
        public String isOnline;
        public String userName;
        public String proImg;
        public BigDecimal maxPrice;
        public BigDecimal minPrice;
    }
}
