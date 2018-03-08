package com.hc360.koiambuyer.api.bean;

import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/18
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class IntentInfo {

    public int count;
    public List<ListBean> list;

    public static class ListBean {
        public ListBean(String cateId, String cateName) {
            this.cateId = cateId;
            this.cateName = cateName;
        }
        public int id;
        public String states;
        public Object parentCateId;
        public String cateId;
        public String cateName;
    }
}
