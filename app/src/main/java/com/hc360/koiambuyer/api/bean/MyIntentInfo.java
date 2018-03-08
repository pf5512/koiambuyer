package com.hc360.koiambuyer.api.bean;

import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/27
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class MyIntentInfo {

    public int count;
    public List<ListBean> list;
    public static class ListBean {
        public String firstCateId;
        public String cateName;
    }
}
