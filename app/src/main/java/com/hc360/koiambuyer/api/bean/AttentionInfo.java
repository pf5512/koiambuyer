package com.hc360.koiambuyer.api.bean;

import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/25
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class AttentionInfo {

    /**
     * count : 1
     * list : [{"compId":1,"sysIndustryName":"应急救援","count":29,"compLogo":"http://img04.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg","compName":"慧聪大超市","cName":"北京市","isCust":"1","mainIndustry":"266","aName":"东城区","pName":"北京","ADDRESS_DETAIL":"地址","ProNames":"IT、数码周边用品,簿/本/册,","compNature":"1","compShortName":"姜哥公司222","checkState":"1"}]
     */

    public int count;
    public List<ListBean> list;

    public static class ListBean {
        /**
         * compId : 1
         * sysIndustryName : 应急救援
         * count : 29
         * compLogo : http://img04.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg
         * compName : 慧聪大超市
         * cName : 北京市
         * isCust : 1
         * mainIndustry : 266
         * aName : 东城区
         * pName : 北京
         * ADDRESS_DETAIL : 地址
         * ProNames : IT、数码周边用品,簿/本/册,
         * compNature : 1
         * compShortName : 姜哥公司222
         * checkState : 1
         */

        public int compId;
        public String sysIndustryName;
        public int count;
        public String compLogo;
        public String compName;
        public String cName;
        public String isCust;
        public String mainIndustry;
        public String aName;
        public String pName;
        public String ADDRESS_DETAIL;
        public String ProNames;
        public String compNature;
        public String compShortName;
        public String checkState;
    }
}
