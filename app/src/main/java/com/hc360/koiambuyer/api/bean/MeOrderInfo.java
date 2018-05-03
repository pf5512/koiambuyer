package com.hc360.koiambuyer.api.bean;

/**
 * Project: koiambuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2018/3/21
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class MeOrderInfo {
    public String text;
    public int picRes;
    public int num;

    public MeOrderInfo(String text, int picRes, int num) {
        this.text = text;
        this.picRes = picRes;
        this.num = num;
    }

    @Override
    public String toString() {
        return "MeOrderInfo{" +
                "text='" + text + '\'' +
                ", picRes=" + picRes +
                ", num=" + num +
                '}';
    }
}
