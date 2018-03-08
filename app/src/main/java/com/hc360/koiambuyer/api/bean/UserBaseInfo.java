package com.hc360.koiambuyer.api.bean;

import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/10/24
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class UserBaseInfo {


    /**
     * content : {"purcIntentionList":[{"firstCateId":"001","cateName":"IT设备/数码产品/软件"},{"firstCateId":"002","cateName":"安全/防护"}],"wxCode":"11111","compId":1,"phone":"15501150866","userHeadImg":"http://img06.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg","compLogo":"http://img08.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg","compName":"姜家成股份有限公司","qqCode":"123456","isCust":"0","cName":"北京市","loginType":"0","aName":"东城区","isBoss":0,"pName":"北京","cUserId":55,"inName":"家电配件","userId":55,"userName":"姜家成221","emailCode":"","checkState":"1","userPosition":"销售经理"}
     * ret : 200
     * msg : success
     */

    public ContentBean content;
    public String ret;
    public String msg;

    public static class ContentBean {
        /**
         * purcIntentionList : [{"firstCateId":"001","cateName":"IT设备/数码产品/软件"},{"firstCateId":"002","cateName":"安全/防护"}]
         * wxCode : 11111
         * compId : 1
         * phone : 15501150866
         * userHeadImg : http://img06.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg
         * compLogo : http://img08.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg
         * compName : 姜家成股份有限公司
         * qqCode : 123456
         * isCust : 0
         * cName : 北京市
         * loginType : 0
         * aName : 东城区
         * isBoss : 0
         * pName : 北京
         * cUserId : 55
         * inName : 家电配件
         * userId : 55
         * userName : 姜家成221
         * emailCode :
         * checkState : 1
         * userPosition : 销售经理
         */

        public String wxCode;
        public int compId;
        public String phone;
        public String userHeadImg;
        public String compLogo;
        public String compName;
        public String qqCode;
        public String isCust;
        public String cName;
        public String loginType;
        public String aName;
        public int isBoss;
        public String pName;
        public int cUserId;
        public String inName;
        public int userId;
        public String userName;
        public String emailCode;
        public String refuseReason;
        public String checkState;
        public String userPosition;
        public List<PurcIntentionListBean> purcIntentionList;

        public static class PurcIntentionListBean {
            /**
             * firstCateId : 001
             * cateName : IT设备/数码产品/软件
             */

            public String firstCateId;
            public String cateName;
        }
    }
}
