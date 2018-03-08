package com.hc360.koiambuyer.api.bean;

import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/9/29
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class InitInfo{

    /**
     * content : {"compId":8,"userHeadImg":"http://img07.iambuyer.com/imgup/upload_bak/images/2017/12/04/WUtTi4uIjWtDEfWR8BKAInI58wZhcjjc2FqNBC5iUmkvUp1lIOPqWyLx4ThbeTnc8qzZwc.jpg","compLogo":"http://img03.iambuyer.com/imgup/upload/images/2017/12/04/PrnqIIZUeYpvVQfXLIdUvN29nXfxM7CiIUKYIcTAuXFxNkwGtHGI4HBtLmhBlhcjjc2BGP.jpg","compName":"慧聪","qqCode":"123456","userEmail":null,"isCust":"1","cName":"北京市","loginType":"1","isWriteCompInfo":"1","aName":"朝阳区","isBoss":0,"pName":"北京","myStProductIntentionList":[{"firstCateId":"003","cateName":"办公文教"}],"cUserId":97,"inName":"食品加工及包装","userId":97,"initType":"1","userName":"123","userPhone":"15501150866","checkState":"-1"}
     * ret : 200
     * msg : success
     */

    public ContentBean content;
    public String ret;
    public String msg;

    public static class ContentBean {
        /**
         * compId : 8
         * userHeadImg : http://img07.iambuyer.com/imgup/upload_bak/images/2017/12/04/WUtTi4uIjWtDEfWR8BKAInI58wZhcjjc2FqNBC5iUmkvUp1lIOPqWyLx4ThbeTnc8qzZwc.jpg
         * compLogo : http://img03.iambuyer.com/imgup/upload/images/2017/12/04/PrnqIIZUeYpvVQfXLIdUvN29nXfxM7CiIUKYIcTAuXFxNkwGtHGI4HBtLmhBlhcjjc2BGP.jpg
         * compName : 慧聪
         * qqCode : 123456
         * userEmail : null
         * isCust : 1
         * cName : 北京市
         * loginType : 1
         * isWriteCompInfo : 1
         * aName : 朝阳区
         * isBoss : 0
         * pName : 北京
         * myStProductIntentionList : [{"firstCateId":"003","cateName":"办公文教"}]
         * cUserId : 97
         * inName : 食品加工及包装
         * userId : 97
         * initType : 1
         * userName : 123
         * userPhone : 15501150866
         * checkState : -1
         */

        public int compId;
        public String userHeadImg;
        public String compLogo;
        public String compName;
        public String qqCode;
        public String userEmail;
        public String isCust;
        public String cName;
        public String loginType;
        public String isWriteCompInfo;
        public String aName;
        public int isBoss;
        public String pName;
        public int cUserId;
        public String inName;
        public int userId;
        public String initType;
        public String wxCode;
        public String userName;
        public String refuseReason;
        public String userPosition;
        public String userPhone;
        public String checkState;
        public List<MyStProductIntentionListBean> myStProductIntentionList;

        public static class MyStProductIntentionListBean {
            /**
             * firstCateId : 003
             * cateName : 办公文教
             */

            public String firstCateId;
            public String cateName;
        }
    }
}
