package com.hc360.koiambuyer.api.bean;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/18
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class ClassifyBean {
    public String cateName;
    public String cateId;
    public String parentCateId;

    public ClassifyBean(String cateName, String cateId, String parentCateId) {
        this.cateName = cateName;
        this.cateId = cateId;
        this.parentCateId = parentCateId;
    }
}
