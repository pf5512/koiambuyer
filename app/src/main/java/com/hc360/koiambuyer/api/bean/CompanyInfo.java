package com.hc360.koiambuyer.api.bean;

import java.io.Serializable;
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

public class CompanyInfo implements Serializable{


    /**
     * content : {"count":null,"refuseReason":"5","checkState":"0","pName":"北京","cName":"北京市","aName":"东城区","inName":"家电配件","isFollow":"1","proNames":null,"userList":[{"compId":1,"userHeadImg":"http://img06.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg","userId":51,"userName":"姜家成1"},{"compId":1,"userHeadImg":"http://img07.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg","userId":55,"userName":"www"}],"provinceCode":"110000","addressDetail":"地址","mainIndustry":"151","banner1":"http://img04.iambuyer.com/imgup/upload/images/2017/10/26/JG78j2N2n3Ud0ldgzqnwxGcOEIJEAWoFIhw95jJnnhcjjc2FAzhUSw0nde7Cn4BcyZwhcjjc2FQhcjjc2B.jpg","banner2":"http://img04.iambuyer.com/imgup/upload/images/2017/10/26/9khcjjc2B42rOaAwhcjjc2BnJ0oHDeqwOH1OTj3rGE87VKNI0XkIoYBNx65fYvVpYl2qm8388nAl.jpg","banner3":null,"banner4":null,"banner5":null,"compLogo":"http://img06.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg","compId":1,"compType":"0","compName":"企业名称","cityCode":"110100","areaCode":"110101","userId":55,"states":"0","createMan":"iambuyer","updateMan":"iambuyer","latitude":"38.456789","longitude":"61.123456","compIntro":null,"iscust":"0","createTime":1506578322000,"updateTime":1510213811000,"compNature":"0","compShortName":"慧聪云信2"}
     * ret : 200
     * msg : success
     */

    public ContentBean content;
    public String ret;
    public String msg;

    public static class ContentBean {
        /**
         * count : null
         * refuseReason : 5
         * checkState : 0
         * pName : 北京
         * cName : 北京市
         * aName : 东城区
         * inName : 家电配件
         * isFollow : 1
         * proNames : null
         * userList : [{"compId":1,"userHeadImg":"http://img06.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg","userId":51,"userName":"姜家成1"},{"compId":1,"userHeadImg":"http://img07.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg","userId":55,"userName":"www"}]
         * provinceCode : 110000
         * addressDetail : 地址
         * mainIndustry : 151
         * banner1 : http://img04.iambuyer.com/imgup/upload/images/2017/10/26/JG78j2N2n3Ud0ldgzqnwxGcOEIJEAWoFIhw95jJnnhcjjc2FAzhUSw0nde7Cn4BcyZwhcjjc2FQhcjjc2B.jpg
         * banner2 : http://img04.iambuyer.com/imgup/upload/images/2017/10/26/9khcjjc2B42rOaAwhcjjc2BnJ0oHDeqwOH1OTj3rGE87VKNI0XkIoYBNx65fYvVpYl2qm8388nAl.jpg
         * banner3 : null
         * banner4 : null
         * banner5 : null
         * compLogo : http://img06.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg
         * compId : 1
         * compType : 0
         * compName : 企业名称
         * cityCode : 110100
         * areaCode : 110101
         * userId : 55
         * states : 0
         * createMan : iambuyer
         * updateMan : iambuyer
         * latitude : 38.456789
         * longitude : 61.123456
         * compIntro : null
         * iscust : 0
         * createTime : 1506578322000
         * updateTime : 1510213811000
         * compNature : 0
         * compShortName : 慧聪云信2
         */

        public Object count;
        public String refuseReason;
        public String checkState;
        public String pName;
        public String cName;
        public String aName;
        public String inName;
        public String isFollow;
        public Object proNames;
        public String provinceCode;
        public String addressDetail;
        public String mainIndustry;
        public String banner1;
        public String banner2;
        public String banner3;
        public String banner4;
        public String banner5;
        public String compLogo;
        public int compId;
        public String compType;
        public String compName;
        public String cityCode;
        public String areaCode;
        public int userId;
        public String states;
        public String createMan;
        public String updateMan;
        public String latitude;
        public String longitude;
        public String compIntro;
        public String iscust;
        public long createTime;
        public long updateTime;
        public String compNature;
        public String compShortName;
        public List<UserListBean> userList;

        public static class UserListBean {
            /**
             * compId : 1
             * userHeadImg : http://img06.iambuyer.com/imgup/upload/images/2017/10/11/JY1RGm53BdzS4ZqFAZjmMsP0rkGLofHeJlAdZ7lYswxMFAhcjjc2F5F66Ozhcjjc2FVWhY5ul6VK.jpg
             * userId : 51
             * userName : 姜家成1
             */

            public int compId;
            public String userHeadImg;
            public int userId;
            public String userName;
        }
    }
}
