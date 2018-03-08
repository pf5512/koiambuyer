package com.hc360.koiambuyer.api.bean;

import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/24
 * Modify:  //TODO
 * Description: //收藏求购
 * Copyright notice:
 */

public class TalkOverInfo {

    public int count;
    public List<ListBean> list;

    public static class ListBean {

        public String userName;
        public String compName;
        public String userHeadImg;
        public String isState;
        public StProductListBean stProduct_list;
        public int id;
        public int userId;
        public int stCompId;
        public int stUserId;
        public String followState;
        public int stProductListId;
        public String states;
        public String createMan;
        public String updateMan;
        public long createTime;
        public long updateTime;
        public long followTime;
        public Object stProductId;

        public static class StProductListBean {

            public int compId;
            public int userId;
            public String listState;
            public long endTime;
            public int productListId;
            public String states;
            public String createMan;
            public String updateMan;
            public int lookCount;
            public Object soldCause;
            public long createTime;
            public long updateTime;
            public int shareCount;
            public int recordCount;
            public String productListTitle;
            public String productListDetail;
        }
    }
}
