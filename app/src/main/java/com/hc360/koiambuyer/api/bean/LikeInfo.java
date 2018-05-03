package com.hc360.koiambuyer.api.bean;

import java.util.List;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/19
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class LikeInfo {

    /**
     * count : 1
     * list : [{"userName":null,"compName":null,"userHeadImg":null,"sysType":"IambuyerKorea","unit":null,"recordCount":5,"lookCount":11,"proName":"2017款 苹果笔记本","proImg":"http://img04.iambuyer.com/imgup/upload/images/2017/12/22/IXNbKLg6PaJ3Zh6Thcjjc2Bfy334aHhcjjc2BNNqNylhVZhcjjc2Bmiemb7GK4FaNcW0Gr0vRKFOBNXhcjjc2FGhcjjc2F.jpg","minPice":null,"id":17,"userId":365,"compId":null,"followState":"0","productId":221,"productUserId":203,"createTime":1521172094000,"createMan":"iambuyer","updateTime":1521426412000,"updateMan":"iambuyer","followTime":1521426412000,"states":"0"}]
     */

    public int count;
    public List<ListBean> list;

    public static class ListBean {
        /**
         * userName : null
         * compName : null
         * userHeadImg : null
         * sysType : IambuyerKorea
         * unit : null
         * recordCount : 5
         * lookCount : 11
         * proName : 2017款 苹果笔记本
         * proImg : http://img04.iambuyer.com/imgup/upload/images/2017/12/22/IXNbKLg6PaJ3Zh6Thcjjc2Bfy334aHhcjjc2BNNqNylhVZhcjjc2Bmiemb7GK4FaNcW0Gr0vRKFOBNXhcjjc2FGhcjjc2F.jpg
         * minPice : null
         * id : 17
         * userId : 365
         * compId : null
         * followState : 0
         * productId : 221
         * productUserId : 203
         * createTime : 1521172094000
         * createMan : iambuyer
         * updateTime : 1521426412000
         * updateMan : iambuyer
         * followTime : 1521426412000
         * states : 0
         */

        public String userName;
        public String compName;
        public String userHeadImg;
        public String sysType;
        public Object unit;
        public int recordCount;
        public int lookCount;
        public int followCount;
        public String proName;
        public String proImg;
        public Object minPice;
        public int id;
        public int userId;
        public Object compId;
        public String followState;
        public String isOnline;
        public int productId;
        public int productUserId;
        public long createTime;
        public String createMan;
        public long updateTime;
        public String updateMan;
        public long followTime;
        public String states;
    }
}
