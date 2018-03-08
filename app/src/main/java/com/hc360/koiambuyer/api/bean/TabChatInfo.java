package com.hc360.koiambuyer.api.bean;

import java.util.List;

/**
 * Project: IAmBuyer
 * Author:  张佳林
 * Version:  1.0
 * Date:    2017/11/17
 * Modify:  //TODO
 * Description: //TODO
 * Copyright notice:
 */

public class TabChatInfo {

    /**
     * content : [{"userHeadImg":"http://img08.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg","title":"标题","sysIndustryName":"家电配件","toUserId":51,"compName":"企业名称","addStr":"北京 北京市 东城区","userName":"姜家成1","endTime":1510818298000,"fromUserId":51,"userPosition":"销售经理"},{"userHeadImg":"http://img07.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg","title":"好开心","sysIndustryName":"家电配件","toUserId":55,"compName":"企业名称","addStr":"北京 北京市 东城区","userName":"www","endTime":1510622463000,"fromUserId":51,"userPosition":"销售经理"}]
     * ret : 200
     * msg : success
     */

    public String ret;
    public String msg;
    public List<ContentBean> content;

    public static class ContentBean {
        /**
         * userHeadImg : http://img08.iambuyer.com/imgup/upload/images/2017/10/12/hcjjc2FiYnHjiQG5aNDdWxWQQzdMShcjjc2F1QPARHw68Wlq0DWiNblgLUzWdxhcjjc2Bq0BH0nojUwxm1.jpg
         * title : 标题
         * sysIndustryName : 家电配件
         * toUserId : 51
         * compName : 企业名称
         * addStr : 北京 北京市 东城区
         * userName : 姜家成1
         * endTime : 1510818298000
         * fromUserId : 51
         * userPosition : 销售经理
         */

        public String userHeadImg;
        public String title;
        public String sysIndustryName;
        public int toUserId;
        public String compName;
        public String addStr;
        public String userName;
        public long endTime;
        public long lastTime;
        public int fromUserId;
        public String userPosition;
        public String checkState;
        public String lastMsg;
        public int notReadNum;
    }
}
